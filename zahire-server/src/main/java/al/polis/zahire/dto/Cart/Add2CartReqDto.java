package al.polis.zahire.dto.Cart;

import lombok.Data;

@Data
public class Add2CartReqDto {
    private Long productId;
    private Long cartId;
}
