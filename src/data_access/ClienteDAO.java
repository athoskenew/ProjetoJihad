package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Produto;
import util.Mensagem;

public class ClienteDAO{

    /*
    *Método utilizado para retornar uma lista com todos os clientes que estão
    *Cadastrados no banco
     */
    public List<Cliente> listaCiente() {
        List<Cliente> listaCliente = new ArrayList<>();

        String sql = "Select * FROM cliente";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            ResultSet resultado = comandoSQL.executeQuery();

            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(resultado.getLong("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setRg(resultado.getString("rg"));
                cliente.setNumeroTelefone(resultado.getString("numerotelefone"));
                cliente.setDataNascimento(resultado.getString("datanascimento"));
                cliente.setDataCompra(resultado.getString("datacompra"));
                cliente.setDebito(resultado.getDouble("debito"));
                cliente.setSituacao(resultado.getString("situacao"));
                listaCliente.add(cliente);
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCliente;
    }

    /*
    *Método utilizado para salvar clientes no banco
     */
    public boolean salvarCliente(Cliente cliente) {
        Connection conexao = new ConnectionFactory().getConnection();

        String sql = "INSERT INTO cliente(nome, cpf, rg, numerotelefone, datanascimento, email, enderecorua, endereconumero, enderecobairro, enderecocidade) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, cliente.getNome());
            comandoSQL.setString(2, cliente.getCpf());
            comandoSQL.setString(3, cliente.getRg());
            comandoSQL.setString(4, cliente.getNumeroTelefone());
            comandoSQL.setString(5, cliente.getDataNascimento());
            comandoSQL.setString(6, cliente.getEmail());
            comandoSQL.setString(7, cliente.getEnderecoRua());
            comandoSQL.setString(8, cliente.getEnderecoNumero());
            comandoSQL.setString(9, cliente.getEnderecoBairro());
            comandoSQL.setString(10, cliente.getEnderecoCidade());
            comandoSQL.execute();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            Mensagem mensagens = new Mensagem();
            String erro = String.valueOf(ex);
            mensagens.mensagemErro("Erro ao salvar dados" + " /n" + erro);
            return false;
        }
    }

    /*
    *Método utilizado para editar clientes no banco
     */
    public boolean editarCliente(Cliente cliente) {
        Connection conexao = new ConnectionFactory().getConnection();

        String sql = "UPDATE cliente SET nome=?, cpf=?, rg=?, numerotelefone=?, datanascimento=?, email=?, enderecorua=?, endereconumero=?, enderecobairro=?, enderecocidade=? where id=?";
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, cliente.getNome());
            comandoSQL.setString(2, cliente.getCpf());
            comandoSQL.setString(3, cliente.getRg());
            comandoSQL.setString(4, cliente.getNumeroTelefone());
            comandoSQL.setString(5, cliente.getDataNascimento());
            comandoSQL.setString(6, cliente.getEmail());
            comandoSQL.setString(7, cliente.getEnderecoRua());
            comandoSQL.setString(8, cliente.getEnderecoNumero());
            comandoSQL.setString(9, cliente.getEnderecoBairro());
            comandoSQL.setString(10, cliente.getEnderecoCidade());
            System.out.println("O codigo é " + cliente.getCodigo());
            comandoSQL.setLong(11, cliente.getCodigo());
            comandoSQL.execute();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            Mensagem mensagens = new Mensagem();
            String erro = String.valueOf(ex);
            mensagens.mensagemErro("Erro ao salvar dados" + " /n" + erro);
            return false;
        }
    }

    /*
    *Método utilizado para remover clientes do banco
     */
    public void apagaCliente(Long idCliente) {
        Connection conexao = new ConnectionFactory().getConnection();
        Mensagem mensagem = new Mensagem();

        String sql = "DELETE FROM cliente WHERE id=?";
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, idCliente);
            comandoSQL.executeUpdate();
            mensagem.mensagemInforma("Cliente excluido");
            conexao.close();
        } catch (SQLException ex) {
            mensagem.mensagemErro("Erro ao excluir o cliente");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    *Método utilizado para realizar uma pesquisa pelo nome de um cliente
     */
    public List<Cliente> pesquisaCliente(String nomeCliente) {
        List<Cliente> listaCliente = new ArrayList<>();

        String sql = "select * from cliente where nome like '%" + nomeCliente + "%'";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            ResultSet resultado = comandoSQL.executeQuery();

            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(resultado.getLong("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setRg(resultado.getString("rg"));
                cliente.setNumeroTelefone(resultado.getString("numerotelefone"));
                cliente.setDataNascimento(resultado.getString("datanascimento"));
                cliente.setDataCompra(resultado.getString("datacompra"));
                cliente.setDebito(resultado.getDouble("debito"));
                cliente.setSituacao(resultado.getString("situacao"));
                listaCliente.add(cliente);
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCliente;
    }

    /*
    *Método utilizado para realizar uma pesquisa pelo id de um cliente
     */
    public Cliente pesquisaCliente(Long idCliente) {
        Cliente cliente = new Cliente();

        String sql = "select * from cliente where id = " + idCliente + "";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            ResultSet resultado = comandoSQL.executeQuery();

            while (resultado.next()) {
                cliente.setCodigo(resultado.getLong("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setRg(resultado.getString("rg"));
                cliente.setNumeroTelefone(resultado.getString("numerotelefone"));
                cliente.setDataNascimento(resultado.getString("datanascimento"));
                cliente.setNumeroTelefone(resultado.getString("numerotelefone"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setEnderecoRua(resultado.getString("enderecorua"));
                cliente.setEnderecoNumero(resultado.getString("endereconumero"));
                cliente.setEnderecoBairro(resultado.getString("enderecobairro"));
                cliente.setEnderecoCidade(resultado.getString("enderecocidade"));
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public Long pesquisaIDCliente(String cpfCliente) {
        Cliente cliente = new Cliente();
        Long id = 0L;
        String sql = "select id from cliente where cpf=?";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, cpfCliente);
            ResultSet resultado = comandoSQL.executeQuery();

            while (resultado.next()) {
                /*cliente.setCodigo(resultado.getLong("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setRg(resultado.getString("rg"));
                cliente.setNumeroTelefone(resultado.getString("numerotelefone"));
                cliente.setDataNascimento(resultado.getString("datanascimento"));
                cliente.setNumeroTelefone(resultado.getString("numerotelefone"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setEnderecoRua(resultado.getString("enderecorua"));
                cliente.setEnderecoNumero(resultado.getInt("endereconumero"));
                cliente.setEnderecoBairro(resultado.getString("enderecobairro"));
                cliente.setEnderecoCidade(resultado.getString("enderecocidade"));
                 */
                id = resultado.getLong("id");
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public boolean verificaSituacaoCliente(Long idCliente){
        
        boolean prosseguir = true;
        String sql = "SELECT situacao FROM venda WHERE idcliente = ?";
        Connection conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, idCliente);
            ResultSet resultado = comandoSQL.executeQuery();
            
            while(resultado.next()){
                prosseguir = false;
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prosseguir;
    }
}
