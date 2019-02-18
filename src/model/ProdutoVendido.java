
package model;


public class ProdutoVendido {

        private int idProdutoNaVenda;
        private Long codProduto;
        private String nomeProduto;
        private int quantidade;
        private Double valorVendaProduto;
        private Double total;
        private Long idVenda;

    public int getIdProdutoNaVenda() {
        return idProdutoNaVenda;
    }

    public void setIdProdutoNaVenda(int idProdutoNaVenda) {
        this.idProdutoNaVenda = idProdutoNaVenda;
    }
    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Long getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Long codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorVendaProduto() {
        return valorVendaProduto;
    }

    public void setValorVendaProduto(Double valorVendaProduto) {
        this.valorVendaProduto = valorVendaProduto;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
        

}
