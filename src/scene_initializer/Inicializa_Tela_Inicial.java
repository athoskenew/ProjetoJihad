/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene_initializer;

import controller.FXML_Tela_InicialController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JFrame;
import util.Mensagem;

public class Inicializa_Tela_Inicial extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Tela_Inicial.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setX(Screen.getPrimary().getVisualBounds().getMinX());
        stage.setY(Screen.getPrimary().getVisualBounds().getMinY());
        stage.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth() - (Screen.getPrimary().getVisualBounds().getWidth()) * 0.01);
        stage.setTitle("Sistema Gestão Financeira Bits Jr.");

        stage.setResizable(false);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
