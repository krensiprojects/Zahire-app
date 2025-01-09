package al.polis.zahire.service.impl;

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

import java.util.List;

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
        return null;
    }

    @Override
    public ChangeCartQtyResDto decreaseCartQty(ChangeCartQtyReqDto requestDto) {
        return null;
    }

    @Override
    public RemoveFromCartResDto removeProductFromCart(RemoveFromCartReqDto requestDto) {
        return null;
    }

    @Override
    public ShowCartResDto showCart(ShowCartReqDto requestDto) {
        return null;
    }

    @Override
    public BookProdResDto bookProduct(BookProdReqDto requestDto) {
        return null;
    }

    @Override
    public GetOrderTotalsResDto getOrderTotals(GetOrderTotalsReqDto requestDto) {
        return null;
    }
}
