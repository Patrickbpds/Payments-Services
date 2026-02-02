package EventPay.Api_Boleto.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoletoRequestDTO {

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    private String barcode;
}
