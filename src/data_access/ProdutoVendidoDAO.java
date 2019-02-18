
package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProdutoVendido;
import util.Mensagem;

public class ProdutoVendidoDAO {
    
    public List<ProdutoVendido> listarProdutosVendidos(Long idVenda){
        List<ProdutoVendido> produtosVendidos = new ArrayList<>();
        
        String sql = "SELECT * FROM produtovendido WHERE idvenda=?";
        
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, idVenda);
            
            ResultSet resultado = comandoSQL.executeQuery();
            
            while(resultado.next()){
                ProdutoVendido produtoVendidoAdiciona = new ProdutoVendido();
                
                produtoVendidoAdiciona.setIdVenda(resultado.getLong("idvenda"));
                produtoVendidoAdiciona.setNomeProduto(resultado.getString("nomeproduto"));
                produtoVendidoAdiciona.setValorVendaProduto(resultado.getDouble("valorvenda"));
                produtoVendidoAdiciona.setQuantidade(resultado.getInt("quantidade"));
                produtoVendidoAdiciona.setTotal(resultado.getDouble("total"));
                produtoVendidoAdiciona.setCodProduto(resultado.getLong("idproduto"));
                produtosVendidos.add(produtoVendidoAdiciona);
            }
            conexao.close();
        } catch (SQLException ex) {
            Mensagem mensagem = new Mensagem();
            mensagem.mensagemErro("Erro ao listar produto vendido");
        }
        
        return produtosVendidos;
    }
        public void fazNada(){
        
    }
    public void salvarProdutoVendido(Long idVenda ,List<ProdutoVendido> listaProdutoVendido){
        String sql = "INSERT INTO produtovendido (idvenda, nomeproduto, valorvenda, quantidade, total, idproduto) VALUES (?,?,?,?,?,?)";
        
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            for(ProdutoVendido produtoVendido : listaProdutoVendido){
                comandoSQL.setLong(1, idVenda);
                comandoSQL.setString(2, produtoVendido.getNomeProduto());
                comandoSQL.setDouble(3, produtoVendido.getValorVendaProduto());
                comandoSQL.setInt(4, produtoVendido.getQuantidade());
                comandoSQL.setDouble(5, produtoVendido.getTotal());
                comandoSQL.setLong(6, produtoVendido.getCodProduto());
                comandoSQL.execute();
            }
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Deu erro aqui");
            //Mensagem mensagem = new Mensagem();
           // mensagem.mensagemErro("Erro ao salvar produto vendido no banco");
        }
    }
    
    public void excluirProdutoVendido(Long idVenda){
        String sql = "DELETE FROM produtovendido WHERE idvenda=?";
        
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, idVenda);
            comandoSQL.executeUpdate();
            
        } catch (SQLException ex) {
            Mensagem mensagem = new Mensagem();
            mensagem.mensagemErro("Erro ao apagar produtos vendidos");
        }
        
    }
}
