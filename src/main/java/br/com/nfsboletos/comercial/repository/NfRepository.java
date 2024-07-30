package br.com.nfsboletos.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.nfsboletos.comercial.model.NotaFiscal;

@Repository
public interface NfRepository extends JpaRepository<NotaFiscal, Long> {
}