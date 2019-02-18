
package model;

import java.util.List;

public class PagarVenda {
   
    private Long id;
    private double valorTotal;
    private int numeroParcelas;//C
    private String situacao;//C
    private String dataCompra;//C
    private Double valorParcela;
    private String dataVencimento;
    private int numeroParcelasPagas;
    private int numeroDaParcela;
    private List<Parcela> listaParcelas;
    private int numeroDeParcelasParaPagarAgora;
    private double debito;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(Double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getNumeroParcelasPagas() {
        return numeroParcelasPagas;
    }

    public void setNumeroParcelasPagas(int numeroParcelasPagas) {
        this.numeroParcelasPagas = numeroParcelasPagas;
    }

    public int getNumeroDaParcela() {
        return numeroDaParcela;
    }

    public void setNumeroDaParcela(int numeroDaParcela) {
        this.numeroDaParcela = numeroDaParcela;
    }

    public List<Parcela> getListaParcelas() {
        return listaParcelas;
    }

    public void setListaParcelas(List<Parcela> listaParcelas) {
        this.listaParcelas = listaParcelas;
    }

    public int getNumeroDeParcelasParaPagarAgora() {
        return numeroDeParcelasParaPagarAgora;
    }

    public void setNumeroDeParcelasParaPagarAgora(int numeroDeParcelasParaPagarAgora) {
        this.numeroDeParcelasParaPagarAgora = numeroDeParcelasParaPagarAgora;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    
}
