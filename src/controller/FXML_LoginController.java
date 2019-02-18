/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import data_access.ConnectionFactory;
import data_access.LoginDAO;
import java.awt.event.KeyAdapter;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.ConfiguraUsuario;
import model.Login_Usuario;
import scene_initializer.Inicializa_Tela_Inicial;
import util.Mensagem;

/**
 *
 * @author TIAGOARAUJO
 */
public class FXML_LoginController implements Initializable {

    Mensagem mensagens = new Mensagem();

    @FXML
    private Button botaoFechar;
    private TextField campoTextoUsuario;
    @FXML
    private Button botaoEntrar;
    @FXML
    private PasswordField campoTextoSenha;
    @FXML
    private Label labelNomeEmpresa;

    private String senhaCorreta;

    @FXML

    private void clicouEntrar() {

        if (!(campoTextoSenha.getText().equals("")) && !(campoTextoSenha.getText() == null)) {
            String senha = campoTextoSenha.getText();

            if (senha.equals("123")) {

                try {
                    Stage stage = (Stage) botaoEntrar.getScene().getWindow();
                    stage.close();
                    new Inicializa_Tela_Inicial().start(new Stage());
                } catch (Exception ex) {
                    mensagens.mensagemErro("Erro ao fazer login!");
                }
            } else {
                mensagens.mensagemErro("Falha ao realizar o login. Provavelmente você digitou a senha errada!");
                campoTextoSenha.setText("");
            }
        }
    }

    @FXML
    public void clicouFechar(ActionEvent event) {
        Stage stage = (Stage) botaoFechar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConfiguraUsuario usuario = new LoginDAO().buscaDadosEmpresa();
        labelNomeEmpresa.setText(usuario.getNomeEmpresa());
        senhaCorreta = usuario.getSenhaAtual();
        labelNomeEmpresa.setWrapText(true);
        campoTextoSenha.setFocusTraversable(true);
        campoTextoSenha.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER)
                    clicouEntrar();
            }
            
        });
        
    }

}
