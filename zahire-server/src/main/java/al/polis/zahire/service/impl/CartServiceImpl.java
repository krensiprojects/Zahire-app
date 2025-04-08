package al.polis.zahire.service.impl;

import al.polis.zahire.dto.BaseMessagedDto;
import al.polis.zahire.dto.Cart.*;
import al.polis.zahire.model.CatalogueProduct;
import al.polis.zahire.model.Order;
import al.polis.zahire.model.OrderRow;
import al.polis.zahire.model.OrderStatus;
import al.polis.zahire.repository.CatalogueProductRepository;
import al.polis.zahire.repository.OrderNowRepository;
import al.polis.zahire.repository.OrderRepository;
import al.polis.zahire.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CatalogueProductRepository catalogueProductRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderNowRepository orderNowRepository;

    @Override
    public Add2CartResDto addProductToCart(Add2CartReqDto requestDto) {
        Add2CartResDto responseDto = new Add2CartResDto();

        // check DTO
        if (requestDto == null) {

            responseDto.setMessage("Invalid request DTO");
            return responseDto;
        }
        // extract info from DTO and check them
        Long productId = requestDto.getProductId();
        if (productId == null) {
            responseDto.setMessage("Invalid product ID");
            return responseDto;
        }
        // check that the product is in catalog
        CatalogueProduct cp = catalogueProductRepository.findById(productId)
                .orElse(null);
        if (cp == null) {
            responseDto.setMessage("Unknown product ID");
            return responseDto;
        }

        Long cartId = requestDto.getCartId();
        if (cartId == null) {
            responseDto.setMessage("Invalid cart ID");
            return responseDto;
        }

        // find (if exists) the order in state "cart"
        Order order = orderRepository.findById(cartId)
                .orElse(null);

        // if it does not exist -> create it
        if (order == null) {
            order = new Order();
            order.setStatus(OrderStatus.CART);
            // FIXME: add missing fields
            order = orderRepository.save(order);
        }
        // found or created, look for the row with the product (if it exists)
        // retrieve all the rows for the cart
        List<OrderRow> rows = order.getOrderRows();

        // find for the one which has the product with given ID
        List<OrderRow> withMyProd = rows.stream()
                .filter(row -> row.getCatalogueProduct().getId().equals(productId))
                .toList();
        // check list size 0 or 1
        if (withMyProd.size() > 1) {
            responseDto.setMessage("Too many products. Contact technical support please with prodId " + productId);
            return responseDto;
        } else if (withMyProd.size() == 1) {
            // increase quantity
            OrderRow orderRow = withMyProd.get(0);
            var qty = orderRow.getQuantity();
            qty++;
            orderRow.setQuantity(qty);
            orderNowRepository.save(orderRow);
            responseDto.setMessage("Product has been added to the cart");
            responseDto.setNumOrdered(qty);
            return responseDto;
        }

        // no row found -> create it
        OrderRow orderRow = new OrderRow();
        orderRow.setQuantity(1);
        orderRow.setOrder(order);
        orderRow.setCatalogueProduct(cp);
        orderNowRepository.save(orderRow);

        order.getOrderRows().add(orderRow);
        orderRepository.save(order);

        cp.getOrders().add(orderRow);
        catalogueProductRepository.save(cp);
        responseDto.setMessage("Product has been added to the cart");
        responseDto.setNumOrdered(1);
        return responseDto;


    }

    @Override
    public ChangeCartQtyResDto increaseCartQty(ChangeCartQtyReqDto requestDto) {
        ChangeCartQtyResDto responseDto = new ChangeCartQtyResDto();

        // Extract cartId, productId, and quantity from the request DTO
        Long cartId = requestDto.getCartId();
        Long productId = requestDto.getProductId();
        int increaseBy = requestDto.getQtyChange(); // Quantity to increase (e.g., 1)

        if (cartId == null || productId == null || increaseBy <= 0) {
            responseDto.setMessage("Invalid request parameters");
            return responseDto;
        }

        // Fetch the order (cart) from the database using cartId
        Order order = orderRepository.findById(cartId).orElse(null);
        if (order == null) {
            responseDto.setMessage(BaseMessagedDto.CART_NOT_FOUND);
            return responseDto;
        }

        // Find the OrderRow for the given productId in the cart
        List<OrderRow> orderRows = order.getOrderRows();
        OrderRow orderRow = orderRows.stream()
                .filter(row -> row.getCatalogueProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (orderRow == null) {
            responseDto.setMessage("Product not found in the cart");
            return responseDto;
        }

        // Increase the quantity of the product in the cart
        int updatedQuantity = orderRow.getQuantity() + increaseBy;
        orderRow.setQuantity(updatedQuantity);

        // Save the updated OrderRow back to the database
        orderNowRepository.save(orderRow);

        // Return the updated quantity in the response
        responseDto.setMessage("Product quantity increased successfully");
        responseDto.setNewQuantity(updatedQuantity);
        return responseDto;
    }

    @Override
    public Add2CartResDto decreaseCartQty(Add2CartReqDto requestDto) {
        Add2CartResDto responseDto = new Add2CartResDto();

        // Step 1: Validate request
        if (requestDto == null || requestDto.getCartId() == null || requestDto.getProductId() == null) {
            responseDto.setMessage("Invalid request");
            return responseDto;
        }

        Long cartId = requestDto.getCartId();
        Long productId = requestDto.getProductId();

        // Step 2: Fetch cart (order)
        Order order = orderRepository.findById(cartId).orElse(null);
        if (order == null) {
            responseDto.setMessage("Cart not found");
            return responseDto;
        }

        // Step 3: Find the matching OrderRow
        List<OrderRow> rows = order.getOrderRows();
        Optional<OrderRow> optionalRow = rows.stream()
                .filter(row -> row.getCatalogueProduct().getId().equals(productId))
                .findFirst();

        if (optionalRow.isEmpty()) {
            responseDto.setMessage("Product not found in cart");
            return responseDto;
        }

        OrderRow orderRow = optionalRow.get();
        int qty = orderRow.getQuantity();

        // Step 4: Decrease quantity or remove row
        if (qty > 1) {
            orderRow.setQuantity(qty - 1);
            orderNowRepository.save(orderRow);
            responseDto.setMessage("Product quantity decreased");
            responseDto.setNumOrdered(qty - 1);
        } else {
            // Remove the order row from the cart
            order.getOrderRows().remove(orderRow);
            orderNowRepository.delete(orderRow);
            orderRepository.save(order);

            responseDto.setMessage("Product removed from cart");
            responseDto.setNumOrdered(0);
        }

        return responseDto;
    }


    @Override
    public RemoveFromCartResDto removeProductFromCart(RemoveFromCartReqDto requestDto) {
        RemoveFromCartResDto responseDto = new RemoveFromCartResDto();

        // Validate request
        if (requestDto == null || requestDto.getCartId() == null || requestDto.getProductId() == null) {
            responseDto.setMessage("Invalid request");
            return responseDto;
        }

        Long cartId = requestDto.getCartId();
        Long productId = requestDto.getProductId();

        // Find the cart
        Order order = orderRepository.findById(cartId).orElse(null);
        if (order == null) {
            responseDto.setMessage("Cart not found");
            return responseDto;
        }

        // Find the matching row
        List<OrderRow> rows = order.getOrderRows();
        Optional<OrderRow> optionalRow = rows.stream()
                .filter(row -> row.getCatalogueProduct().getId().equals(productId))
                .findFirst();

        if (optionalRow.isEmpty()) {
            responseDto.setMessage("Product not found in cart");
            return responseDto;
        }

        OrderRow orderRow = optionalRow.get();

        // Remove the row
        order.getOrderRows().remove(orderRow);
        orderNowRepository.delete(orderRow);
        orderRepository.save(order);

        responseDto.setMessage("Product removed from cart");
        responseDto.setNumOrdered(0);

        return responseDto;
    }


    @Override
    public ShowCartResDto showCart(ShowCartReqDto requestDto) {
        ShowCartResDto responseDto = new ShowCartResDto();
        List<CartItemDto> items = new ArrayList<>();

        if (requestDto == null || requestDto.getCartId() == null) {
            responseDto.setCartId(null);
            responseDto.setItems(items);

            return responseDto;
        }

        Long cartId = requestDto.getCartId();
        Order order = orderRepository.findById(cartId).orElse(null);

        if (order == null) {
            responseDto.setCartId(cartId);
            responseDto.setItems(items); // empty list
            return responseDto;
        }

        for (OrderRow row : order.getOrderRows()) {
            CatalogueProduct product = row.getCatalogueProduct();

            CartItemDto item = new CartItemDto();
            item.setProductId(product.getId());
            item.setProductName(product.getTitleDescription()); // update if name is different
            item.setQuantity(row.getQuantity());
            item.setPrice(product.getPrice());
            item.setImageUrl(product.getImageUrl());

            items.add(item);
        }

        responseDto.setCartId(order.getId());
        responseDto.setItems(items);

        return responseDto;
    }

    @Override
    public BookProdResDto bookProduct(BookProdReqDto requestDto) {
        BookProdResDto responseDto = new BookProdResDto();

        // Step 1: Validate the request data
        if (requestDto == null || requestDto.getCartId() == null) {
            responseDto.setMessage("Invalid request or cart ID");
            return responseDto;
        }

        // Step 2: Fetch the order (cart) from the database using cartId
        Long cartId = requestDto.getCartId();
        Order order = orderRepository.findById(cartId).orElse(null);
        if (order == null || !order.getStatus().equals(OrderStatus.CART)) {
            responseDto.setMessage("Order not found or not in cart state");
            return responseDto;
        }

        // Step 3: Check product availability (e.g., check stock)
        for (OrderRow row : order.getOrderRows()) {
            CatalogueProduct product = row.getCatalogueProduct();
            if (product.getStockQuantity() < row.getQuantity()) {
                responseDto.setMessage("Not enough stock for product " + product.getId());
                return responseDto;
            }
        }

        // Step 4: Update stock quantities (decrease stock based on order)
        for (OrderRow row : order.getOrderRows()) {
            CatalogueProduct product = row.getCatalogueProduct();
            product.setStockQuantity(product.getStockQuantity() - row.getQuantity());
            catalogueProductRepository.save(product);  // Save updated product stock
        }

        // Step 5: Change order status to "booked" or "completed"
        order.setStatus(OrderStatus.BOOKED);
        orderRepository.save(order);

        // Step 6: Return response
        responseDto.setMessage("Order has been successfully booked");
        responseDto.setOrderId(order.getId());
        return responseDto;
    }

    @Override
    public GetOrderTotalsResDto getOrderTotals(Long cartId) {
        GetOrderTotalsResDto responseDto = new GetOrderTotalsResDto();

        // Fetch the order (cart) by ID
        Order order = orderRepository.findById(cartId).orElse(null);
        if (order == null) {
            // Handle case if the cart is not found
            responseDto.setSubtotal(0.0);
            responseDto.setShippingFee(0.0);
            responseDto.setTaxAmount(0.0);
            responseDto.setTotalDiscount(0.0);
            responseDto.setTotal(0.0);
            responseDto.setCurrency("USD"); // Set the currency, or fetch it from config
            return responseDto;
        }

        // Calculate the subtotal (sum of all item prices * quantity)
        double subtotal = 0.0;
        for (OrderRow row : order.getOrderRows()) {
            CatalogueProduct product = row.getCatalogueProduct();
            subtotal += product.getPrice() * row.getQuantity();
        }

        // You can replace these with actual business logic
        double shippingFee = 10.0; // Example fixed shipping fee
        double taxAmount = subtotal * 0.1; // Example 10% tax
        double totalDiscount = 5.0; // Example fixed discount
        double total = subtotal + shippingFee + taxAmount - totalDiscount;

        // Set the values in response DTO
        responseDto.setSubtotal(subtotal);
        responseDto.setShippingFee(shippingFee);
        responseDto.setTaxAmount(taxAmount);
        responseDto.setTotalDiscount(totalDiscount);
        responseDto.setTotal(total);
        responseDto.setCurrency("USD"); // Set the currency, or fetch it from config

        return responseDto;
    }
}

