package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;
    
    //private static Scene login;
    private static Scene telaInicial;
    private static Scene adicionarProduto;
    private static Scene adicionarFornecedor;
    private static Scene adicionarUsuario;
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Controle de estoque");

        //Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        //login = new Scene(fxmlLogin);
        
        Parent fxmlTelaInicial = FXMLLoader.load(getClass().getResource("/views/TelaInicial.fxml"));
        telaInicial = new Scene(fxmlTelaInicial);

        Parent fxmlAdicionarProduto = FXMLLoader.load(getClass().getResource("/views/AdicionarProduto.fxml"));
        adicionarProduto = new Scene(fxmlAdicionarProduto);

        Parent fxmlAdicionarFornecedor = FXMLLoader.load(getClass().getResource("/views/AdicionarFornecedor.fxml"));
        adicionarFornecedor = new Scene(fxmlAdicionarFornecedor);

        Parent fxmlAdicionarUsuario = FXMLLoader.load(getClass().getResource("/views/AdicionarUsuario.fxml"));
        adicionarUsuario = new Scene(fxmlAdicionarUsuario);

        primaryStage.setScene(telaInicial);     
        primaryStage.show();
        
    }

    //CRIAR ENUM PARA CADA TELA
    public static void changeScreen(String scr) {
        switch (scr) {
            case "main":
                stage.setScene(telaInicial);
                break;

            case "addProduto":
                stage.setScene(adicionarProduto);
                break;

            case "addFornecedor":
                stage.setScene(adicionarFornecedor);
                break;

            case "addUsuario":
                stage.setScene(adicionarUsuario);
                break;

        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
