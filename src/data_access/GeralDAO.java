
package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.AnalisaSituacao;

public class GeralDAO {
    public void atualizacaoAutomatica(){
        String sql = "SELECT datavencimento, idvenda FROM parcelavenda WHERE parcelapaga=?";
        String sql2 = "UPDATE venda SET situacao=? WHERE id=?";
        AnalisaSituacao analisa = new AnalisaSituacao();
        Connection conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            PreparedStatement comandoSQL2 = conexao.prepareStatement(sql2);
            comandoSQL.setBoolean(1, false);
            comandoSQL2.setString(1, "Atrasado");
            ResultSet resultado = comandoSQL.executeQuery();
            while(resultado.next()){
                if((analisa.analisaSituacao(resultado.getString("datavencimento"))).equals("Atrasado")){
                    comandoSQL2.setLong(2, resultado.getLong("idvenda"));
                    comandoSQL2.execute();
                }
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(GeralDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
