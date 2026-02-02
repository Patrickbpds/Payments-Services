package EventPay.Api_Boleto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import EventPay.Api_Boleto.entity.enums.SituationBoleto;

@Entity
@Table(name = "boletos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoletoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "situation_boleto")
    private SituationBoleto situationBoleto;

    @Column(name = "date_creation")
    private LocalDateTime creationDate;

    @Column(name = "date_update")
    private LocalDateTime updateDate;
}
