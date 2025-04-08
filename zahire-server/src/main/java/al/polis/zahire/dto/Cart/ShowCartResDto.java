package al.polis.zahire.dto.Cart;

import lombok.Data;

import java.util.List;

@Data
public class ShowCartResDto {
    private Long cartId;
    private List<CartItemDto> items;

}
