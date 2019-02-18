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
import model.ProdutoVendido;
import model.QuantidadeProdutoVendido;
import model.Venda;
import util.AnalisaSituacao;
import util.FormataData;
import util.Mensagem;
//import util.Mensagem;

public class VendasDAO {

    public List<Venda> listaVenda() {
        List<Venda> listaVendas = new ArrayList<>();

        /**
         * ******************* Iniciando Busca no Banco ******************
         */
        String sql = "Select * FROM venda";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            ResultSet resultado = comandoSQL.executeQuery();

            while (resultado.next()) {
                Venda venda = new Venda();
                venda.setId(resultado.getLong("id"));
                venda.setIdCliente(resultado.getLong("idcliente"));
                venda.setValorTotal(resultado.getDouble("totalvenda"));
                venda.setFormaPagamento(resultado.getString("formapagamento"));
                venda.setNumeroParcela(resultado.getInt("numeroparcela"));
                venda.setSituacao(resultado.getString("situacao"));
                venda.setDebito(resultado.getDouble("debito"));
                venda.setDataCompra(resultado.getString("datacompra"));
                listaVendas.add(venda);
            }
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Problema ao listar");
        }
        return listaVendas;
    }

    public void fazNada() {

    }

    public void salvarVenda(Venda venda, List<ProdutoVendido> produtosVendidos, List<QuantidadeProdutoVendido> listaQuantidadeProdutoVendidos) {
        String sql = "INSERT INTO venda(idcliente, totalvenda, formapagamento, numeroparcela, debito, situacao, datacompra) VALUES(?,?,?,?,?,?,?)";
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            if (venda.getIdCliente() == null) {
                comandoSQL.setLong(1, -1);
            } else {
                comandoSQL.setLong(1, venda.getIdCliente());
            }
            comandoSQL.setDouble(2, venda.getValorTotal());
            comandoSQL.setString(3, venda.getFormaPagamento());
            comandoSQL.setInt(4, venda.getNumeroParcela());
            comandoSQL.setDouble(5, venda.getDebito());
            comandoSQL.setString(6, venda.getSituacao());
            comandoSQL.setString(7, venda.getDataCompra());
            comandoSQL.execute();

            conexao.close();

            if (venda.getFormaPagamento().equals("Parcelado")) {
                Long idVenda = pesquisaVenda(venda);
                for (int i = 1; i <= venda.getNumeroParcela(); i++) {
                    Parcela parcela = new Parcela();
                    parcela.setIdVenda(venda.getId());
                    parcela.setNumeroParcela(i);
                    parcela.setValorParcela(venda.getValorTotal() / venda.getNumeroParcela());
                    parcela.setParcelaPaga(false);
                    parcela.setDataVencimento(new FormataData().dataMensalParcelaFormatada(Date.from(Instant.now()), i));
                    ParcelaDAO parcelaDAO = new ParcelaDAO();
                    parcelaDAO.salvarParcela(parcela);
                }
            }
            Long idVenda = pesquisaVenda(venda);
            ProdutoVendidoDAO produtoVendidoDAO = new ProdutoVendidoDAO();
            produtoVendidoDAO.salvarProdutoVendido(idVenda, produtosVendidos);
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.atualizaQuantidade(listaQuantidadeProdutoVendidos);

        } catch (SQLException ex) {
            System.out.println("Problema ao salvar" + ex);
        }
    }

    public Long pesquisaVenda(Venda venda) {
        String sql2 = "SELECT id FROM venda WHERE idcliente=? AND totalvenda=? AND formapagamento=? AND numeroparcela=? AND debito=? AND situacao=? AND datacompra=?";
        Connection conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement comandoSQL2 = conexao.prepareStatement(sql2);
            if (venda.getIdCliente() == null) {
                comandoSQL2.setLong(1, -1);
            } else {
                comandoSQL2.setLong(1, venda.getIdCliente());
            }
            comandoSQL2.setDouble(2, venda.getValorTotal());
            comandoSQL2.setString(3, venda.getFormaPagamento());
            comandoSQL2.setInt(4, venda.getNumeroParcela());
            comandoSQL2.setDouble(5, venda.getDebito());
            comandoSQL2.setString(6, venda.getSituacao());
            comandoSQL2.setString(7, venda.getDataCompra());
            ResultSet resultado = comandoSQL2.executeQuery();
            while (resultado.next()) {
                venda.setId(resultado.getLong("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return venda.getId();
    }

    public List<Venda> pesquisaVendaParaPagar(Long idCliente) {
        List<Venda> listaVendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE idcliente=? AND formapagamento=? AND situacao<>?";
        Connection conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, idCliente);
            comandoSQL.setString(2, "Parcelado");
            comandoSQL.setString(3, "Pago");
            ResultSet resultado = comandoSQL.executeQuery();

            while (resultado.next()) {
                Venda venda = new Venda();
                venda.setId(resultado.getLong("id"));
                venda.setIdCliente(resultado.getLong("idcliente"));
                venda.setValorTotal(resultado.getDouble("totalvenda"));
                venda.setFormaPagamento(resultado.getString("formapagamento"));
                venda.setNumeroParcela(resultado.getInt("numeroparcela"));
                venda.setDebito(resultado.getDouble("debito"));
                venda.setSituacao(resultado.getString("situacao"));
                venda.setDataCompra(resultado.getString("datacompra"));
                venda.setParcelasPagas(resultado.getInt("parcelaspagas"));
                listaVendas.add(venda);
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaVendas;
    }

    public void atualizarStatusPagamento(PagarVenda pagarVenda) {
        String sql = "UPDATE venda SET debito=?, situacao=?, parcelaspagas=? WHERE id=?";
        Connection conexao = new ConnectionFactory().getConnection();
        double debito = (pagarVenda.getDebito()) - (pagarVenda.getNumeroDeParcelasParaPagarAgora() * pagarVenda.getValorParcela());
        List<Parcela> listaParcelas = pagarVenda.getListaParcelas();
        String situacao = null;
        if (pagarVenda.getNumeroParcelas() == /*pagarVenda.getNumeroParcelasPagas() + */pagarVenda.getNumeroDeParcelasParaPagarAgora()) {
            situacao = "Pago";
        } else {
            Parcela parcela = listaParcelas.get(pagarVenda.getNumeroDeParcelasParaPagarAgora());
            situacao = new AnalisaSituacao().analisaSituacao(parcela.getDataVencimento());
        }

        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setDouble(1, debito);
            comandoSQL.setString(2, situacao);
            comandoSQL.setInt(3, (pagarVenda.getNumeroParcelasPagas() + pagarVenda.getNumeroDeParcelasParaPagarAgora()));
            comandoSQL.setLong(4, pagarVenda.getId());
            comandoSQL.execute();
            conexao.close();
            new ParcelaDAO().atualizarStatusPagamento(pagarVenda);
        } catch (SQLException ex) {
            Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean analisaSituacao(long id){
        boolean devendo = false;
        Connection conexao = new ConnectionFactory().getConnection();
        
        String sql = "SELECT situacao FROM venda WHERE idcliente=? AND situacao=?";
        
        try {
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setLong(1, id);
            comandoSQL.setString(2, "Atrasado");
            
            ResultSet resultado = comandoSQL.executeQuery();
            
            while(resultado.next()){
                System.out.println(devendo);
                devendo = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(devendo);
        return devendo;
        
    }

    public static void main(String[] args) {
        /*ProdutoVendido produtoVendido = new ProdutoVendido();
        ProdutoVendido produtoVendido2 = new ProdutoVendido();
        ProdutoVendido produtoVendido3 = new ProdutoVendido();

        produtoVendido.setCodProduto(1L);
        produtoVendido.setNomeProduto("Toddynho");
        produtoVendido.setValorVendaProduto(10.0);
        produtoVendido.setQuantidade(2);
        produtoVendido.setTotal(20.0);

        produtoVendido2.setCodProduto(2L);
        produtoVendido2.setNomeProduto("Nescau 2.0");
        produtoVendido2.setValorVendaProduto(5.0);
        produtoVendido2.setQuantidade(3);
        produtoVendido2.setTotal(15.0);

        produtoVendido3.setCodProduto(3L);
        produtoVendido3.setNomeProduto("N||T");
        produtoVendido3.setValorVendaProduto(15.0);
        produtoVendido3.setQuantidade(10);
        produtoVendido3.setTotal(150.0);

        List<ProdutoVendido> p = new ArrayList<>();
        p.add(produtoVendido);
        p.add(produtoVendido2);
        p.add(produtoVendido3);

        Venda venda = new Venda();
        venda.setIdCliente(Long.MIN_VALUE);
        venda.setValorTotal(185.0);
        venda.setFormaPagamento("Parcelado");
        venda.setNumeroParcela(3);
        venda.setSituacao("Atrasado");
        venda.setDebito(61.30);
        venda.setDataCompra(LocalDate.now());

        VendasDAO vendasDAO = new VendasDAO();
        vendasDAO.salvarVenda(venda, p);*/
    }
}
