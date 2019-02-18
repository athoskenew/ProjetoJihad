
package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConfiguraUsuario;
import model.Login_Usuario;

public class LoginDAO {
    
    
    public Login_Usuario login(String usuario){
        
        String sql = "select * from login where usuario = ? ";
        Login_Usuario novoLogin = new Login_Usuario();
        
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, usuario);
            
            ResultSet resultado = comandoSQL.executeQuery();
            
            while(resultado.next()){
                novoLogin.setLogin(resultado.getString("usuario"));
                novoLogin.setSenha(resultado.getString("senha"));
            }
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return novoLogin;
    }
    
    public ConfiguraUsuario buscaDadosEmpresa(){
        ConfiguraUsuario usuario = new ConfiguraUsuario();
    
    String sql = "SELECT * FROM empresa WHERE id=?";
    
    Connection conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, 1);
            ResultSet resultado = comandoSQL.executeQuery();
            while(resultado.next()){
                usuario.setNomeEmpresa(resultado.getString("nome"));
                usuario.setSenhaAtual(resultado.getString("senha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return usuario;
}
    
    public void configuracaoUsuario(ConfiguraUsuario usuario){
        
    }
}
