package br.com.nfsboletos.comercial.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.nfsboletos.comercial.model.Boleto;

@Component
public class BoletoListener {

	@KafkaListener(topics = "boletos", groupId = "grupo_boletos")
    public void listen(Boleto boleto) {
        processarBoleto(boleto);
        
        enviarBoletoParaCobranca(boleto);
    }

    private void processarBoleto(Boleto boleto) {
        System.out.println("Processando boleto: " + boleto);
    }

    private void enviarBoletoParaCobranca(Boleto boleto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://microservico-cobranca/api/cobranca"; // URL teórico do microserviço de cobrança
        restTemplate.postForObject(url, boleto, Void.class);
        System.out.println("Boleto enviado para o microserviço de cobrança: " + boleto);
    }
}