/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data_access.ClienteDAO;
import data_access.GeralDAO;
import data_access.LoginDAO;
import data_access.ParcelaDAO;
import data_access.ProdutoDAO;
import data_access.UsuarioDAO;
import data_access.VendasDAO;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JFormattedTextField;
import model.Cliente;
import model.ConfiguraUsuario;
import model.PagarVenda;
import model.Parcela;
import model.Produto;
import model.ProdutoVendido;
import model.QuantidadeProdutoVendido;
import model.Usuario;
import model.Venda;
import model.TextFieldFormatter;
import org.controlsfx.control.textfield.TextFields;
import util.FormataData;
import util.Mensagem;

public class FXML_Tela_InicialController implements Initializable {
    
    @FXML
    private int troca = 1;
    @FXML
    private double pagar;

    @FXML
    private TabPane TabGeral;
    @FXML
    private Tab AbaVenda;
    @FXML
    private TextField TexPesqProdt;
    @FXML
    private Button BPesqVend;
    @FXML
    private Button BAdiconarVend;
    @FXML
    private Button BLImparVend;
    @FXML
    private Button BVenderVend;
    @FXML
    private Button BCancelarVend;
    @FXML
    private Button BRemItemVend;
    @FXML
    private TextField TexTotVend;
    @FXML
    private TextField TexDescVend;
    @FXML
    private TextField TextPagVend;
    @FXML
    private ComboBox<String> comboBoxQuantidade;
    @FXML
    private Tab AbaClientes;
    private Tab AbaUsuarios;
    private Tab AbaRelatorio;
    @FXML
    private AnchorPane paneInterno;
    @FXML
    private AnchorPane paneExterno;
    @FXML
    private TableView<Produto> TabPesquisaVenda;
    @FXML
    private TableColumn<Produto, Long> colCodigo;
    @FXML
    private TableColumn<Produto, String> colProduto;
    @FXML
    private TableColumn<Produto, String> colMarca;
    @FXML
    private TableColumn<Produto, Integer> colQuantidade;
    @FXML
    private TableColumn<Produto, Float> colValorUnidade;
    private TableColumn<Produto, Double> colValorTotal;
    @FXML
    private TableView<ProdutoVendido> TabAdiVend;
    @FXML
    private TableColumn<Produto, Long> colCodigoVendaAdiciona;
    @FXML
    private TableColumn<ProdutoVendido, Double> colTotalVendaAdiciona;
    @FXML
    private TableColumn<Produto, String> colProdutoVendaAdiciona;
    @FXML
    private TableColumn<ProdutoVendido, Long> colQuantidadeVendaAdiciona;
    @FXML
    private TableColumn<Produto, Double> colValorUnidadeVendaAdiciona;

    //------------- Aba Produto --------------------------------------------------------
    @FXML
    private Tab AbaProdutos;
    @FXML
    private TableView<Produto> tabelaPesquisaProduto;
    @FXML
    private TableColumn<Produto, Long> colProdutoCod;
    @FXML
    private TableColumn<Produto, String> colProdutoProduto;
    @FXML
    private TableColumn<Produto, String> colProdutoFornecedor;
    @FXML
    private TableColumn<Produto, String> colProdutoMarca;
    @FXML
    private TableColumn<Produto, Integer> colProdutoQuantidade;
    @FXML
    private TableColumn<Produto, Double> colProdutoValorCusto;
    @FXML
    private TableColumn<Produto, Double> colProdutoValorVenda;
    @FXML
    private TextField textFildpesquisaProduto;
    @FXML
    private Button botaoPesquisaProduto;
    @FXML
    private Button botaoCadastraProduto;
    @FXML
    private Button botaoRemoverProduto;
    @FXML
    private Button botaoEditarProduto;
    @FXML
    private Button botaoFecharProduto;

    //------------- Aba Cadastrar Produto --------------------------------------------------------_
    @FXML
    private Tab AbaCadastrarProduto;
    @FXML
    private TextField textFieldcadastroProduto;
    @FXML
    private TextField textFieldcadastroProdutoFornecedor;
    @FXML
    private TextField textFieldcadastroProdutoMarca;
    @FXML
    private TextField textFieldcadastroProdutoValorCusto;
    @FXML
    private TextField textFieldcadastroProdutoValorVenda;
    @FXML
    private TextField textFieldcadastroProdutoQuantidade;
    @FXML
    private Button botaoSalvarCadastroProduto;
    @FXML
    private Button botaoCancelarCadastroProduto;
    @FXML
    private Button botaoLImparCadastroProduto;
    @FXML
    private AnchorPane abaProduto;
    @FXML
    private AnchorPane abaCadastroProduto;
    //------------- Aba Pesquisa Cliente --------------------------------------------------------_
    @FXML
    private Button botaoPesquisaCliente;
    @FXML
    private TableView<Cliente> tabelaPesquisaCliente;
    @FXML
    private TableColumn<Cliente, Long> colClienteCod;
    @FXML
    private TableColumn<Cliente, String> colClienteNome;
    @FXML
    private TableColumn<Cliente, String> colClienteCPF;
    @FXML
    private TableColumn<Cliente, String> colClienteRG;
    @FXML
    private TableColumn<Cliente, String> colClienteCelular;
    @FXML
    private TableColumn<Cliente, String> colClienteDaraNascimento;
    @FXML
    private Button botaoCadastraCliente;
    @FXML
    private Button botaoEditarCliente;
    @FXML
    private Button botaoPagarDebitoCliente;
    @FXML
    private Button botaoRemoverCliente;
    @FXML
    private Button botaoFecharAbaCliente;
    @FXML
    private TextField textFildpesquisaCliente;

    //------------- Aba Cadastro Cliente --------------------------------------------------------_
    private Tab abaCadastrarCliente;
    @FXML
    private TextField textFieldCadastroClienteRG;
    @FXML
    private TextField textFieldcadastroClienteCPF;
    @FXML
    private TextField textFieldcadastroClienteDataNascimento;
    @FXML
    private TextField textFieldcadastroClienteNome;
    @FXML
    private TextField textFieldcadastroClienteNumeroContato;
    @FXML
    private TextField textFieldcadastroClienteEmail;
    @FXML
    private TextField textFieldcadastroClienteEnderecoRua;
    @FXML
    private TextField textFieldcadastroClienteEnderecoNumero;
    @FXML
    private TextField textFieldcadastroClienteEnderecoBairro;
    @FXML
    private TextField textFieldcadastroClienteEnderecoCidade;
    @FXML
    private Button botaoSalvarCadastroCliente;
    @FXML
    private Button botaoCancelarCadastroCliente;
    @FXML
    private Button botaoLimparCadastroCliente;

    List<String> numerosComboBox = new ArrayList<>();
    ObservableList<String> obsnumerosComboBox;
    @FXML
    private Tab AbaCadastrarCliente;

    Mensagem mensagem = new Mensagem();
    Venda venda = new Venda();

    List<Produto> listaProdutos;
    List<Cliente> listaClientes;
    List<Usuario> listaUsuarios;
    List<String> listaSugestaoProduto = new ArrayList<>();
    List<String> listaSugestaoCliente = new ArrayList<>();
    List<String> listaSugestaoUsuario = new ArrayList<>();
    List<Produto> listaProdutosAdicionados = new ArrayList<>();
    List<ProdutoVendido> listaDeProdutosVendidos = new ArrayList<>();
    List<QuantidadeProdutoVendido> listaquantidadeVendida = new ArrayList<>();
    List<QuantidadeProdutoVendido> listaQuantidadeRestante = new ArrayList<>();

    List<PagarVenda> listaPagar = new ArrayList<>();

    int selecionaSalvarOuEditarCliente;
    int selecionaSalvarOuEditarProduto;
    int selecionaSalvarOuEditarUsuario;

    Long idClienteParaEditar;
    Long idProdutoParaEditar;
    Long idUsuarioParaEditar;

    Produto produtoParaTransferirDaTabelaPesquisaParaATabelaAdicionadaTelaVenda;

    Double totalVenda = 0.0;
    Double desconto = 0.0;
    Double totalPagar = 0.0;

    int inteiro = 0;

    PagarVenda vendaPagar = new PagarVenda();
    @FXML
    private Button botaoMenuVenda;
    @FXML
    private Button botaoMenuProduto;
    @FXML
    private Button botaoMenuCliente;
    @FXML
    private Button botaoMenuAjuda;
    @FXML
    private Button botaoMenuSair;

    /*
    Para a confirmação de venda
     */
    List<String> listaFormasDePagamentoComboBox = new ArrayList<>();
    ObservableList<String> obsListaFormasDePagamentoComboBox;
    List<String> listaNumeroDeParcelasComboBox = new ArrayList<>();
    ObservableList<String> obsListaNumeroDeParcelasComboBox;
    List<ProdutoVendido> produtosVendidos = new ArrayList<>();

    List<Cliente> listaAuxiliarSugestaoClienteFinalizaVenda;
    List<String> listaSugestaoClienteFinalizaVenda = new ArrayList<>();
    List<QuantidadeProdutoVendido> listaQuantidadeProdutoVendidos = new ArrayList<>();

    /*
    Elementos para a confirmação da venda
     */
    @FXML
    private ComboBox<String> comboBoxFormaPagamento;
    @FXML
    private ComboBox<String> comboBoxNumeroParcelas;
    @FXML
    private Button buttonFinalizarVenda;
    @FXML
    private Button buttonCancelarVenda;
    @FXML
    private TextField textFieldPesquisaClienteParaConfirmar;
    @FXML
    private Button buttonConfirmaCliente;
    @FXML
    private Label labelNumeroParcelas;
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane splitLeft;
    @FXML
    private AnchorPane splitRight;
    @FXML
    private AnchorPane anchorVendas;
    @FXML
    private HBox hBoxVendas;
    @FXML
    private Pane paneRight;
    @FXML
    private TextField TextRecebidoVend;
    @FXML
    private TextField TextTrocoVend;
    private int idProdutoNaListaDeVenda = 0;
    /*
    Elementos para pagar débito
     */

    @FXML
    private Tab AbaPagarDebito;
    @FXML
    private TextField TextFieldNomeClientePagar;
    @FXML
    private ComboBox<String> ComboBoxNumeroParcelasPagar;
    @FXML
    private TextField TexTotVendPagar;
    @FXML
    private TextField TexDescVendPagar;
    @FXML
    private TextField TextPagVendPagar;
    @FXML
    private TextField TextRecebidoVendPagar;
    @FXML
    private TextField TextTrocoVendPagar;
    @FXML
    private TextField TexVenciProximaParcela;
    @FXML
    private Button ButtonPagar;
    @FXML
    private Button ButtonCancelar;
    @FXML
    private Button botaoMenuPagar;
    @FXML
    private TableView<PagarVenda> tabListaVendas;
    @FXML
    private TableColumn<PagarVenda, Long> colIdVendaPagar;
    @FXML
    private TableColumn<PagarVenda, Double> colValorTotalVendaPagar;
    @FXML
    private TableColumn<PagarVenda, Integer> colNumeroParcelasVendaPagar;
    @FXML
    private TableColumn<PagarVenda, Double> colValorParcelaVendaPagar;
    @FXML
    private TableColumn<PagarVenda, String> colDataCompraPagar;
    @FXML
    private TableColumn<PagarVenda, String> colDataVencimentoProximaParcelaPagar;
    @FXML
    private TableColumn<PagarVenda, String> colSituacaoPagar;
    @FXML
    private Button botaoPesquisarClientePagarVenda;

    double valorTotalNaHoraDePagar = 0.0;
    @FXML
    private AnchorPane splitLeftPagar;
    @FXML
    private AnchorPane splitRightPagar;

    final double pos[] = {0.58};

    final double pos2[] = {0.4846};
    @FXML
    private SplitPane splitPagar;
    @FXML
    private Button botaoMenuConfiguracoes;
    @FXML
    private Tab AbaConfig;
    @FXML
    private TextField TextFieldConfiguraNome;
    @FXML
    private PasswordField TextFieldConfiguraSenha;
    @FXML
    private PasswordField TextFieldConfiguraConfirmaSenha;
    @FXML
    private PasswordField TextFieldConfiguraConfirmaSenhaAtual;
    @FXML
    private Button boaoSalvarConfig;
    @FXML
    private Button botaoCancelarConfig;
    @FXML
    private Label labelNomeEmpresaInterno;
    @FXML
    private Button botaoIniciarPagamento;
    @FXML
    private Button BotaoFecharPagamento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        labelNomeEmpresaInterno.setText(buscaNomeEmpresa());
        tabelasVazia();
        inicioAbasRemovidas();
        combo();
        preencherComboBoxFormaPagamento();
        preencherComboBoxNumeroDeParcelas();
        inicializaListasSugestoes();
        inicializaFuncoesTextFields();

        GeralDAO atualizaAutomatica = new GeralDAO();
        atualizaAutomatica.atualizacaoAutomatica();

        splitRightPagar.setDisable(true);

        paneInterno.setLayoutX((Screen.getPrimary().getVisualBounds().getWidth()) / 2 - (708.0 / 2));
        splitPane.setLayoutX((Screen.getPrimary().getVisualBounds().getWidth()) / 2 - (708.0 / 2));
        splitPane.getDividerPositions();

        splitPane.setDividerPositions(pos);

        for (int i = 0; i < splitPane.getDividers().size(); i++) {
            final int ind = i;
            SplitPane.Divider divider = splitPane.getDividers().get(i);
            divider.positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
                    -> {
                divider.setPosition(pos[ind]);
            });
        }

        splitPagar.setDividerPositions(pos2);

        for (int i = 0; i < splitPagar.getDividers().size(); i++) {
            final int ind = i;
            SplitPane.Divider divider = splitPagar.getDividers().get(i);
            divider.positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
                    -> {
                divider.setPosition(pos2[ind]);
            });
        }
        
        TextRecebidoVendPagar.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d")) {
            if(newValue.contains(".") && newValue.matches("\\d")){
                
            }else{
               TextRecebidoVendPagar.setText(newValue.replaceAll("[^ 0-9.]", ""));
            }
            
        }
    }
});
        
        TabGeral.setFocusTraversable(false);
        TabPesquisaVenda.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() >= 2) {
                        inteiro = 0;
                        pegaProdutoAndAdicionaToVenda();
                    }
                }
            }
        });

        tabListaVendas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() >= 2) {
                        iniciarPagamento();
                    }
                }
            }
        });

        comboBoxNumeroParcelas.setVisible(false);
        labelNumeroParcelas.setVisible(false);
        splitRight.setDisable(true);
        comboBoxFormaPagamento.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(comboBoxFormaPagamento.getSelectionModel().getSelectedItem());
                if (comboBoxFormaPagamento.getSelectionModel().getSelectedItem().equals("Parcelado")) {
                    comboBoxNumeroParcelas.setVisible(true);
                    labelNumeroParcelas.setVisible(true);
                } else if (comboBoxFormaPagamento.getSelectionModel().getSelectedItem().equals("A vista")) {
                    comboBoxNumeroParcelas.setVisible(false);
                    labelNumeroParcelas.setVisible(false);
                }
                venda.setFormaPagamento(comboBoxFormaPagamento.getSelectionModel().getSelectedItem());
            }

        });

        TexTotVend.setEditable(false);
        TextPagVend.setEditable(false);
        TextTrocoVend.setEditable(false);

        TexTotVendPagar.setEditable(false);
        TextPagVendPagar.setEditable(false);
        TextTrocoVendPagar.setEditable(false);

        TextRecebidoVend.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!(TextRecebidoVend.getText().equals("")) && (TextRecebidoVend != null)) {
                    Double recebido = Double.parseDouble(TextRecebidoVend.getText());
                    Double troco = recebido - pagar;
                    TextTrocoVend.setText("Valor do troco: " + String.valueOf(troco)); //
                } else {
                    TextTrocoVend.setText("Valor do troco: 0.0");
                    TextRecebidoVend.setText("");
                }
            }
        });

        TexDescVend.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!(TexDescVend.getText().equals("")) && (TexDescVend != null)) {
                    pagar = totalVenda - Double.parseDouble(TexDescVend.getText()); //(Double.parseDouble(TexTotVend.getText())) - (Double.parseDouble(TexDescVend.getText()));
                    TextPagVend.setText("A pagar: " + String.valueOf(pagar));
                } else {
                    pagar = totalVenda;
                    TextPagVend.setText("A pagar: " + String.valueOf(pagar));
                }
            }
        });

        TexTotVendPagar.setText(String.valueOf(0.0));
        TexDescVendPagar.setText(String.valueOf(0.0));
        TextPagVendPagar.setText(String.valueOf(0.0));
        TextRecebidoVendPagar.setText(String.valueOf(0.0));
        TextTrocoVendPagar.setText(String.valueOf(0.0));
        TexDescVendPagar.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!(TexDescVendPagar.getText().equals("")) && (TexDescVendPagar != null)) {
                    Double pagar = (Double.parseDouble(String.valueOf(vendaPagar.getValorParcela() * (ComboBoxNumeroParcelasPagar.getSelectionModel().getSelectedIndex() + 1)))) - (Double.parseDouble(TexDescVendPagar.getText()));
                    TextPagVendPagar.setText("Total a pagar: " + String.valueOf(pagar));
                } else {
                    Double pagar = (Double.parseDouble(String.valueOf(vendaPagar.getValorParcela() * (ComboBoxNumeroParcelasPagar.getSelectionModel().getSelectedIndex() + 1))));
                    TextPagVendPagar.setText("Total a pagar: " + String.valueOf(pagar));
                }
            }
        });

        ComboBoxNumeroParcelasPagar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(ComboBoxNumeroParcelasPagar.getItems().isEmpty())) {
                    TexTotVendPagar.setText("Valor: " + String.valueOf(vendaPagar.getValorParcela() * (ComboBoxNumeroParcelasPagar.getSelectionModel().getSelectedIndex() + 1)));
                    Double totalPagar = (vendaPagar.getValorParcela() * (ComboBoxNumeroParcelasPagar.getSelectionModel().getSelectedIndex() + 1)) - (Double.valueOf(TexDescVendPagar.getText().replaceAll(",", ".")));
                    TextPagVendPagar.setText("Total a pagar: " + String.valueOf(totalPagar));
                }

            }
        });

        valorTotalNaHoraDePagar = 0.0;

        

        comboBoxFormaPagamento.setValue("A vista");
        TexDescVend.setText("");
        TexTotVend.setText(String.valueOf(0.0));
        TextPagVend.setText(String.valueOf(0.0));
    }

    public void combo() {
        numerosComboBox.add(String.valueOf(""));
        numerosComboBox.add(String.valueOf(1));
        numerosComboBox.add(String.valueOf(2));
        numerosComboBox.add(String.valueOf(3));
        numerosComboBox.add(String.valueOf(4));
        numerosComboBox.add(String.valueOf(5));
        obsnumerosComboBox = FXCollections.observableArrayList(numerosComboBox);
        comboBoxQuantidade.setItems(obsnumerosComboBox);
        comboBoxQuantidade.setEditable(true);
        comboBoxQuantidade.getSelectionModel().select(0);
    }

    public void preencherComboBoxFormaPagamento() {
        listaFormasDePagamentoComboBox.add("A vista");
        listaFormasDePagamentoComboBox.add("Parcelado");
        obsListaFormasDePagamentoComboBox = FXCollections.observableArrayList(listaFormasDePagamentoComboBox);
        comboBoxFormaPagamento.setItems(obsListaFormasDePagamentoComboBox);
    }

    public void preencherComboBoxNumeroDeParcelas() {
        listaNumeroDeParcelasComboBox.add("1");
        listaNumeroDeParcelasComboBox.add("2");
        listaNumeroDeParcelasComboBox.add("3");
        listaNumeroDeParcelasComboBox.add("4");
        listaNumeroDeParcelasComboBox.add("5");
        listaNumeroDeParcelasComboBox.add("6");
        listaNumeroDeParcelasComboBox.add("7");
        listaNumeroDeParcelasComboBox.add("8");
        obsListaNumeroDeParcelasComboBox = FXCollections.observableArrayList(listaNumeroDeParcelasComboBox);
        comboBoxNumeroParcelas.setItems(obsListaNumeroDeParcelasComboBox);
    }

    /*
    *Utilizado para centralizar o painel com as abas na tela no inicio da execução e quando houver
    *redimensionamento da janela
     */
    @FXML
    public void centralizarPanelInterno() {

        paneInterno.setLayoutX((paneExterno.getWidth() / 2) - (paneInterno.getWidth() / 2));
    }

    /*
    *Método para preencher as listas/lista de sugestão de produtos, clientes e usuarios utilizadas nos
    * campos de pesquisa, ao iniciar o software.
     */
    public void inicializaListasSugestoes() {
        //Preencher a lista de produtos e a lista de sugestão de produtos
        atualizaListaProdutos();

        //preencher a lista de clientes e a lista de sugestão de clientes
        listaClientes = new ClienteDAO().listaCiente();
        for (Cliente cliente : listaClientes) {
            listaSugestaoCliente.add(cliente.getNome());
        }

        //preencher a lista de usuarios e a lista de sugestão de usuarios
        listaUsuarios = new UsuarioDAO().listaUsuario();
        for (Usuario usuario : listaUsuarios) {
            listaSugestaoUsuario.add(usuario.getNome());
        }

        //preencher a lista de clientes e a lista de sugestão de clientes incluindo CPF para finalizar venda
        listaAuxiliarSugestaoClienteFinalizaVenda = new ClienteDAO().listaCiente();
        for (Cliente cliente : listaAuxiliarSugestaoClienteFinalizaVenda) {
            String nomeCPF = "";
            nomeCPF = cliente.getNome() + " -- " + cliente.getCpf();
            listaSugestaoClienteFinalizaVenda.add(nomeCPF);
        }

        //preencher a lista de produtos restantes no estoque
        atualizaListaQuantidade();
    }

    public void atualizaListaQuantidade() {
        atualizaListaProdutos();
        for (Produto p : listaProdutos) {
            QuantidadeProdutoVendido q = new QuantidadeProdutoVendido();
            q.setId(p.getCodProduto());
            q.setQuantidade(p.getQuantidadeProduto());
            listaQuantidadeRestante.add(q);
        }
    }

    //Preencher a lista de produtos e a lista de sugestão de produtos
    public void atualizaListaProdutos() {
        listaProdutos = new ProdutoDAO().listaProduto();
        for (Produto produto : listaProdutos) {
            listaSugestaoProduto.add(produto.getProduto());
        }
    }
    
    @FXML
    private void tfCPFKeyReleased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldcadastroClienteCPF);
        tff.formatter();
    }
    
    @FXML
    private void tfNascimentoKeyReleased(){
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##/##/####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldcadastroClienteDataNascimento);
        tff.formatter();
    }
    
    

    /*
    *Método para configurar sugestões de nomes nos textFields de pesquisa e tornar automático 
    *o preenchimento de tabelas a medida que é digitado o nome no campo de texto
     */
    public void inicializaFuncoesTextFields() {

        TextFields.bindAutoCompletion(TexPesqProdt, listaSugestaoProduto);
        TexPesqProdt.textProperty().addListener((observable, oldvalue, newvalue) -> {

            if (TexPesqProdt.getText().trim() != null && !(TexPesqProdt.getText().trim().equals(""))) {
                inicializarTabelaPesquisaProduto();
                TexPesqProdt.requestFocus();
            } else {
                TabPesquisaVenda.getItems().clear();
            }
        });

        TextFields.bindAutoCompletion(textFildpesquisaProduto, listaSugestaoProduto);
        textFildpesquisaProduto.textProperty().addListener((observable, oldvalue, newvalue) -> {
            if (textFildpesquisaProduto.getText().trim() != null && !(textFildpesquisaProduto.getText().trim().equals(""))) {
                inicializarTabelaProduto();
                textFildpesquisaProduto.requestFocus();
            } else {
                tabelaPesquisaProduto.getItems().clear();
            }
        });

        TextFields.bindAutoCompletion(textFildpesquisaCliente, listaSugestaoCliente);
        textFildpesquisaCliente.textProperty().addListener((observable, oldvalue, newvalue) -> {
            if (textFildpesquisaCliente.getText().trim() != null && !(textFildpesquisaCliente.getText().trim().equals(""))) {
                inicializarTabelaCliente();
                textFildpesquisaCliente.requestFocus();
            } else {
                tabelaPesquisaCliente.getItems().clear();
            }
        });

        /* TextFields.bindAutoCompletion(textFildpesquisaUsuario, listaSugestaoUsuario);
        textFildpesquisaUsuario.textProperty().addListener((observable, oldvalue, newvalue) -> {
            if (textFildpesquisaUsuario.getText().trim() != null && !(textFildpesquisaUsuario.getText().trim().equals(""))) {
                inicializarTabelaUsuario();
                textFildpesquisaUsuario.requestFocus();
            } else {
                tabelaPesquisaUsuario.getItems().clear();
            }
        });
         */
        TextFields.bindAutoCompletion(TextFieldNomeClientePagar, listaSugestaoClienteFinalizaVenda);

        TextFields.bindAutoCompletion(textFieldPesquisaClienteParaConfirmar, listaSugestaoClienteFinalizaVenda);
    }

    /*
    *
    *INICIO DA ABA VENDA
    *
     */
 /*
    *Método para inicializar a tabela de pesquisa de produto na aba venda, informando o que vai em cada coluna
     */
    @FXML
    public void inicializarTabelaPesquisaProduto() {
        colCodigo.setCellValueFactory(new PropertyValueFactory("codProduto"));
        colProduto.setCellValueFactory(new PropertyValueFactory("produto"));
        colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        colValorUnidade.setCellValueFactory(new PropertyValueFactory("valorVendaProduto"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory("quantidadeProduto"));

        TabPesquisaVenda.setItems(atualizaTabelaPesquisaProduto());
        TabPesquisaVenda.setItems(atualizaTabelaPesquisaProduto());
    }

    /*
    *Método para preencher a tabela pesquisa de produto da venda produto com os dados do banco
     */
    public ObservableList<Produto> atualizaTabelaPesquisaProduto() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> a = produtoDAO.pesquisaProduto(TexPesqProdt.getText().trim());
        return FXCollections.observableArrayList(produtoDAO.pesquisaProduto(TexPesqProdt.getText().trim()));
    }

    /*
    *Método para inicializar a tabela onde estão os produtos adicionados a venda na aba venda
    *informando o que vai em cada coluna
     */
    public void inicializarTabelaAdicionaProduto() {
        colCodigoVendaAdiciona.setCellValueFactory(new PropertyValueFactory("codProduto"));
        colProdutoVendaAdiciona.setCellValueFactory(new PropertyValueFactory("nomeProduto"));
        colQuantidadeVendaAdiciona.setCellValueFactory(new PropertyValueFactory("quantidade"));
        colValorUnidadeVendaAdiciona.setCellValueFactory(new PropertyValueFactory("valorVendaProduto"));
        colTotalVendaAdiciona.setCellValueFactory(new PropertyValueFactory("total"));
        TabAdiVend.setItems(FXCollections.observableArrayList(listaDeProdutosVendidos));
    }

    /*
    *Método para chamar o metodo de preencher a tabela de produtos adicionados a venda pelo botão adicionar
     */
    @FXML
    public void metodoAuxiliarPegaProdutoAndAdiconaToVenda() {
        inteiro = 1;
        if (TabPesquisaVenda.getItems().get(0) != null) {
            pegaProdutoAndAdicionaToVenda();
        }

    }

    /*
    *Método para preencher a tabela de produtos adicionados a venda
     */
    public void pegaProdutoAndAdicionaToVenda() {
        ProdutoVendido produtoVendido = new ProdutoVendido();
        Long codProduto = 0L;
        int quantidade = 0;
        int quantidadeEscolhida = Integer.parseInt(comboBoxQuantidade.getSelectionModel().getSelectedItem());
        int quantidadeDisponivel = 0;
        boolean continuaVenda = false;
        boolean jaEstaNaLista = false;
        if (inteiro == 1 && TabPesquisaVenda.getSelectionModel().isEmpty()) {
            TabPesquisaVenda.getSelectionModel().select(0);
            codProduto = TabPesquisaVenda.getSelectionModel().getSelectedItem().getCodProduto();
        } else if (inteiro == 1 && !(TabPesquisaVenda.getSelectionModel().isEmpty())) {
            codProduto = TabPesquisaVenda.getSelectionModel().getSelectedItem().getCodProduto();
        } else {
            codProduto = TabPesquisaVenda.getSelectionModel().getSelectedItem().getCodProduto();
        }

        if (listaquantidadeVendida.isEmpty()) {
            QuantidadeProdutoVendido primeiroProduto = new QuantidadeProdutoVendido();
            primeiroProduto.setId(codProduto);
            primeiroProduto.setQuantidade(0);
            primeiroProduto.setIdProdutoNaListaDeVenda(idProdutoNaListaDeVenda);
            listaquantidadeVendida.add(primeiroProduto);
        } else {
            for (QuantidadeProdutoVendido q : listaquantidadeVendida) {
                if (q.getId() == codProduto) {
                    jaEstaNaLista = true;
                }
            }
            if (jaEstaNaLista == false) {
                QuantidadeProdutoVendido proximoProduto = new QuantidadeProdutoVendido();
                proximoProduto.setId(codProduto);
                proximoProduto.setQuantidade(0);
                proximoProduto.setIdProdutoNaListaDeVenda(idProdutoNaListaDeVenda);
                listaquantidadeVendida.add(proximoProduto);
            }
        }

        for (Produto produto : listaProdutos) {     //Percorre a lista de produtos
            if (produto.getCodProduto() == codProduto) {    //Verifica se o produto em analise atualmente na lista é o produto que queremos adicionar
                for (QuantidadeProdutoVendido q : listaquantidadeVendida) { //Percorre a lista de quantidades vendidas
                    if (produto.getCodProduto() == q.getId()) { //Verifica se o produto em analise atualmente na lista de quantidade vendida é o produto que queremos adicionar
                        if (produto.getQuantidadeProduto() >= (q.getQuantidade() + quantidadeEscolhida)) {  //Verifica se a quantidade do produto atual na lista de produtos é maior ou igual ao que já foi adicionado a venda mais o que vamos adicionar agora, se for, continua a venda
                            continuaVenda = true;
                            quantidade = q.getQuantidade() + quantidadeEscolhida;   //Faz o calculo da quantidade para adicionar na lista de quantidade vendida depois que adicionar a venda 
                        } else {
                            continuaVenda = false;
                        }
                        quantidadeDisponivel = produto.getQuantidadeProduto();
                    }
                }
            }
        }
        if (continuaVenda) {

            produtoParaTransferirDaTabelaPesquisaParaATabelaAdicionadaTelaVenda = new ProdutoDAO().pesquisaProduto(codProduto);
            produtoVendido.setIdProdutoNaVenda(idProdutoNaListaDeVenda);
            produtoVendido.setCodProduto(produtoParaTransferirDaTabelaPesquisaParaATabelaAdicionadaTelaVenda.getCodProduto());
            produtoVendido.setNomeProduto(produtoParaTransferirDaTabelaPesquisaParaATabelaAdicionadaTelaVenda.getProduto());
            produtoVendido.setValorVendaProduto(produtoParaTransferirDaTabelaPesquisaParaATabelaAdicionadaTelaVenda.getValorVendaProduto());
            produtoVendido.setQuantidade(quantidadeEscolhida);
            produtoVendido.setTotal(produtoParaTransferirDaTabelaPesquisaParaATabelaAdicionadaTelaVenda.getValorVendaProduto() * quantidadeEscolhida);
            listaDeProdutosVendidos.add(produtoVendido);
            inicializarTabelaAdicionaProduto();
            totalVenda += produtoVendido.getTotal();
            TexTotVend.setText("Total: " + String.valueOf(totalVenda));
            TextPagVend.setText("A pagar: " + String.valueOf(totalVenda));
            TexPesqProdt.setText("");
            comboBoxQuantidade.getSelectionModel().select(0);
            TexPesqProdt.requestFocus();
            for (QuantidadeProdutoVendido q : listaquantidadeVendida) {
                if (q.getId() == codProduto) {
                    QuantidadeProdutoVendido q2 = new QuantidadeProdutoVendido();
                    q2.setId(codProduto);
                    q2.setQuantidade(quantidade);
                    q2.setIdProdutoNaListaDeVenda(idProdutoNaListaDeVenda);
                    listaquantidadeVendida.remove(q);
                    listaquantidadeVendida.add(q2);
                    break;
                }
            }
            for (int i = 0; i < listaQuantidadeRestante.size(); i++) {  //Percorre a lista de quantidade restante que tem a nova quantidade que irá ser utilizada para atualizar a quantidade disponivel no banco passada para finalizar a venda na outra tela
                System.out.println("imprimi o valor de i " + i);
                if (listaQuantidadeRestante.get(i).getId() == codProduto) { //Verifica se o quantidade atual é referente ao produto que queremos adicionar
                    System.out.println("Entrou " + i);
                    listaQuantidadeRestante.get(i).setQuantidade(listaQuantidadeRestante.get(i).getQuantidade() - quantidadeEscolhida);
                    System.out.println("Nova quantidade " + listaQuantidadeRestante.get(i).getQuantidade());
                    break;
                }
            }
            idProdutoNaListaDeVenda = idProdutoNaListaDeVenda + 1;
            codProduto = 0L;
        } else {
            mensagem.mensagemErro("Existem apenas " + quantidadeDisponivel + " unidades disponíveis no estoque");
        }
    }

    /*
    *Método para limpar todos os dados da tabela de pesquisa de produto para venda
     */
    @FXML
    public void limpaTabelaPesquisaProdutoParaVenda() {
        TabPesquisaVenda.getItems().clear();
        List<Produto> a = new ArrayList<>();
        a.add(null);
        TabPesquisaVenda.setItems(FXCollections.observableArrayList(a));
    }

    public void limpaTabelaAdicionaProdutoParaVenda() {
        List<ProdutoVendido> a = new ArrayList<>();
        a.add(null);
        TabAdiVend.setItems(FXCollections.observableArrayList(a));
    }

    /*
    *Método para cancelar a venda
     */
    @FXML
    public void cancelaVenda() {
        String escolha = mensagem.mensagemConfirmacao("Cancelar a Venda?", "Se continuar os dados informados serão perdidos!");
        if (escolha.equals("Confirma")) {
            TabAdiVend.getItems().clear();
            TabPesquisaVenda.getItems().clear();
            comboBoxQuantidade.getSelectionModel().select(0);
            TexPesqProdt.setText("");
            TexDescVend.setText(String.valueOf(0.0));
            TexTotVend.setText(String.valueOf(0.0));
            TextPagVend.setText(String.valueOf(0.0));
            listaDeProdutosVendidos.clear();
            listaProdutosAdicionados.clear();
            listaQuantidadeProdutoVendidos.clear();
            listaquantidadeVendida.clear();
            inicializaListasSugestoes();
            totalVenda = 0.0;
            inteiro = 0;
            limpaTabelaPesquisaProdutoParaVenda();
            limpaTabelaAdicionaProdutoParaVenda();
        } else {

        }
    }

    /*
    *Método para remover um item da venda
     */
    @FXML
    public void auxiliarRemoverItemVenda() {
        if ((TabAdiVend.getSelectionModel().getSelectedItem() != null)) {
            removeItemVenda();
        } else {
            mensagem.mensagemErro("Selecione um produto para remover");
        }
    }

    public void removeItemVenda() {
        ProdutoVendido produtoParaRemover = new ProdutoVendido();
        produtoParaRemover = TabAdiVend.getSelectionModel().getSelectedItem();
        int aux = 0;
        int idNaVenda = produtoParaRemover.getIdProdutoNaVenda();
        ProdutoVendido p1 = new ProdutoVendido();
        int controle = 0;
        for (ProdutoVendido p : listaDeProdutosVendidos) {
            if (listaDeProdutosVendidos.contains(produtoParaRemover)) {
                aux = 1;
            }
        }

        for (QuantidadeProdutoVendido q : listaquantidadeVendida) {

            if (q.getId() == produtoParaRemover.getCodProduto()) {
                for (ProdutoVendido a : listaDeProdutosVendidos) {

                    if (a.getIdProdutoNaVenda() == idNaVenda) {
                        System.out.println("id de cada produto da lista " + q.getIdProdutoNaListaDeVenda());
                        System.out.println("id do produto a ser removido " + idNaVenda);
                        System.out.println("Quantidade antes de fazer a troca " + q.getQuantidade());
                        int quantidade = 0;
                        quantidade = q.getQuantidade();
                        quantidade = quantidade - produtoParaRemover.getQuantidade();
                        QuantidadeProdutoVendido qu = new QuantidadeProdutoVendido();
                        qu.setId(q.getId());
                        qu.setIdProdutoNaListaDeVenda(q.getIdProdutoNaListaDeVenda());
                        qu.setQuantidade(quantidade);
                        listaquantidadeVendida.remove(q);
                        listaquantidadeVendida.add(qu);
                        System.out.println("Quantidade depois de fazer a troca " + qu.getQuantidade());
                        controle = 1;
                        break;

                    }
                }
                if (controle == 1) {
                    break;
                }
            }

        }

        for (int i = 0; i < listaQuantidadeRestante.size(); i++) {  //Percorre a lista de quantidade restante que tem a nova quantidade que irá ser utilizada para atualizar a quantidade disponivel no banco passada para finalizar a venda na outra tela
            if (listaQuantidadeRestante.get(i).getId() == produtoParaRemover.getCodProduto()) { //Verifica se o quantidade atual é referente ao produto que queremos adicionar
                listaQuantidadeRestante.get(i).setQuantidade(listaQuantidadeRestante.get(i).getQuantidade() + produtoParaRemover.getQuantidade());
                break;
            }
        }

        if (aux == 1) {
            listaDeProdutosVendidos.remove(produtoParaRemover);
            inicializarTabelaAdicionaProduto();
            totalVenda = totalVenda - (produtoParaRemover.getQuantidade() * produtoParaRemover.getValorVendaProduto());
            TexTotVend.setText(String.valueOf(totalVenda));
            TextPagVend.setText(String.valueOf(totalVenda));
        }
        aux = 0;
        if (listaDeProdutosVendidos.size() == 0) {
            limpaTabelaAdicionaProdutoParaVenda();
        }
    }

    @FXML
    public void vender() {
        if (!(listaDeProdutosVendidos.isEmpty())) {
            splitRight.setDisable(false);
            splitLeft.setDisable(true);
            TextTrocoVend.setText("Valor do troco: 0.0");
        } else {
            mensagem.mensagemErro("Selecione pelo menos 1 produto para realizar a venda");
        }
    }

    @FXML
    public void cancelar() {
        venda = new Venda();
        TextRecebidoVend.setText("0.0");
        TextTrocoVend.setText("0.0");
        splitRight.setDisable(true);
        splitLeft.setDisable(false);

    }

    @FXML
    public void confirmarCliente() {
        if (!(textFieldPesquisaClienteParaConfirmar.getText().trim()).equals("") && textFieldPesquisaClienteParaConfirmar.getText() != null) {
            if (listaSugestaoCliente.contains(textFieldPesquisaClienteParaConfirmar.getText()));
            {
                String[] nome = textFieldPesquisaClienteParaConfirmar.getText().split("--");
                String cpf = nome[1];
                ClienteDAO clienteDAO = new ClienteDAO();
                Long id = clienteDAO.pesquisaIDCliente(cpf.trim());
                VendasDAO vendaDAO = new VendasDAO();
                boolean continua = vendaDAO.analisaSituacao(id);
                if (continua) {
                    String resposta = mensagem.mensagemConfirmacao("Débito!", "O cliente está em débito. Deseja continuar?");
                    if (resposta.equals("Confirma")) {
                        venda.setIdCliente(id);
                    } else {

                    }
                } else {
                    venda.setIdCliente(id);
                }

            }
        }
    }

    @FXML
    public void finalizarVenda() {

        if (comboBoxFormaPagamento.getSelectionModel().getSelectedItem().equals("A vista")) {
            venda.setValorTotal(totalVenda);
            venda.setDebito(0.0);
            venda.setSituacao("Pago");
            venda.setDataCompra(new FormataData().dataAtualFormatada(Date.from(Instant.now())));
            venda.setFormaPagamento(comboBoxFormaPagamento.getSelectionModel().getSelectedItem());
            VendasDAO vendaDAO = new VendasDAO();
            vendaDAO.salvarVenda(venda, listaDeProdutosVendidos, listaquantidadeVendida);
            mensagem.mensagemInforma("Venda Realizada");
            cancelar();
            limparVendaFinalizada();
        } else {
            if (venda.getIdCliente() != null) {
                venda.setValorTotal(pagar); //totalVenda
                venda.setDebito(totalVenda); //
                venda.setSituacao("Em dia");
                venda.setNumeroParcela(Integer.parseInt(comboBoxNumeroParcelas.getSelectionModel().getSelectedItem()));
                venda.setDataCompra(new FormataData().dataAtualFormatada(Date.from(Instant.now())));
                venda.setFormaPagamento(comboBoxFormaPagamento.getSelectionModel().getSelectedItem());
                VendasDAO vendaDAO = new VendasDAO();
                vendaDAO.salvarVenda(venda, listaDeProdutosVendidos, listaquantidadeVendida);
                mensagem.mensagemInforma("Venda Realizada");
                cancelar();
                limparVendaFinalizada();
            } else {
                mensagem.mensagemErro("Para realizar uma venda parcelada vc precisa selecionar um cliente e clicar em confirmar");
            }
        }
    }

    public void limparVendaFinalizada() {
        TabAdiVend.getItems().clear();
        TabPesquisaVenda.getItems().clear();
        comboBoxQuantidade.getSelectionModel().select(0);
        TexPesqProdt.setText("");
        TexDescVend.setText(String.valueOf(0.0));
        TexTotVend.setText(String.valueOf(0.0));
        TextPagVend.setText(String.valueOf(0.0));
        listaDeProdutosVendidos.clear();
        listaProdutosAdicionados.clear();
        listaQuantidadeProdutoVendidos.clear();
        listaquantidadeVendida.clear();
        inicializaListasSugestoes();
        comboBoxFormaPagamento.getSelectionModel().select(0);
        totalVenda = 0.0;
        inteiro = 0;
        textFieldPesquisaClienteParaConfirmar.clear();
        ComboBoxNumeroParcelasPagar.getSelectionModel().select(0);
        listaquantidadeVendida.clear();
        atualizaListaQuantidade();
        limpaTabelaPesquisaProdutoParaVenda();
        limpaTabelaAdicionaProdutoParaVenda();
    }

    /*
    *
    *INICIO DA ABA PRODUTO
    *
     */
 /*
    *Método para inicializar a tabela pesquisa de produto da aba produto, informando o que vai em cada coluna
     */
    @FXML
    public void inicializarTabelaProduto() {
        colProdutoCod.setCellValueFactory(new PropertyValueFactory("codProduto"));
        colProdutoProduto.setCellValueFactory(new PropertyValueFactory("produto"));
        colProdutoFornecedor.setCellValueFactory(new PropertyValueFactory("fornecedor"));
        colProdutoMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        colProdutoQuantidade.setCellValueFactory(new PropertyValueFactory("quantidadeProduto"));
        colProdutoValorCusto.setCellValueFactory(new PropertyValueFactory("valorCustoProduto"));
        colProdutoValorVenda.setCellValueFactory(new PropertyValueFactory("valorVendaProduto"));
        tabelaPesquisaProduto.setItems(atualizaTabelaProtudo());
    }

    /*
    *Método para preencher a tabela pesquisa de produto da aba produto com os dados do banco
     */
    public ObservableList<Produto> atualizaTabelaProtudo() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        if (AbaProdutos.isSelected()) {
            return FXCollections.observableArrayList(produtoDAO.pesquisaProduto(textFildpesquisaProduto.getText()));
        } else {
            return FXCollections.observableArrayList(produtoDAO.pesquisaProduto(TexPesqProdt.getText()));
        }
    }

    /*
    *Método para iniciar a edição do produto... Ele pega o id do produto, busca os
    *dados do produto no banco e joga na tela de cadastro para serem editados
     */
    @FXML
    private void editarCadastroProduto() {
        if (tabelaPesquisaProduto.getSelectionModel().isEmpty()) {
            mensagem.mensagemErro("Selecione um produto para editar");
        } else {
            idProdutoParaEditar = tabelaPesquisaProduto.getSelectionModel().getSelectedItem().getCodProduto();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produtoParaEditar = produtoDAO.pesquisaProduto(idProdutoParaEditar);
            textFieldcadastroProduto.setText(produtoParaEditar.getProduto());
            textFieldcadastroProdutoFornecedor.setText(produtoParaEditar.getFornecedor());
            textFieldcadastroProdutoMarca.setText(produtoParaEditar.getMarca());
            textFieldcadastroProdutoQuantidade.setText(String.valueOf(produtoParaEditar.getQuantidadeProduto()));
            textFieldcadastroProdutoValorCusto.setText(String.valueOf(produtoParaEditar.getValorCustoProduto()));
            textFieldcadastroProdutoValorVenda.setText(String.valueOf(produtoParaEditar.getValorVendaProduto()));
            chamaSalvarParaEditarProduto();
        }
    }

    /*
    *Método utilizado para chamar a tela de cadastrar produto pelo botão cadastrar da aba produto
     */
    @FXML
    public void chamaSalvarParaCadastrarProduto() {
        TabGeral.getTabs().add(1, AbaCadastrarProduto);
        selecionaSalvarOuEditarProduto = 1;
        TabGeral.getSelectionModel().select(AbaCadastrarProduto);
    }

    /*
    *Método utilizado para chamar a tela de cadastrar produto pelo botão editar da aba produto
     */
    public void chamaSalvarParaEditarProduto() {
        TabGeral.getTabs().add(2, AbaCadastrarProduto);
        selecionaSalvarOuEditarProduto = 2;
        TabGeral.getSelectionModel().select(AbaCadastrarProduto);
    }

    /*
    *Método utilizado para apagar o cadastro de um produto
     */
    @FXML
    private void apagarCadastroProduto() {

        if (tabelaPesquisaProduto.getSelectionModel().isEmpty()) {
            mensagem.mensagemErro("Selecione um produto para apagar!");
        } else {
            String confirma = mensagem.mensagemConfirmacao("Apagar Cadastro de Produto", "Apagar Cadastro de Produto?");
            if (confirma.equals("Confirma")) {
                ProdutoDAO produtoDAO = new ProdutoDAO();
                Long idProduto = tabelaPesquisaProduto.getSelectionModel().getSelectedItem().getCodProduto();
                produtoDAO.apagaProduto(idProduto);
                inicializarTabelaProduto();
            }

        }
    }

    /*
    *
    *INICIO DA ABA CADASTRAR PRODUTO
    *
     */
 /*
    *Método utilizado para indicar qual método a ação do botão salvar da tela de cadastro de produtos
    *deve chamar
     */
    @FXML
    public void escolheMetodoSalvarOuEditarProduto() {
        if (selecionaSalvarOuEditarProduto == 1) {
            salvarCadastroProduto();

        } else if (selecionaSalvarOuEditarProduto == 2) {
            salvarCadastroProdutoEditado();

        }
    }

    /*
    *Método utilizado para cadastrar um produto
     */
    private void salvarCadastroProduto() {
        boolean continua = verificaCamposCadastrarProduto();
        if (continua) {
            Produto produto = new Produto();
            produto.setProduto(textFieldcadastroProduto.getText());
            produto.setFornecedor(textFieldcadastroProdutoFornecedor.getText());
            produto.setMarca(textFieldcadastroProdutoMarca.getText());
            produto.setQuantidadeProduto(Integer.parseInt(textFieldcadastroProdutoQuantidade.getText()));
            produto.setValorCustoProduto(Double.parseDouble(textFieldcadastroProdutoValorCusto.getText()));
            produto.setValorVendaProduto(Double.parseDouble(textFieldcadastroProdutoValorVenda.getText()));

            try {

                ProdutoDAO produtoDao = new ProdutoDAO();
                produtoDao.salvarProduto(produto);
                inicializarTabelaProduto();
                inicializarTabelaPesquisaProduto();
                atualizaListaProdutos();
                mensagem.mensagemInforma("Produto cadastrado");
                cancelaCadastroProduto();
            } catch (Error salvar) {

            }
        } else {
            mensagem.mensagemErro("Preencha todos os campos antes de salvar");
        }

    }

    /*
    *Método utilizado para editar o cadastro de um produto
     */
    public void salvarCadastroProdutoEditado() {
        boolean continua = verificaCamposCadastrarProduto();
        if (continua) {
            Produto produto = new Produto();
            produto.setCodProduto(idProdutoParaEditar);
            produto.setProduto(textFieldcadastroProduto.getText());
            produto.setFornecedor(textFieldcadastroProdutoFornecedor.getText());
            produto.setMarca(textFieldcadastroProdutoMarca.getText());
            produto.setQuantidadeProduto(Integer.parseInt(textFieldcadastroProdutoQuantidade.getText()));
            produto.setValorCustoProduto(Double.parseDouble(textFieldcadastroProdutoValorCusto.getText()));
            produto.setValorVendaProduto(Double.parseDouble(textFieldcadastroProdutoValorVenda.getText()));

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.editarProduto(produto);
            inicializarTabelaProduto();
            inicializarTabelaPesquisaProduto();
            atualizaListaProdutos();
            mensagem.mensagemInforma("Produto editado");
            cancelaCadastroProduto();
        } else {
            mensagem.mensagemErro("Preencha todos os campos antes de salvar");
        }

    }

    public boolean verificaCamposCadastrarProduto() {
        boolean preenchido = false;

        if (!(textFieldcadastroProduto.getText().equals("")) && !(textFieldcadastroProduto.getText() == null)
                && !(textFieldcadastroProdutoFornecedor.getText().equals("")) && !(textFieldcadastroProdutoFornecedor.getText() == null)
                && !(textFieldcadastroProdutoMarca.getText().equals("")) && !(textFieldcadastroProdutoMarca.getText() == null)
                && !(textFieldcadastroProdutoQuantidade.getText().equals("")) && !(textFieldcadastroProdutoQuantidade.getText() == null)
                && !(textFieldcadastroProdutoValorCusto.getText().equals("")) && !(textFieldcadastroProdutoValorCusto.getText() == null)
                && !(textFieldcadastroProdutoValorVenda.getText().equals("")) && !(textFieldcadastroProdutoValorVenda.getText() == null)) {
            preenchido = true;
        }

        return preenchido;
    }

    @FXML
    public void cancelaCadastroProduto() {
        limpaDadosdaTelaCadastroProduto();
        TabGeral.getTabs().set(1, AbaProdutos);
        TabGeral.getSelectionModel().select(AbaProdutos);
        TabGeral.getTabs().remove(2);

    }

    @FXML
    public void limpaDadosdaTelaCadastroProduto() {
        textFieldcadastroProduto.setText("");
        textFieldcadastroProdutoFornecedor.setText("");
        textFieldcadastroProdutoMarca.setText("");
        textFieldcadastroProdutoQuantidade.setText("");
        textFieldcadastroProdutoValorCusto.setText("");
        textFieldcadastroProdutoValorVenda.setText("");
    }

    /*
    *
    *INICIO DA ABA CLIENTE
    *
     */
 /*
    *Método para inicializar a tabela de clientes na aba Cliente, informando o que vai em cada coluna
     */
    @FXML
    public void inicializarTabelaCliente() {
        colClienteCod.setCellValueFactory(new PropertyValueFactory("codigo"));
        colClienteNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colClienteCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
        colClienteRG.setCellValueFactory(new PropertyValueFactory("rg"));
        colClienteCelular.setCellValueFactory(new PropertyValueFactory("numeroTelefone"));
        colClienteDaraNascimento.setCellValueFactory(new PropertyValueFactory("dataNascimento"));
        tabelaPesquisaCliente.setItems(atualizaTabelaCliente());
    }

    /*
    *Método para preencher a tabela pesquisa de clientes da aba cliente com os dados do banco
     */
    public ObservableList<Cliente> atualizaTabelaCliente() {
        ClienteDAO clienteDAO = new ClienteDAO();
        return FXCollections.observableArrayList(clienteDAO.pesquisaCliente(textFildpesquisaCliente.getText()));
    }

    /*
    *Método para iniciar a edição do cliente... Ele pega o id do cliente, busca os
    *dados do cliente no banco e joga na tela de cadastro para serem editados
     */
    @FXML
    private void editarCadastroCliente() {
        if (!(tabelaPesquisaCliente.getSelectionModel().isEmpty())) {
            idClienteParaEditar = tabelaPesquisaCliente.getSelectionModel().getSelectedItem().getCodigo();
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente clienteParaEditar = clienteDAO.pesquisaCliente(idClienteParaEditar);
            textFieldcadastroClienteNome.setText(clienteParaEditar.getNome());
            textFieldCadastroClienteRG.setText(clienteParaEditar.getRg());
            textFieldcadastroClienteCPF.setText(clienteParaEditar.getCpf());
            textFieldcadastroClienteDataNascimento.setText(clienteParaEditar.getDataNascimento());
            textFieldcadastroClienteNumeroContato.setText(clienteParaEditar.getNumeroTelefone());
            textFieldcadastroClienteEmail.setText(clienteParaEditar.getEmail());
            textFieldcadastroClienteEnderecoRua.setText(clienteParaEditar.getEnderecoRua());
            textFieldcadastroClienteEnderecoNumero.setText(String.valueOf(clienteParaEditar.getEnderecoNumero()));
            textFieldcadastroClienteEnderecoBairro.setText(clienteParaEditar.getEnderecoBairro());
            textFieldcadastroClienteEnderecoCidade.setText(clienteParaEditar.getEnderecoCidade());
            chamaSalvarParaEditarCliente();
        } else {
            mensagem.mensagemErro("Selecione um cliente para editar!");
        }

    }

    /*
    *Método utilizado para chamar a tela de cadastrar cliente pelo botão cadastrar da aba cliente
     */
    @FXML
    public void chamaSalvarParaCadastrarCliente() {
        selecionaSalvarOuEditarCliente = 1;
        TabGeral.getTabs().add(1, AbaCadastrarCliente);
        TabGeral.getSelectionModel().select(AbaCadastrarCliente);
    }

    /*
    *Método utilizado para chamar a tela de cadastrar cliente pelo botão editar da aba cliente
     */
    public void chamaSalvarParaEditarCliente() {
        selecionaSalvarOuEditarCliente = 2;
        TabGeral.getTabs().add(2, AbaCadastrarCliente);
        TabGeral.getSelectionModel().select(AbaCadastrarCliente);
    }

    /*
    *Método utilizado para apagar o cadastro de um cliente
     */
    @FXML
    private void apagarCadastroCliente() {

        if (!(tabelaPesquisaCliente.getSelectionModel().isEmpty())) {
            String confirmar = mensagem.mensagemConfirmacao("Apagar cadastro de Cliente", "Deseja apagar o cadastro deste cliente?");
            if (confirmar.equals("Confirma")) {
                ClienteDAO clienteDAO = new ClienteDAO();

                Long idCliente = tabelaPesquisaCliente.getSelectionModel().getSelectedItem().getCodigo();
                boolean prosseguir = clienteDAO.verificaSituacaoCliente(idCliente);
                if (prosseguir) {
                    clienteDAO.apagaCliente(idCliente);
                    inicializarTabelaCliente();
                } else {
                    mensagem.mensagemErro("Não é possível excluir, pois o cliente possui contas não quitadas!");
                }

            } else {

            }
        } else {
            mensagem.mensagemErro("Selecione o cliente para apagar!");
        }

    }

    /*
    *
    *INICIO DA ABA CADASTRAR CLIENTE
    *
     */
 /*
    *Método utilizado para indicar qual método a ação do botão salvar da tela de cadastro de cliente
    *deve chamar
     */
    @FXML
    public void escolheMetodoSalvarOuEditarCliente() {
        if (selecionaSalvarOuEditarCliente == 1) {
            salvarCadastroCliente();
        } else if (selecionaSalvarOuEditarCliente == 2) {
            salvarCadastroClienteEditado();
        }
    }

    /*
    *Método utilizado para cadastrar um cliente
     */
    private void salvarCadastroCliente() {
        if (!(textFieldcadastroClienteNome.getText().equals("")) && textFieldcadastroClienteNome.getText() != null
                && !(textFieldcadastroClienteCPF.getText().equals("")) && textFieldcadastroClienteCPF.getText() != null) {
            Cliente cliente = new Cliente();
            cliente.setNome(textFieldcadastroClienteNome.getText());
            cliente.setCpf(textFieldcadastroClienteCPF.getText());
            cliente.setRg(textFieldCadastroClienteRG.getText());
            cliente.setDataNascimento(textFieldcadastroClienteDataNascimento.getText());
            cliente.setNumeroTelefone(textFieldcadastroClienteNumeroContato.getText());
            cliente.setEmail(textFieldcadastroClienteEmail.getText());
            cliente.setEnderecoRua(textFieldcadastroClienteEnderecoRua.getText());
            cliente.setEnderecoNumero(textFieldcadastroClienteEnderecoNumero.getText());
            cliente.setEnderecoBairro(textFieldcadastroClienteEnderecoBairro.getText());
            cliente.setEnderecoCidade(textFieldcadastroClienteEnderecoCidade.getText());
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.salvarCliente(cliente);
            mensagem.mensagemInforma("Cliente cadastrado");
            inicializarTabelaCliente();
            cancelarCadastroCliente();
        } else {
            mensagem.mensagemErro("É obrigatório informar o nome e o CPF do cliente!");
        }

    }

    /*
    *Método utilizado para editar o cadastro de um cliente
     */
    private void salvarCadastroClienteEditado() {
        if (!(textFieldcadastroClienteNome.getText().equals("")) && textFieldcadastroClienteNome.getText() != null
                && !(textFieldcadastroClienteCPF.getText().equals("")) && textFieldcadastroClienteCPF.getText() != null) {
            Cliente cliente = new Cliente();
            cliente.setCodigo(idClienteParaEditar);
            cliente.setNome(textFieldcadastroClienteNome.getText());
            cliente.setCpf(textFieldcadastroClienteCPF.getText());
            cliente.setRg(textFieldCadastroClienteRG.getText());
            cliente.setDataNascimento(textFieldcadastroClienteDataNascimento.getText());
            cliente.setNumeroTelefone(textFieldcadastroClienteNumeroContato.getText());
            cliente.setEmail(textFieldcadastroClienteEmail.getText());
            cliente.setEnderecoRua(textFieldcadastroClienteEnderecoRua.getText());
            cliente.setEnderecoNumero(textFieldcadastroClienteEnderecoNumero.getText());
            cliente.setEnderecoBairro(textFieldcadastroClienteEnderecoBairro.getText());
            cliente.setEnderecoCidade(textFieldcadastroClienteEnderecoCidade.getText());
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.editarCliente(cliente);
            mensagem.mensagemInforma("Cliente editado");
            inicializarTabelaCliente();
            cancelarCadastroCliente();
        } else {
            mensagem.mensagemErro("É obrigatório informar o nome e o CPF do cliente!");
        }

    }

    @FXML
    public void cancelarCadastroCliente() {
        fecharAbaCadastroCliente();
        limparDadosTelaCadastroClientes();
    }

    @FXML
    public void limparDadosTelaCadastroClientes() {
        textFieldcadastroClienteNome.setText("");
        textFieldCadastroClienteRG.setText("");
        textFieldcadastroClienteCPF.setText("");
        textFieldcadastroClienteDataNascimento.setText("");
        textFieldcadastroClienteEmail.setText("");
        textFieldcadastroClienteEnderecoBairro.setText("");
        textFieldcadastroClienteEnderecoCidade.setText("");
        textFieldcadastroClienteEnderecoNumero.setText("");
        textFieldcadastroClienteEnderecoRua.setText("");
        textFieldcadastroClienteNumeroContato.setText("");
    }

    /*
    *
    *INICIO DA ABA PAGAR DÉBITO
    *
     */
    @FXML
    public void chamarIniciarTabelaVendaPagarDebito() {
        if (!(TextFieldNomeClientePagar.getText().equals("")) && TextFieldNomeClientePagar != null) {
            if (listaSugestaoClienteFinalizaVenda.contains(TextFieldNomeClientePagar.getText())) {
                inicializarTabelaVendaPagarDebito();
            }
        }
    }

    public void inicializarTabelaVendaPagarDebito() {
        colIdVendaPagar.setCellValueFactory(new PropertyValueFactory("id"));
        colValorTotalVendaPagar.setCellValueFactory(new PropertyValueFactory("valorTotal"));
        colNumeroParcelasVendaPagar.setCellValueFactory(new PropertyValueFactory("numeroParcelas"));
        colValorParcelaVendaPagar.setCellValueFactory(new PropertyValueFactory("valorParcela"));
        colDataCompraPagar.setCellValueFactory(new PropertyValueFactory("dataCompra"));
        colDataVencimentoProximaParcelaPagar.setCellValueFactory(new PropertyValueFactory("dataVencimento"));
        colSituacaoPagar.setCellValueFactory(new PropertyValueFactory("situacao"));
        tabListaVendas.getItems().clear();
        listaPagar.clear();
        tabListaVendas.setItems(atualizaTabelaVendaPagarDebito());
    }

    private ObservableList<PagarVenda> atualizaTabelaVendaPagarDebito() {

        if (!(TextFieldNomeClientePagar.getText().trim()).equals("") && TextFieldNomeClientePagar.getText() != null) {
            if (listaSugestaoCliente.contains(TextFieldNomeClientePagar.getText()));
            {
                String[] nome = TextFieldNomeClientePagar.getText().split("--");    //Pego o nome que está no textField e separo onde tem "--"
                String cpf = nome[1]; //Pego o CPF do cliente
                ClienteDAO clienteDAO = new ClienteDAO();
                Long idCliente = clienteDAO.pesquisaIDCliente(cpf.trim());  //Pesquisa o id do cliente com esse CPF
                List<Venda> vendasCliente = new VendasDAO().pesquisaVendaParaPagar(idCliente);  //Lista todas as vendas deste que não estão quitadas
                if (vendasCliente.size() > 0) { //Verifica se exite alguma venda na lista
                    for (Venda a : vendasCliente) { //Percorre todas as vendas não quitadas que foram listadas acima
                        List<Parcela> listaParcelas = new ArrayList<>();    //Cria uma lista para guardar as parcelas de cada venda.
                        PagarVenda b = new PagarVenda();    //Cria a variavel que vai guardar todas as informações da conta que será paga
                        b.setId(a.getId());
                        b.setValorTotal(a.getValorTotal());
                        b.setNumeroParcelas(a.getNumeroParcela() - a.getParcelasPagas());
                        b.setSituacao(a.getSituacao());
                        b.setDataCompra(a.getDataCompra());
                        listaParcelas = new ParcelaDAO().buscarParcela(a.getId());  //Preenche a lista de parcelas com as parcelas não pagas dessa venda
                        Parcela proximaParcelaVencida = new Parcela();  //cria a variável que vai guardar a proxima parcela a vencer, que normalmente é a que é paga
                        proximaParcelaVencida = listaParcelas.get(0);   //Pega a primeira parcela da lista
                        b.setValorParcela(proximaParcelaVencida.getValorParcela());
                        b.setDataVencimento(proximaParcelaVencida.getDataVencimento());
                        b.setNumeroParcelasPagas(a.getParcelasPagas());
                        b.setNumeroDaParcela(proximaParcelaVencida.getNumeroParcela());
                        b.setListaParcelas(listaParcelas);
                        b.setDebito(a.getDebito());

                        listaPagar.add(b);
                    }
                } else {

                }
            }
        }
        return FXCollections.observableArrayList(listaPagar);
    }

    @FXML
    public void chamarPagarDebitoPelaTelaClientes() {
        if (!(tabelaPesquisaCliente.getSelectionModel().isEmpty())) {
            inicializarTabelaVendaPagarDebitoPelaTelaDeClientes();
        } else {
            mensagem.mensagemErro("Selecione um cliente!");
        }
        

    }

    public void inicializarTabelaVendaPagarDebitoPelaTelaDeClientes() {
        colIdVendaPagar.setCellValueFactory(new PropertyValueFactory("id"));
        colValorTotalVendaPagar.setCellValueFactory(new PropertyValueFactory("valorTotal"));
        colNumeroParcelasVendaPagar.setCellValueFactory(new PropertyValueFactory("numeroParcelas"));
        colValorParcelaVendaPagar.setCellValueFactory(new PropertyValueFactory("valorParcela"));
        colDataCompraPagar.setCellValueFactory(new PropertyValueFactory("dataCompra"));
        colDataVencimentoProximaParcelaPagar.setCellValueFactory(new PropertyValueFactory("dataVencimento"));
        colSituacaoPagar.setCellValueFactory(new PropertyValueFactory("situacao"));
        tabListaVendas.getItems().clear();
        listaPagar.clear();
        if (!(atualizaTabelaVendaPagarDebitoPelaTelaDeClientes().isEmpty())) {
            tabListaVendas.setItems(FXCollections.observableArrayList(listaPagar));
            TabGeral.getTabs().remove(AbaClientes);
            TabGeral.getTabs().add(1, AbaPagarDebito);
            TabGeral.getSelectionModel().select(AbaPagarDebito);

        } else {
            mensagem.mensagemErro("Este cliente não possui contas pendentes");
        }

    }

    private List<PagarVenda> atualizaTabelaVendaPagarDebitoPelaTelaDeClientes() {

        Long idCliente = tabelaPesquisaCliente.getSelectionModel().getSelectedItem().getCodigo();
        List<Venda> vendasCliente = new VendasDAO().pesquisaVendaParaPagar(idCliente);  //Lista todas as vendas deste que não estão quitadas
        if (vendasCliente.size() > 0) { //Verifica se exite alguma venda na lista
            for (Venda a : vendasCliente) { //Percorre todas as vendas não quitadas que foram listadas acima
                List<Parcela> listaParcelas = new ArrayList<>();    //Cria uma lista para guardar as parcelas de cada venda.
                PagarVenda b = new PagarVenda();    //Cria a variavel que vai guardar todas as informações da conta que será paga
                b.setId(a.getId());
                b.setValorTotal(a.getValorTotal());
                b.setNumeroParcelas(a.getNumeroParcela());
                b.setSituacao(a.getSituacao());
                b.setDataCompra(a.getDataCompra());
                listaParcelas = new ParcelaDAO().buscarParcela(a.getId());  //Preenche a lista de parcelas com as parcelas não pagas dessa venda
                Parcela proximaParcelaVencida = new Parcela();  //cria a variável que vai guardar a proxima parcela a vencer, que normalmente é a que é paga
                proximaParcelaVencida = listaParcelas.get(0);   //Pega a primeira parcela da lista
                b.setValorParcela(proximaParcelaVencida.getValorParcela());
                b.setDataVencimento(proximaParcelaVencida.getDataVencimento());
                b.setNumeroParcelasPagas(a.getParcelasPagas());
                b.setNumeroDaParcela(proximaParcelaVencida.getNumeroParcela());
                b.setListaParcelas(listaParcelas);
                b.setDebito(a.getDebito());

                listaPagar.add(b);

            }
        } else {

        }

        return listaPagar;
    }

    @FXML
    public void chamaIniciarPagamentoPeloBotao(){
        if(!(tabListaVendas.getSelectionModel().isEmpty())){
            iniciarPagamento();
        }else{
            mensagem.mensagemErro("Selecione uma conta para pagar");
        }
    }
    
    @FXML
    public void fechaTelaPagamento(){
        limparTelaPagamento();
        fechaPagarDebito();     
    }
    
    public void limparTelaPagamento(){
        List<PagarVenda> listaPagarVenda = new ArrayList<>();
        listaPagarVenda.add(null);
        tabListaVendas.setItems(FXCollections.observableArrayList(listaPagarVenda));
        TextFieldNomeClientePagar.setText("");
    }
    
    public void iniciarPagamento() {
        vendaPagar = tabListaVendas.getSelectionModel().getSelectedItem();
        int controle = vendaPagar.getNumeroParcelas(); //- vendaPagar.getNumeroParcelasPagas();
        List<String> listaParaComboBox = new ArrayList<>();
        //listaParaComboBox.add(String.valueOf("Número de parcelas a pagar"));
        for (int i = 1; i <= controle; i++) {
            listaParaComboBox.add(String.valueOf("Número de parcelas a pagar" + i));
        }
        ComboBoxNumeroParcelasPagar.setItems(FXCollections.observableArrayList(listaParaComboBox));
        ComboBoxNumeroParcelasPagar.getSelectionModel().select(0);
        TexDescVendPagar.setText(String.valueOf(""));
        TextRecebidoVendPagar.setText(String.valueOf(""));
        TextTrocoVendPagar.setText("Valor do troco:" + String.valueOf(0.0));
        valorTotalNaHoraDePagar = vendaPagar.getValorTotal();
        TextRecebidoVendPagar.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!(TextRecebidoVendPagar.getText().equals("")) && (TextRecebidoVendPagar != null)) {
                    if((TexDescVendPagar.getText().equals("")))
                        TexDescVendPagar.setText("0.0");
                    Double totalPagar = (vendaPagar.getValorParcela() * (ComboBoxNumeroParcelasPagar.getSelectionModel().getSelectedIndex() + 1)) - (Double.parseDouble(TexDescVendPagar.getText()));
                    Double recebido = Double.parseDouble(TextRecebidoVendPagar.getText());
                    Double troco = recebido - totalPagar;
                    TextTrocoVendPagar.setText("Valor do troco: " + String.valueOf(troco));
                } else {

                }
            }
        });
        FormataData f = new FormataData();
        String a = f.dataVencimentoFormatada(vendaPagar.getDataVencimento());
        TexVenciProximaParcela.setText("Data de vencimento da próxima parcela: " + a);
        
        splitLeftPagar.setDisable(true);
        splitRightPagar.setDisable(false);
        //TextRecebidoVendPagar.requestFocus();
    }

    @FXML
    public void cancelarIniciarPagamento() {
        ComboBoxNumeroParcelasPagar.getItems().clear();
        TexTotVendPagar.setText(String.valueOf(0.0));
        TexDescVendPagar.setText(String.valueOf(0.0));
        TextPagVendPagar.setText(String.valueOf(0.0));
        TextRecebidoVendPagar.setText(String.valueOf(0.0));
        TextTrocoVendPagar.setText(String.valueOf(0.0));
        TexVenciProximaParcela.setText(String.valueOf(0.0));
        valorTotalNaHoraDePagar = 0.0;
        splitRightPagar.setDisable(true);
        splitLeftPagar.setDisable(false);
        vendaPagar = new PagarVenda();
    }

    @FXML
    public void realizarPagamento() {
        String continua = mensagem.mensagemConfirmacao("Realizar pagamento", "Deseja realizar o pagamento de " + (ComboBoxNumeroParcelasPagar.getSelectionModel().getSelectedIndex() + 1) + " parcela(s)");
        if (continua.equals("Confirma")) {
            vendaPagar.setNumeroDeParcelasParaPagarAgora((ComboBoxNumeroParcelasPagar.getSelectionModel().getSelectedIndex() + 1));
            VendasDAO vendasDAO = new VendasDAO();
            vendasDAO.atualizarStatusPagamento(vendaPagar);
            cancelarIniciarPagamento();
            
        } else {

        }
    }

    //Transfere dados entre tabelas, porem so serve pra Label
    /*public void selecionarItemTabelaProtudo(Venda venda) {
        
        System.out.println(venda.getQuantidade());
        
        colCodigoVendaAdiciona.setId(String.valueOf(venda.getId()));
        colProdutoVendaAdiciona.setCellValueFactory(new PropertyValueFactory(venda.getProduto()));
        colQuantidadeVendaAdiciona.setCellValueFactory(new PropertyValueFactory(String.valueOf(venda.getQuantidade())));
        colValorUnidadeVendaAdiciona.setCellValueFactory(new PropertyValueFactory(String.valueOf(venda.getValorUnidade())));
        colTotalVendaAdiciona.setCellValueFactory(new PropertyValueFactory(String.valueOf(venda.getValotTotal())));
        
        TabAdiVend.setItems(atualizaTabelaVendaProduto());
    }*/
 /*@FXML
    public void inicializarTabelaUsuario() {
        colUsuarioCod.setCellValueFactory(new PropertyValueFactory("codigo"));
        colUsuarioFuncao.setCellValueFactory(new PropertyValueFactory("funcao"));
        colUsuarioNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colUsuarioCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
        colUsuarioDaraNascimento.setCellValueFactory(new PropertyValueFactory("dataNascimento"));
        colUsuarioCelular.setCellValueFactory(new PropertyValueFactory("numeroTelefone"));
        colUsuarioLogin.setCellValueFactory(new PropertyValueFactory("login"));
        colUsuarioSenha.setCellValueFactory(new PropertyValueFactory("senha"));

        tabelaPesquisaUsuario.setItems(atualizaTabelaUsuario());
    }

    @FXML
    private void chamaSalvarParaCadastrarUsuario(ActionEvent event) {
        selecionaSalvarOuEditarUsuario = 1;
        TabGeral.getTabs().set(2, AbaCadastraUsuarios);
        TabGeral.getSelectionModel().select(AbaCadastraUsuarios);
    }

    /*
    *Método utilizado para chamar a tela de cadastrar usuario pelo botão editar da aba usuario
     */
 /*public void chamaSalvarParaEditarUsuario() {
        selecionaSalvarOuEditarUsuario = 2;
        TabGeral.getTabs().set(2, AbaCadastraUsuarios);
        TabGeral.getSelectionModel().select(AbaCadastraUsuarios);
    }*/

 /*
    editar cadastro usuario
     */
 /* @FXML
    private void editarCadastroUsuario(ActionEvent event) {
        idUsuarioParaEditar = tabelaPesquisaUsuario.getSelectionModel().getSelectedItem().getCodigo();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioParaEditar = usuarioDAO.pesquisaUsuario(idUsuarioParaEditar);
        textFieldcadastroUsuarioNome.setText(usuarioParaEditar.getNome());
        textFieldcadastroUsuarioFuncao.setText(usuarioParaEditar.getFuncao());
        textFieldcadastroUsuarioLogin.setText(usuarioParaEditar.getLogin());
        textFieldcadastroUsuarioSenha.setText(usuarioParaEditar.getSenha());
        textFieldcadastroUsuarioDataEntrada.setText(usuarioParaEditar.getDataEntrada());
        textFieldcadastroUsuarioDataSaida.setText(usuarioParaEditar.getDataSaida());
        textFieldcadastroUsuarioCPF.setText(usuarioParaEditar.getCpf());
        textFieldCadastroUsuarioRG.setText(usuarioParaEditar.getRg());
        textFieldcadastroUsuarioDataNascimento.setText(usuarioParaEditar.getDataNascimento());
        textFieldcadastroUsuarioNumeroContato.setText(usuarioParaEditar.getNumeroTelefone());
        textFieldcadastroUsuarioEmail.setText(usuarioParaEditar.getEmail());
        textFieldcadastroUsuarioEnderecoRua.setText(usuarioParaEditar.getEnderecoRua());
        textFieldcadastroUsuarioEnderecoNumero.setText(usuarioParaEditar.getEnderecoNumero());
        textFieldcadastroUsuarioEnderecoBairro.setText(usuarioParaEditar.getEnderecoBairro());
        textFieldcadastroUsuarioEnderecoCidade.setText(usuarioParaEditar.getEnderecoCidade());
        chamaSalvarParaEditarUsuario();
    }

    @FXML
    private void apagarCadastroUsuario(ActionEvent event) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Long idUsuario = tabelaPesquisaUsuario.getSelectionModel().getSelectedItem().getCodigo();
        usuarioDAO.apagaUsuario(idUsuario);
        inicializarTabelaUsuario();

    }

    @FXML
    private void escolheMetodoSalvarOuEditarUsuario(ActionEvent event) {
        if (selecionaSalvarOuEditarUsuario == 1) {
            salvarCadastroUsuario();
            TabGeral.getTabs().set(2, tabInicioPos0);
        } else if (selecionaSalvarOuEditarUsuario == 2) {
            salvarCadastroUsuarioEditado();
            TabGeral.getTabs().set(2, tabInicioPos0);
        }
    }*/

 /*
    *Método utilizado para cadastrar um usuario
     */
 /*private void salvarCadastroUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(textFieldcadastroUsuarioNome.getText());
        usuario.setFuncao(textFieldcadastroUsuarioFuncao.getText());
        usuario.setLogin(textFieldcadastroUsuarioLogin.getText());
        usuario.setSenha(textFieldcadastroUsuarioSenha.getText());
        usuario.setDataEntrada(textFieldcadastroUsuarioDataEntrada.getText());
        usuario.setDataSaida(textFieldcadastroUsuarioDataSaida.getText());
        usuario.setCpf(textFieldcadastroUsuarioCPF.getText());
        usuario.setRg(textFieldCadastroUsuarioRG.getText());
        usuario.setDataNascimento(textFieldcadastroUsuarioDataNascimento.getText());
        usuario.setNumeroTelefone(textFieldcadastroUsuarioNumeroContato.getText());
        usuario.setEmail(textFieldcadastroUsuarioEmail.getText());
        usuario.setEnderecoRua(textFieldcadastroUsuarioEnderecoRua.getText());
        usuario.setEnderecoNumero(textFieldcadastroUsuarioEnderecoNumero.getText());
        usuario.setEnderecoBairro(textFieldcadastroUsuarioEnderecoBairro.getText());
        usuario.setEnderecoCidade(textFieldcadastroUsuarioEnderecoCidade.getText());
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.salvarUsuario(usuario);
        mensagem.mensagemInforma("Usuario cadastrado");
        inicializarTabelaUsuario();
    }*/

 /*
    *Método utilizado para editar o cadastro de um usuario
     */
 /*private void salvarCadastroUsuarioEditado() {
        Usuario usuario = new Usuario();

        usuario.setCodigo(idUsuarioParaEditar);
        usuario.setNome(textFieldcadastroUsuarioNome.getText());
        usuario.setFuncao(textFieldcadastroUsuarioFuncao.getText());
        usuario.setLogin(textFieldcadastroUsuarioLogin.getText());
        usuario.setSenha(textFieldcadastroUsuarioSenha.getText());
        usuario.setDataEntrada(textFieldcadastroUsuarioDataEntrada.getText());
        usuario.setDataSaida(textFieldcadastroUsuarioDataSaida.getText());
        usuario.setCpf(textFieldcadastroUsuarioCPF.getText());
        usuario.setRg(textFieldCadastroUsuarioRG.getText());
        usuario.setDataNascimento(textFieldcadastroUsuarioDataNascimento.getText());
        usuario.setNumeroTelefone(textFieldcadastroUsuarioNumeroContato.getText());
        usuario.setEmail(textFieldcadastroUsuarioEmail.getText());
        usuario.setEnderecoRua(textFieldcadastroUsuarioEnderecoRua.getText());
        usuario.setEnderecoNumero(textFieldcadastroUsuarioEnderecoNumero.getText());
        usuario.setEnderecoBairro(textFieldcadastroUsuarioEnderecoBairro.getText());
        usuario.setEnderecoCidade(textFieldcadastroUsuarioEnderecoCidade.getText());
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.editarUsuario(usuario);
        mensagem.mensagemInforma("Usuario editado");
        inicializarTabelaUsuario();
    }*/

 /* @FXML
    public void inicializarTabelaUsuario() {
        colUsuarioCod.setCellValueFactory(new PropertyValueFactory("codigo"));
        colUsuarioFuncao.setCellValueFactory(new PropertyValueFactory("funcao"));
        colUsuarioNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colUsuarioCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
        colUsuarioDaraNascimento.setCellValueFactory(new PropertyValueFactory("dataNascimento"));
        colUsuarioCelular.setCellValueFactory(new PropertyValueFactory("numeroTelefone"));
        colUsuarioLogin.setCellValueFactory(new PropertyValueFactory("login"));
        colUsuarioSenha.setCellValueFactory(new PropertyValueFactory("senha"));
        
        tabelaPesquisaUsuario.setItems(atualizaTabelaUsuario());
    }*/

 /*
    *Método para preencher a tabela pesquisa de usuario da aba usuarioeditarCadastroUsuario com os dados do banco
     */
 /*public ObservableList<Usuario> atualizaTabelaUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return FXCollections.observableArrayList(usuarioDAO.pesquisaUsuario(textFildpesquisaUsuario.getText()));
    }*/
 /*
    *
    *INICIO DA ABA CADASTRAR CLIENTE
    *
     */
 /*
    *Método para trocar senha e nome que aparece na interface
     */
    public void tabelasVazia() {

        List<Produto> listaProduto = new ArrayList<>();
        List<ProdutoVendido> listaProdutoVendido = new ArrayList<>();
        //List<Usuario> listaUsuario = new ArrayList<>();
        List<Cliente> listaCliente = new ArrayList<>();
        List<PagarVenda> listaPagarVenda = new ArrayList<>();
        /*
        Produto produto = new Produto();
        ProdutoVendido produtoVendido = new ProdutoVendido();
        Usuario usuario = new Usuario();
        Cliente cliente = new Cliente();
         */

        listaProduto.add(null);
        listaProdutoVendido.add(null);
        //listaUsuario.add(null);
        listaCliente.add(null);
        listaPagarVenda.add(null);

        TabPesquisaVenda.setItems(FXCollections.observableArrayList(listaProduto));
        TabAdiVend.setItems(FXCollections.observableArrayList(listaProdutoVendido));
        tabelaPesquisaProduto.setItems(FXCollections.observableArrayList(listaProduto));
        tabelaPesquisaCliente.setItems(FXCollections.observableArrayList(listaCliente));
        tabListaVendas.setItems(FXCollections.observableArrayList(listaPagarVenda));
        //tabelaPesquisaUsuario.setItems(FXCollections.observableArrayList(listaUsuario));
    }

    /*
    *Método para Fechar a Abas
     */
    @FXML
    private void fecharAbaProduto(ActionEvent event) {
        TabGeral.getSelectionModel().select(AbaVenda);
        TabGeral.getTabs().remove(AbaProdutos);
        List<Produto> listaProdutoaux = new ArrayList<>();
        listaProdutoaux.add(null);
        tabelaPesquisaProduto.setItems(FXCollections.observableArrayList(listaProdutoaux));
    }

    @FXML
    private void fecharAbaCliente(ActionEvent event) {
        TabGeral.getTabs().remove(AbaClientes);
    }

    private void fecharAbaCadastroCliente() {
        TabGeral.getTabs().remove(AbaCadastrarCliente);
    }

    public void fechaVenda() {
        TabGeral.getTabs().remove(AbaVenda);
    }

    public void fechaPagarDebito() {
        TabGeral.getTabs().remove(AbaPagarDebito);
    }

    @FXML
    public void fechaConfig() {
        TabGeral.getTabs().remove(AbaConfig);
        TabGeral.getSelectionModel().select(AbaVenda);
    }
    /*
    *Método para Exibir a Abas
     */
    @FXML
    private void exibirAbaVenda(ActionEvent event) {

        TabGeral.getTabs().clear();
        TabGeral.getTabs().add(0, AbaVenda);
        // TabGeral.getTabs().add(AbaVenda);
        TabGeral.getSelectionModel().select(AbaVenda);
        troca = 1;
    }

    private void exibirAbaCadastrarUsuario() {

        TabGeral.getTabs().add(2, AbaCadastrarProduto);
    }

    @FXML
    private void exibirAbaProduto(ActionEvent event) {
        TabGeral.getTabs().clear();
        TabGeral.getTabs().add(0, AbaVenda);
        TabGeral.getTabs().add(1, AbaProdutos);
        TabGeral.getSelectionModel().select(AbaProdutos);
        //TabGeral.getTabs().set(2, tabInicioPos0);
        troca = 2;

    }

    @FXML
    private void exibirAbaCliente(ActionEvent event) {
        TabGeral.getTabs().clear();
        TabGeral.getTabs().add(0, AbaVenda);
        TabGeral.getTabs().add(1, AbaClientes);
        TabGeral.getSelectionModel().select(AbaClientes);
        troca = 3;
    }

    @FXML
    private void exibirAbaPagarDebito(ActionEvent event) {
        TabGeral.getTabs().clear();
        TabGeral.getTabs().add(0, AbaVenda);
        TabGeral.getTabs().add(1, AbaPagarDebito);
        TabGeral.getSelectionModel().select(AbaPagarDebito);
    }

    @FXML
    private void exibirAbaConfig(ActionEvent event) {
        TabGeral.getTabs().clear();
        TabGeral.getTabs().add(0, AbaVenda);
        TabGeral.getTabs().add(1, AbaConfig);
        TabGeral.getSelectionModel().select(AbaConfig);
        troca = 0;
    }

    /*@FXML
    private void exibirAbaUsuario(ActionEvent event) {
        TabGeral.getTabs().set(1, AbaUsuarios);
        TabGeral.getSelectionModel().select(AbaUsuarios);
    }*/
 /*
    @FXML
    private void exibirAbaRelatorio(ActionEvent event) {
        TabGeral.getTabs().set(1, AbaRelatorio);
        TabGeral.getSelectionModel().select(AbaRelatorio);
    }
     */
    public void inicioAbasRemovidas() {
        //TabGeral.getTabs().remove(AbaVenda);
        TabGeral.getTabs().remove(AbaProdutos);
        TabGeral.getTabs().remove(AbaClientes);
        //TabGeral.getTabs().remove(AbaUsuarios);
        TabGeral.getTabs().remove(AbaRelatorio);

        TabGeral.getTabs().remove(AbaCadastrarProduto);
        TabGeral.getTabs().remove(AbaCadastrarCliente);
        //TabGeral.getTabs().remove(AbaCadastraUsuarios);
        TabGeral.getTabs().remove(AbaPagarDebito);
        TabGeral.getTabs().remove(AbaConfig);
    }

    @FXML
    private void exibirAbaAjuda(ActionEvent event) {
    }

    @FXML
    private void exibirAbaSair(ActionEvent event) {
        if(mensagem.mensagemConfirmacao("Encerrar Programa?", "Você deseja fechar o programa?").equals("Confirma")){
            Stage stage = (Stage) botaoMenuSair.getScene().getWindow();
            stage.close();
        }else{
            
        }
    }

    private String buscaNomeEmpresa() {
        LoginDAO loginDAO = new LoginDAO();
        ConfiguraUsuario nomeEmpresa = loginDAO.buscaDadosEmpresa();
        return nomeEmpresa.getNomeEmpresa();
    }
    
    @FXML
    private void trocarAbas(ActionEvent event){
        
        switch(troca){
            case 0:
                exibirAbaVenda(event);
                break;
            case 1:
                exibirAbaProduto(event);
                break;
            case 2:
                exibirAbaCliente(event);
                break;
            case 3:
                exibirAbaConfig(event);
                break;
            //case 4:
            //    exibirAbaCadastrarProduto(event);
            //    break;
            default:
                troca = 0;
                trocarAbas(event);
                break;
        }
    }

}
