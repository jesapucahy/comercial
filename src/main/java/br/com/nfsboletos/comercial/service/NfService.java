package br.com.nfsboletos.comercial.service;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.nfsboletos.comercial.model.Boleto;
import br.com.nfsboletos.comercial.model.Cliente;
import br.com.nfsboletos.comercial.model.NotaFiscal;
import br.com.nfsboletos.comercial.repository.NfRepository;

@Service
public class NfService {

	@Autowired
    private NfRepository nfRepository;

    @Autowired
    private KafkaTemplate<String, Boleto> kafkaTemplate;
    
    @Autowired
    private RestTemplate restTemplate;

    public void processNf(NotaFiscal notaFiscal) {
        boolean isValid = validarNotaFiscal(notaFiscal);
        if (!isValid) {
            throw new IllegalArgumentException("Nota fiscal inválida");
        }

        String clienteUrl = "http://microservico-clientes/api/clientes/" + notaFiscal.getClienteId();
        Cliente cliente = null;
        try {
        	cliente = restTemplate.getForObject(clienteUrl, Cliente.class);
        }catch(Exception ex) {
        	ex.printStackTrace();
        	cliente = null;
        }
        if (cliente == null) {
        	System.out.println("Cliente não encontrado");
        }else {
        	System.out.println("Dados do cliente: " + cliente);
        }
        
        nfRepository.save(notaFiscal);

        Boleto boleto = new Boleto(notaFiscal.getId(), "Informações do boleto");
        kafkaTemplate.send("boletos", boleto);
    }

    private boolean validarNotaFiscal(NotaFiscal notaFiscal) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.gov.br/validarNfe";
        //Boolean isValid = restTemplate.postForObject(url, notaFiscal, Boolean.class); // Validação da nota, em desenvolvimento real verificaria se a URL está ok e teria que passar o certificado digital.
        Boolean isValid = true; //Linha acima seria um passo da validação real.
        return isValid != null && isValid;
    }
}