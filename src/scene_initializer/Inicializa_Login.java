/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene_initializer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Tiago
 */
public class Inicializa_Login extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Tela de Login");
        stage.setResizable(false);
        //stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight()); 
        //stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        //stage.setX(Screen.getPrimary().getVisualBounds().getMinX());
        //stage.setY(Screen.getPrimary().getVisualBounds().getMinY());
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
