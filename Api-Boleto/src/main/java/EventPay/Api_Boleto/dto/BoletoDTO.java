package EventPay.Api_Boleto.dto;

import lombok.*;
import java.time.LocalDateTime;
import EventPay.Api_Boleto.entity.enums.SituationBoleto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoletoDTO {

    private String barcode;

    private SituationBoleto situationBoleto;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;
}
