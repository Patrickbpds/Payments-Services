package EventPay.Api_Boleto.service.kafka;

import EventPay.Api_Boleto.avro.Boleto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BoletoProducer {

    @Value("${app.kafka.topic.boleto}")
    private String topic;

    private final KafkaTemplate<String, Boleto> kafkaTemplate;

    public BoletoProducer(KafkaTemplate<String, Boleto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Boleto boleto) {
        kafkaTemplate.send(topic, getKey(boleto), boleto);
    }

    private String getKey(Boleto boleto) {
        if (boleto.getBarcode().toString().startsWith("2")) {
            return "key1";
        }
        return "key2";
    }
}
