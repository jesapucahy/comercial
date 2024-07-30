package br.com.nfsboletos.comercial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long notaFiscalId;
    private String descricao;

    public Boleto() {}

    public Boleto(Long notaFiscalId, String descricao) {
        this.notaFiscalId = notaFiscalId;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNotaFiscalId() {
        return notaFiscalId;
    }

    public void setNotaFiscalId(Long notaFiscalId) {
        this.notaFiscalId = notaFiscalId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Boleto{" +
                "id=" + id +
                ", notaFiscalId=" + notaFiscalId +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}