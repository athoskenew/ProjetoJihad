package util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Mensagem {
    String escolhaRetorno = "";
    
    public void mensagemErro(String menssagem){
        Alert aviso = new Alert(Alert.AlertType.ERROR);
        String audioMenssagem = "Erro: " + menssagem;
        aviso.setTitle(audioMenssagem);
        aviso.setHeaderText(null);
        aviso.setContentText("Erro");
        aviso.showAndWait();
    }
    
    public void mensagemInforma(String menssagem){
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle(menssagem);
        aviso.setHeaderText(null);
        aviso.setContentText(menssagem);
        
        aviso.showAndWait();
    }
    
    public String mensagemConfirmacao(String titulo, String headText){
        Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType botaoConfirma = new ButtonType("Confirmar");
        ButtonType botaoCancela = new ButtonType("Cancelar");
        
        String audioMenssagem = headText;
        aviso.setTitle(audioMenssagem);
        aviso.setHeaderText(titulo);
        aviso.getButtonTypes().setAll(botaoConfirma, botaoCancela);
        aviso.showAndWait().ifPresent(escolha ->{
            if(escolha == botaoConfirma){
                escolhaRetorno = "Confirma";
            }else
                escolhaRetorno = "Cancela";
        });
        return escolhaRetorno;
    }
}
