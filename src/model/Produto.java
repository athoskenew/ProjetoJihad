
package model;


public class Produto{

   private Long codProduto;
   private String produto;
   private String fornecedor;
   private String marca;
   private int quantidadeProduto;
   private double valorCustoProduto;
   private double valorVendaProduto;

    public Long getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Long codProduto) {
        this.codProduto = codProduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public double getValorCustoProduto() {
        return valorCustoProduto;
    }

    public void setValorCustoProduto(double valorCustoProduto) {
        this.valorCustoProduto = valorCustoProduto;
    }

    public double getValorVendaProduto() {
        return valorVendaProduto;
    }

    public void setValorVendaProduto(double valorVendaProduto) {
        this.valorVendaProduto = valorVendaProduto;
    }
    
     
   
}

