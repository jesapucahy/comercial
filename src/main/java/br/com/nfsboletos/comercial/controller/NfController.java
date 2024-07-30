package br.com.nfsboletos.comercial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nfsboletos.comercial.model.NotaFiscal;
import br.com.nfsboletos.comercial.service.NfService;

@RestController
@RequestMapping("/api/nf")
public class NfController {

    @Autowired
    private NfService nfService;

    @PostMapping
    public ResponseEntity<String> receiveNf(@RequestBody NotaFiscal notaFiscal) {
        nfService.processNf(notaFiscal);
        return ResponseEntity.ok("Nota Fiscal recebida com sucesso!");
    }
}