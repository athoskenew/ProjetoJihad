
package data_access;

import controller.FXML_Tela_InicialController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Produto;
import model.QuantidadeProdutoVendido;
import util.Mensagem;


public class ProdutoDAO{
    
    /*
    *Método utilizado para retornar uma lista com todos os clientes que estão
    *Cadastrados no banco
    */    
    public List<Produto> listaProduto(){
        List<Produto> listaProduto = new ArrayList<>();
           
        /*********************  Iniciando Busca no Banco  *******************/
        String sql = "Select * FROM produto";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            ResultSet resultado = comandoSQL.executeQuery();
            
            while(resultado.next()){
                Produto produto = new Produto();
                produto.setCodProduto(resultado.getLong("id"));
                produto.setProduto(resultado.getString("produto"));
                produto.setFornecedor(resultado.getString("fornecedor"));
                produto.setMarca(resultado.getString("marca"));
                produto.setQuantidadeProduto(resultado.getInt("quantidade"));
                produto.setValorCustoProduto(resultado.getDouble("valorcusto"));
                produto.setValorVendaProduto(resultado.getDouble("valorvenda"));
                listaProduto.add(produto);
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProduto;
    }
     
    /*
    *Método utilizado para realizar uma pesquisa pelo nome de um produto no banco
    */
    public List<Produto> pesquisaProduto(String nomeProduto){
        List<Produto> listaProduto = new ArrayList<>();
           
        String sql = "select * from produto where produto like '%"+nomeProduto+"%'";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            ResultSet resultado = comandoSQL.executeQuery();
            
            while(resultado.next()){
                Produto produto = new Produto();
                produto.setCodProduto(resultado.getLong("id"));
                produto.setProduto(resultado.getString("produto"));
                produto.setFornecedor(resultado.getString("fornecedor"));
                produto.setMarca(resultado.getString("marca"));
                produto.setQuantidadeProduto(resultado.getInt("quantidade"));
                produto.setValorCustoProduto(resultado.getDouble("valorcusto"));
                produto.setValorVendaProduto(resultado.getDouble("valorvenda"));
                listaProduto.add(produto);
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProduto;
    }
    
    /*
    *Método utilizado para realizar uma pesquisa pelo id de um produto no banco
    */
    public Produto pesquisaProduto(Long idProduto){
        Produto produto = new Produto();
           
        String sql = "select * from produto where id = ?";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, idProduto);
            ResultSet resultado = comandoSQL.executeQuery();
            
            while(resultado.next()){
                produto.setCodProduto(resultado.getLong("id"));
                produto.setProduto(resultado.getString("produto"));
                produto.setFornecedor(resultado.getString("fornecedor"));
                produto.setMarca(resultado.getString("marca"));
                produto.setQuantidadeProduto(resultado.getInt("quantidade"));
                produto.setValorCustoProduto(resultado.getDouble("valorcusto"));
                produto.setValorVendaProduto(resultado.getDouble("valorvenda"));
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produto;
    }
   
    /*
    *Método utilizado para salvar um produto no banco
    */
     public boolean salvarProduto(Produto produto){
        Connection conexao = new ConnectionFactory().getConnection();
       
        String sql = "INSERT INTO produto(produto, fornecedor, marca, quantidade, valorcusto, valorvenda) VALUES(?,?,?,?,?,?)";
       try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, produto.getProduto());
            comandoSQL.setString(2, produto.getFornecedor());
            comandoSQL.setString(3, produto.getMarca());
            comandoSQL.setInt(4, produto.getQuantidadeProduto());
            comandoSQL.setDouble(5, produto.getValorCustoProduto());
            comandoSQL.setDouble(6, produto.getValorVendaProduto());
            comandoSQL.execute();
            conexao.close();
            return true;
       } catch (SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            Mensagem mensagens = new Mensagem();
            String erro = String.valueOf(ex);
            mensagens.mensagemErro("Erro ao salvar dados"+" /n"+erro); 
            return false;
        }
    }
     
    /*
    *Método utilizado para editar um produto no banco
    */
     public boolean editarProduto(Produto produto){
        Connection conexao = new ConnectionFactory().getConnection();
       
        String sql = "UPDATE produto SET produto=?, fornecedor=?, marca=?, quantidade=?, valorcusto=?, valorvenda=? WHERE id=?";
       try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, produto.getProduto());
            comandoSQL.setString(2, produto.getFornecedor());
            comandoSQL.setString(3, produto.getMarca());
            comandoSQL.setInt(4, produto.getQuantidadeProduto());
            comandoSQL.setDouble(5, produto.getValorCustoProduto());
            comandoSQL.setDouble(6, produto.getValorVendaProduto());
            comandoSQL.setLong(7, produto.getCodProduto());
            comandoSQL.execute();
            conexao.close();
            return true;
       } catch (SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            Mensagem mensagens = new Mensagem();
            String erro = String.valueOf(ex);
            mensagens.mensagemErro("Erro ao editar os dados"+" /n"+erro); 
            return false;
        }
    }
    
    /*
    *Método utilizado para apagar um produto do banco
    */
     public void apagaProduto(Long idProduto){
        Connection conexao = new ConnectionFactory().getConnection();
        String sql = "DELETE FROM produto WHERE id=?";
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, idProduto);
            comandoSQL.executeUpdate();
            conexao.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
     }
     
    /*
    *Método utilizado para atualizar a quantidade de uma lista de produtos no banco. Chamado sempre que
    *uma venda é finalizada
    */
     public void atualizaQuantidade(List<QuantidadeProdutoVendido> quantidadeProdutoVendido){
        Connection conexao = new ConnectionFactory().getConnection();
       
        int quantidade = 0; 
        String sql = "UPDATE produto SET quantidade=? WHERE id=?";
        String sql2 = "SELECT quantidade FROM produto WHERE id=?";

        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            PreparedStatement comandoSQL2 = conexao.prepareStatement(sql2);
            
            for(QuantidadeProdutoVendido produto : quantidadeProdutoVendido){
                
                comandoSQL2.setLong(1, produto.getId());
                ResultSet resultado = comandoSQL2.executeQuery();
                
                while(resultado.next()){
                    quantidade = resultado.getInt("quantidade");
                }
                
                comandoSQL.setInt(1, quantidade - produto.getQuantidade());
                comandoSQL.setLong(2, produto.getId());
                comandoSQL.execute();
            }
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro: "+String.valueOf(ex));        }
         
     }
     
     public static void main(String[] args) {
        
        Produto produto = new Produto();
	produto.setProduto("arroz");
        produto.setFornecedor("jose");
        produto.setMarca("sadia");
        produto.setQuantidadeProduto(Integer.parseInt("12"));
        produto.setValorCustoProduto(22);
        produto.setValorVendaProduto(22);
        
     
        ProdutoDAO produtoDao = new ProdutoDAO();
        produtoDao.salvarProduto(produto);
      
	
}
}

