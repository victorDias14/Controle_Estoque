package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;

    private static Scene login;
    private static Scene telaInicial;
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Controle de estoque");

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        login = new Scene(fxmlLogin);
        
        Parent fxmlTelaInicial = FXMLLoader.load(getClass().getResource("/views/TelaInicial.fxml"));
        telaInicial = new Scene(fxmlTelaInicial);

        primaryStage.setScene(login);     
        primaryStage.show();
        
    }

    //CRIAR ENUM PARA CADA TELA
    public static void changeScreen(String scr) {
        switch (scr) {
            case "main":
                stage.setScene(telaInicial);
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
