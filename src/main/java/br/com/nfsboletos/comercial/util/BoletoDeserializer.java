package br.com.nfsboletos.comercial.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nfsboletos.comercial.model.Boleto;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;	

public class BoletoDeserializer implements Deserializer<Boleto> {

    @Override
    public Boleto deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        Boleto boleto = null;
        try {
            boleto = objectMapper.readValue(data, Boleto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boleto;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }
}