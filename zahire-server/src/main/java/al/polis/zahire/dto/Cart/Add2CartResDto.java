package al.polis.zahire.dto.Cart;

import al.polis.zahire.dto.BaseMessagedDto;
import lombok.Data;

@Data
public class Add2CartResDto extends BaseMessagedDto {
    private int numOrdered;
    private int numBooked;
}
