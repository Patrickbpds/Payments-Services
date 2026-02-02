package EventPay.Api_Boleto.service;

import EventPay.Api_Boleto.controller.exception.ApplicationException;
import EventPay.Api_Boleto.controller.exception.NotFoundException;
import EventPay.Api_Boleto.dto.BoletoDTO;
import EventPay.Api_Boleto.entity.BoletoEntity;
import EventPay.Api_Boleto.entity.enums.SituationBoleto;
import EventPay.Api_Boleto.mapper.BoletoMapper;
import EventPay.Api_Boleto.service.kafka.BoletoProducer;
import EventPay.Api_Boleto.repository.BoletoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoletoService {

    private final BoletoRepository boletoRepository;
    private final BoletoProducer boletoProducer;

    public BoletoService(BoletoRepository boletoRepository, BoletoProducer boletoProducer) {
        this.boletoRepository = boletoRepository;
        this.boletoProducer = boletoProducer;
    }

    public BoletoDTO save(String barcode) {
        var boletoOptional = boletoRepository.findByBarcode(barcode);
        if (boletoOptional.isPresent()) {
            throw new ApplicationException("There is already a payment request for this invoice.");
        }

        var boletoEntity = BoletoEntity.builder()
                .barcode(barcode)
                .situationBoleto(SituationBoleto.INITIALIZED)
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        boletoRepository.save(boletoEntity);
        boletoProducer.sendMessage(BoletoMapper.toAvro(boletoEntity));
        return BoletoMapper.toDTO(boletoEntity);
    }

    public BoletoDTO findBoletoByBarcode(String barcode) {
        return BoletoMapper.toDTO(recoverBoleto(barcode));
    }

    private BoletoEntity recoverBoleto(String barcode) {
        return boletoRepository.findByBarcode(barcode)
                .orElseThrow(() -> new NotFoundException("Invoice not found"));
    }

    public void update(BoletoEntity boleto) {
        var boletoPresent = recoverBoleto(boleto.getBarcode());

        boletoPresent.setSituationBoleto(boleto.getSituationBoleto());
        boletoPresent.setUpdateDate(LocalDateTime.now());
        boletoRepository.save(boletoPresent);
    }


}
