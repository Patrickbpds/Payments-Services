package EventPay.Api_Boleto.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import EventPay.Api_Boleto.avro.Boleto;
import EventPay.Api_Boleto.mapper.BoletoMapper;
import EventPay.Api_Boleto.service.BoletoService;

@Service
public class NotificationConsumer {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(NotificationConsumer.class);

    private final BoletoService boletoService;

    public NotificationConsumer(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @KafkaListener(topics = "${app.kafka.topic.notification}")
    public void consumer(@Payload Boleto boleto) {
        LOGGER.info("Consuming notification -> {}", boleto);
        boletoService.update(BoletoMapper.toEntity(boleto));
    }
}
