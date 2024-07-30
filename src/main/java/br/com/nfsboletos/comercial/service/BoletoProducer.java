package br.com.nfsboletos.comercial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.nfsboletos.comercial.model.Boleto;

@Service
public class BoletoProducer {

    @Autowired
    private KafkaTemplate<String, Boleto> kafkaTemplate;

    private static final String TOPIC = "boletos";

    public void sendBoleto(Boleto boleto) {
        this.kafkaTemplate.send(TOPIC, boleto);
    }
}