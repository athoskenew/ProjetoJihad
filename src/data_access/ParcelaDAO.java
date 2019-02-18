package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PagarVenda;
import model.Parcela;
import util.FormataData;
import util.Mensagem;

public class ParcelaDAO {

    public void salvarParcela(Parcela parcela) {
        String sql = "INSERT INTO parcelavenda(idvenda, numeroparcela, valorparcela, parcelapaga, datavencimento) VALUES(?,?,?,?,?)";
        Connection conexao = new ConnectionFactory().getConnection();
        PreparedStatement comandoSQL;
        try {
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, parcela.getIdVenda());
            comandoSQL.setInt(2, parcela.getNumeroParcela());
            comandoSQL.setDouble(3, parcela.getValorParcela());
            comandoSQL.setBoolean(4, parcela.isParcelaPaga());
            comandoSQL.setString(5, parcela.getDataVencimento());
            comandoSQL.execute();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ParcelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Parcela> buscarParcela(Long idVenda) {
        List<Parcela> listaParcelas = new ArrayList<>();
        String sql = "SELECT * FROM parcelavenda WHERE idvenda=? AND parcelapaga=? ORDER BY numeroparcela ASC";
        Connection conexao = new ConnectionFactory().getConnection();
        PreparedStatement comandoSQL;
        try {
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, idVenda);
            comandoSQL.setBoolean(2, false);

            ResultSet resultado = comandoSQL.executeQuery();

            while (resultado.next()) {
                Parcela parcela = new Parcela();
                parcela.setIdVenda(resultado.getLong("idvenda"));
                parcela.setNumeroParcela(resultado.getInt("numeroparcela"));
                parcela.setValorParcela(resultado.getDouble("valorparcela"));
                parcela.setParcelaPaga(resultado.getBoolean("parcelapaga"));
                parcela.setDataVencimento(resultado.getString("datavencimento"));
                parcela.setDataPagamento(resultado.getString("datapagamento"));
                listaParcelas.add(parcela);
            }

            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ParcelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaParcelas;
    }

    public void atualizarStatusPagamento(PagarVenda pagarVenda) {
        String sql = "UPDATE parcelavenda SET parcelapaga=?, datapagamento=? WHERE idvenda=? AND numeroparcela=?";
        Connection conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            List<Parcela> lista = pagarVenda.getListaParcelas();
            for (int i=1; i<=pagarVenda.getNumeroDeParcelasParaPagarAgora(); i++) {
                Parcela p = lista.get((i-1));
                comandoSQL.setBoolean(1, true);
                comandoSQL.setString(2, new FormataData().dataAtualFormatada(Date.from(Instant.now())));
                comandoSQL.setLong(3, pagarVenda.getId());
                comandoSQL.setInt(4, p.getNumeroParcela());
                comandoSQL.execute();
            }
            conexao.close();
            Mensagem mensagem = new Mensagem();
        } catch (SQLException ex) {
            Logger.getLogger(ParcelaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
