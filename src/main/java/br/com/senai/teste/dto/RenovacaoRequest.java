package br.com.senai.teste.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class RenovacaoRequest {
    
    @NotNull (message = "A data prevista de devolução é obrigatória")
    @Future (message = "A data prevista de devolução deve ser uma data futura")
    private LocalDate novaDataPrevista;

    public RenovacaoRequest() {
    }

    public LocalDate getNovaDataPrevista() {
        return novaDataPrevista;
    }

    public void setNovaDataPrevista(LocalDate novaDataPrevista) {
        this.novaDataPrevista = novaDataPrevista;
    }
}
