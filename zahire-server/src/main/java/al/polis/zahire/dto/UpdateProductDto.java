package al.polis.zahire.dto;

import lombok.Data;

@Data
public class UpdateProductDto extends InsertProductDto {

    private Long productId; // only needed for update

}
