package org.example.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, Product> kafkaTemplate;

    public void send(Product product) {

        ListenableFutureCallback<SendResult<String, Product>> futureCallback = new ListenableFutureCallback<SendResult<String, Product>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Failed to send event {}", throwable.getCause());
                throw new RuntimeException(throwable);
            }

            @Override
            public void onSuccess(SendResult<String, Product> sendResult) {
                log.info("Success to send event {}", sendResult.getRecordMetadata().toString());
            }
        };
        log.info("Payload enviado: {}", product);
        kafkaTemplate.send(topicName, product.getHashId(), product).addCallback(futureCallback);
    }

}
