package al.polis.zahire.dto;

import lombok.Data;

@Data
public class BaseMessagedDto {
    private String message = "";
    // Predefined messages
    public static final String INVALID_REQUEST_DTO = "Invalid request DTO";
    public static final String INVALID_PRODUCT_ID = "Invalid product ID";
    public static final String UNKNOWN_PRODUCT_ID = "Unknown product ID";
    public static final String INVALID_CART_ID = "Invalid cart ID";
    public static final String TOO_MANY_PRODUCTS = "Too many products. Contact technical support please with prodId ";
    public static final String PRODUCT_ADDED_TO_CART = "Product has been added to the cart";
    public static final String INVALID_REQUEST_PARAMETERS = "Invalid request parameters";
    public static final String CART_NOT_FOUND = "Cart not found";
    public static final String PRODUCT_NOT_FOUND_IN_CART = "Product not found in the cart";
    public static final String PRODUCT_QUANTITY_INCREASED = "Product quantity increased successfully";
}
