package br.com.nfsboletos.comercial.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nfsboletos.comercial.model.Boleto;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

public class BoletoSerializer implements Serializer<Boleto> {

    @Override
    public byte[] serialize(String topic, Boleto data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }
}