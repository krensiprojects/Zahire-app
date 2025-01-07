package al.polis.zahire.dto.Cart;

import lombok.Data;

@Data
public class ShowCartResDto {
    private Long cartId;
    private List<CartItemDto> items;
}
