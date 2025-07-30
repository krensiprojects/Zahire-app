package al.polis.zahire.service;

import al.polis.zahire.dto.Cart.*;

public interface CartService {
    Add2CartResDto addProductToCart(Add2CartReqDto requestDto);

    ChangeCartQtyResDto increaseCartQty(ChangeCartQtyReqDto requestDto);

    ChangeCartQtyResDto decreaseCartQty(ChangeCartQtyReqDto requestDto);

    RemoveFromCartResDto removeProductFromCart(RemoveFromCartReqDto requestDto);

    ShowCartResDto showCart(ShowCartReqDto requestDto);

    BookProdResDto bookProduct(BookProdReqDto requestDto);

    GetOrderTotalsResDto getOrderTotals(GetOrderTotalsReqDto requestDto);



}
