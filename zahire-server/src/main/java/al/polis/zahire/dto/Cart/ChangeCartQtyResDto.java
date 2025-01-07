package al.polis.zahire.dto.Cart;

import lombok.Data;

@Data
public class ChangeCartQtyResDto {
    private Long productId;
    private Integer newQuantity;
}
