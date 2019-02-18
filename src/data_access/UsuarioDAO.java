package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import util.Mensagem;

public class UsuarioDAO {

    /*
    *Método utilizado para retornar uma lista com todos os usuario que estão
    *Cadastrados no banco
     */
    public List<Usuario> listaUsuario() {
        List<Usuario> listaUsuario = new ArrayList<>();

        String sql = "Select * FROM usuario";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            ResultSet resultado = comandoSQL.executeQuery();

            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setCodigo(resultado.getLong("id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setFuncao(resultado.getString("funcao"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setDataEntrada(resultado.getString("dataentrada"));
                usuario.setDataSaida(resultado.getString("datasaida"));
                usuario.setCpf(resultado.getString("cpf"));
                usuario.setRg(resultado.getString("rg"));
                usuario.setDataNascimento(resultado.getString("datanascimento"));
                usuario.setNumeroTelefone(resultado.getString("numerotelefone"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setEnderecoRua(resultado.getString("enderecorua"));
                usuario.setEnderecoNumero(resultado.getString("endereconumero"));
                usuario.setEnderecoBairro(resultado.getString("enderecobairro"));
                usuario.setEnderecoCidade(resultado.getString("enderecocidade"));
               
                listaUsuario.add(usuario);
            }
            conexao.close();
        } catch (SQLException ex) {
            Mensagem mensagem = new Mensagem();
            mensagem.mensagemErro("Erro");
            System.out.println(ex);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuario;
    }

    /*
    *Método utilizado para salvar usuario no banco
     */
    public boolean salvarUsuario(Usuario usuario) {
        Connection conexao = new ConnectionFactory().getConnection();

        String sql = "INSERT INTO usuario(nome, funcao, login, senha, dataentrada, datasaida, cpf, rg, datanascimento, numerotelefone, email, enderecorua, endereconumero, enderecobairro, enderecocidade) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, usuario.getNome());
            comandoSQL.setString(2, usuario.getFuncao());
            comandoSQL.setString(3, usuario.getLogin());
            comandoSQL.setString(4, usuario.getSenha());
            comandoSQL.setString(5, usuario.getDataEntrada());
            comandoSQL.setString(6, usuario.getDataSaida());
            comandoSQL.setString(7, usuario.getCpf());
            comandoSQL.setString(8, usuario.getRg());
            comandoSQL.setString(9, usuario.getDataNascimento());
            comandoSQL.setString(10, usuario.getNumeroTelefone());
            comandoSQL.setString(11, usuario.getEmail());
            comandoSQL.setString(12, usuario.getEnderecoRua());
            comandoSQL.setString(13, usuario.getEnderecoNumero());
            comandoSQL.setString(14, usuario.getEnderecoBairro());
            comandoSQL.setString(15, usuario.getEnderecoCidade());
            comandoSQL.execute();
            conexao.close();
  
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            Mensagem mensagens = new Mensagem();
            String erro = String.valueOf(ex);
            mensagens.mensagemErro("Erro ao salvar dados" + " /n" + erro);
            return false;
        }
    }

    /*
    *Método utilizado para editar usuario no banco
     */
    public boolean editarUsuario(Usuario usuario) {
        Connection conexao = new ConnectionFactory().getConnection();

        String sql = "UPDATE usuario SET nome=?, funcao=?, login=?, senha=?, dataentrada=?, datasaida=?, cpf=?, rg=?, datanascimento=?, numerotelefone=?, email=?, enderecorua=?, endereconumero=?, enderecobairro=?, enderecocidade=? where id=?";
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, usuario.getNome());
            comandoSQL.setString(2, usuario.getFuncao());
            comandoSQL.setString(3, usuario.getLogin());
            comandoSQL.setString(4, usuario.getSenha());
            comandoSQL.setString(5, usuario.getDataEntrada());
            comandoSQL.setString(6, usuario.getDataSaida());
            comandoSQL.setString(7, usuario.getCpf());
            comandoSQL.setString(8, usuario.getRg());
            comandoSQL.setString(9, usuario.getDataNascimento());
            comandoSQL.setString(10, usuario.getNumeroTelefone());
            comandoSQL.setString(11, usuario.getEmail());
            comandoSQL.setString(12, usuario.getEnderecoRua());
            comandoSQL.setString(13, usuario.getEnderecoNumero());
            comandoSQL.setString(14, usuario.getEnderecoBairro());
            comandoSQL.setString(15, usuario.getEnderecoCidade());
            comandoSQL.setLong(16, usuario.getCodigo());
            comandoSQL.execute();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            Mensagem mensagens = new Mensagem();
            String erro = String.valueOf(ex);
            mensagens.mensagemErro("Erro ao salvar dados" + " /n" + erro);
            return false;
        }
    }

    /*
    *Método utilizado para remover usuario do banco
     */
    public void apagaUsuario(Long idUsuario) {
        Connection conexao = new ConnectionFactory().getConnection();
        Mensagem mensagem = new Mensagem();

        String sql = "DELETE FROM usuario WHERE id=?";
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, idUsuario);
            comandoSQL.executeUpdate();
            mensagem.mensagemInforma("Usuario excluido");
            conexao.close();
        } catch (SQLException ex) {
            mensagem.mensagemErro("Erro ao excluir o usuario");
            System.out.println(ex);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    *Método utilizado para realizar uma pesquisa pelo nome de um usuario
     */
    public List<Usuario> pesquisaUsuario(String nomeUsuario) {
        List<Usuario> listaUsuario = new ArrayList<>();

        String sql = "select * from usuario where nome like '%" + nomeUsuario + "%'";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            ResultSet resultado = comandoSQL.executeQuery();

            while (resultado.next()) {
                Usuario usuario = new Usuario();
                              
                usuario.setCodigo(resultado.getLong("id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setFuncao(resultado.getString("funcao"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setDataEntrada(resultado.getString("dataentrada"));
                usuario.setDataSaida(resultado.getString("datasaida"));
                usuario.setCpf(resultado.getString("cpf"));
                usuario.setRg(resultado.getString("rg"));
                usuario.setDataNascimento(resultado.getString("datanascimento"));
                usuario.setNumeroTelefone(resultado.getString("numerotelefone"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setEnderecoRua(resultado.getString("enderecorua"));
                usuario.setEnderecoNumero(resultado.getString("endereconumero"));
                usuario.setEnderecoBairro(resultado.getString("enderecobairro"));
                usuario.setEnderecoCidade(resultado.getString("enderecocidade"));
                
                listaUsuario.add(usuario);
            }
            conexao.close();
        } catch (SQLException ex) {
            Mensagem mensagem = new Mensagem();
            mensagem.mensagemErro("Erro");
            System.out.println(ex);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuario;
    }
    
    /*
    *Método utilizado para realizar uma pesquisa pelo id de um usuario
     */
    public Usuario pesquisaUsuario(Long idUsuario) {
        Usuario usuario = new Usuario();

        String sql = "select * from usuario where id = " + idUsuario + "";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            ResultSet resultado = comandoSQL.executeQuery();

            while (resultado.next()) {
                
                usuario.setCodigo(resultado.getLong("id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setFuncao(resultado.getString("funcao"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setDataEntrada(resultado.getString("dataentrada"));
                usuario.setDataSaida(resultado.getString("datasaida"));
                usuario.setCpf(resultado.getString("cpf"));
                usuario.setRg(resultado.getString("rg"));
                usuario.setDataNascimento(resultado.getString("datanascimento"));
                usuario.setNumeroTelefone(resultado.getString("numerotelefone"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setEnderecoRua(resultado.getString("enderecorua"));
                usuario.setEnderecoNumero(resultado.getString("endereconumero"));
                usuario.setEnderecoBairro(resultado.getString("enderecobairro"));
                usuario.setEnderecoCidade(resultado.getString("enderecocidade"));
                
            }
            conexao.close();
        } catch (SQLException ex) {
            Mensagem mensagem = new Mensagem();
            mensagem.mensagemErro("Erro");
            System.out.println(ex);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

     /*
    *Método utilizado para realizar uma pesquisa pelo cpf de um usuario
     */
    public Long pesquisaIDUsuario(String cpfUsuario) {
        Usuario usuario = new Usuario();
        Long id = 0L;
        String sql = "select id from usuario where cpf=?";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, cpfUsuario);
            ResultSet resultado = comandoSQL.executeQuery();

            while (resultado.next()) {
              id = resultado.getLong("id");
            }
            conexao.close();
        } catch (SQLException ex) {
            Mensagem mensagem = new Mensagem();
            mensagem.mensagemErro("Erro");
            System.out.println(ex);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public static void main(String[] args) {
        Usuario usuario = new Usuario();
      

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.salvarUsuario(usuario);
    }
}
