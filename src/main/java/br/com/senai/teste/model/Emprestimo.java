package br.com.senai.teste.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;


@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LocalDate dataPrevistaDevolucao;
    private static final BigDecimal MULTA_POR_DIA = new BigDecimal("2.00");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;


    public Emprestimo() {
    }

    public Emprestimo(Aluno aluno, Livro livro, LocalDate dataEmprestimo) {
        this.aluno = aluno;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
    }

    public Integer getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    } 

    public String getSituacao() {
        if (dataDevolucao != null) {

            return "Devolvido";
        } else if (dataPrevistaDevolucao == null) {

            return "Sem_Previsão";
        } else if (dataPrevistaDevolucao.isBefore(LocalDate.now())) {

            return "Atrasado";
        } else {

            return "Ativo";
        }
    }

    public long getDiasAtraso() {
        
        LocalDate dataFinal;

        if(dataPrevistaDevolucao == null) {
            return 0;
        }

        if (dataDevolucao == null) {
            dataFinal = LocalDate.now();
        } else {
            dataFinal = dataDevolucao;
        }

        return ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataFinal);
    } 

    public BigDecimal getValorMulta() {
        return MULTA_POR_DIA.multiply(BigDecimal.valueOf(getDiasAtraso()));
    }
}
