package al.polis.zahire.dto.Cart;

import lombok.Data;

@Data
public class CartItemDto {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
    private String imageUrl;
    private double subtotal;

}
