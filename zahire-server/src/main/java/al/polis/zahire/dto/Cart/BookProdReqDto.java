package al.polis.zahire.dto.Cart;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BookProdReqDto {
    private Long productId;
    private Integer quantity;
    private LocalDate deliveryDate;
}
