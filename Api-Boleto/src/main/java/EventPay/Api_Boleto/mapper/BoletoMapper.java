package EventPay.Api_Boleto.mapper;

import EventPay.Api_Boleto.dto.BoletoDTO;
import EventPay.Api_Boleto.entity.BoletoEntity;
import EventPay.Api_Boleto.entity.enums.SituationBoleto;
import EventPay.Api_Boleto.avro.Boleto;


public class BoletoMapper {

    public static BoletoDTO toDTO(BoletoEntity boleto) {
        return BoletoDTO.builder()
                .barcode(boleto.getBarcode())
                .situationBoleto(boleto.getSituationBoleto())
                .creationDate(boleto.getCreationDate())
                .updateDate(boleto.getUpdateDate())
                .build();
    }

    public static Boleto toAvro(BoletoEntity boleto) {
        return Boleto.newBuilder()
                .setBarcode(boleto.getBarcode())
                .setSituationBoleto(boleto.getSituationBoleto().ordinal())
                .build();
    }

    public static BoletoEntity toEntity(Boleto boleto) {
        return BoletoEntity.builder()
                .barcode(boleto.getBarcode().toString())
                .situationBoleto(SituationBoleto.values()[boleto.getSituationBoleto()])
                .build();
    }
}
