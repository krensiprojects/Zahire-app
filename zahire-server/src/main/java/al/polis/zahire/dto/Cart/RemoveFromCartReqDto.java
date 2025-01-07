package al.polis.zahire.dto.Cart;

import lombok.Data;

@Data
public class RemoveFromCartReqDto {
    private Long productId;
    private Long cartId;
}
