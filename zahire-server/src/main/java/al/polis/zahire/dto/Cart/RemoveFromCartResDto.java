package al.polis.zahire.dto.Cart;

import al.polis.zahire.dto.BaseMessagedDto;
import lombok.Data;

@Data
public class RemoveFromCartResDto extends BaseMessagedDto {
    private Integer totalItems;
    private Integer totalPrice;
    private int numOrdered;
}
