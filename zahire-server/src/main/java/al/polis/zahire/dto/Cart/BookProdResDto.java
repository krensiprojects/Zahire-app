package al.polis.zahire.dto.Cart;

import al.polis.zahire.dto.BaseMessagedDto;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BookProdResDto extends BaseMessagedDto {
    private Long orderId;
    private Long bookingId;
    private LocalDate estimatedDeliveryDate;
}
