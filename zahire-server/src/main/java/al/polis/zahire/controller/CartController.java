package al.polis.zahire.controller;

import al.polis.zahire.dto.Cart.*;
import al.polis.zahire.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Add2CartResDto addProductToCart(@RequestBody Add2CartReqDto requestDto) {
        return cartService.addProductToCart(requestDto);
    }

    @PostMapping("/increase-qty")
    public ChangeCartQtyResDto increaseCartQty(@RequestBody ChangeCartQtyReqDto requestDto) {
        return cartService.increaseCartQty(requestDto);
    }

    @PostMapping("/decrease-qty")
    public ChangeCartQtyResDto decreaseCartQty(@RequestBody ChangeCartQtyReqDto requestDto) {
        return cartService.decreaseCartQty(requestDto);
    }

    @DeleteMapping("/remove")
    public RemoveFromCartResDto removeProductFromCart(@RequestBody RemoveFromCartReqDto requestDto) {
        return cartService.removeProductFromCart(requestDto);
    }
    @GetMapping("/show")
    public ShowCartResDto showCart(@RequestBody ShowCartReqDto requestDto) {
        return cartService.showCart(requestDto);
    }
    @PostMapping("/book")
    public BookProdResDto bookProduct(@RequestBody BookProdReqDto requestDto) {
        return cartService.bookProduct(requestDto);
    }
    @PostMapping("/getOrderTotals")
    public GetOrderTotalsResDto getOrderTotals(@RequestBody GetOrderTotalsReqDto requestDto) {
        return cartService.getOrderTotals(requestDto);
    }

}
