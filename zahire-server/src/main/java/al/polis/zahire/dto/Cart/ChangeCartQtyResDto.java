package al.polis.zahire.dto.Cart;

import al.polis.zahire.dto.BaseMessagedDto;
import lombok.Data;

@Data
public class ChangeCartQtyResDto extends BaseMessagedDto {
    private Long productId;
    private Integer newQuantity;
}
