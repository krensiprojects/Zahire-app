package al.polis.zahire.dto.Cart;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BookProdResDto {
    private Long bookingId;
    private LocalDate estimatedDeliveryDate;
}
