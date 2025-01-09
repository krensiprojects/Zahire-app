package al.polis.zahire.dto.Cart;

import lombok.Data;

@Data
public class ChangeCartQtyReqDto {
    private Long productId;
    private Integer qtyChange;
    private Long cartId;
}
