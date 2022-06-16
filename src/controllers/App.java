package controllers;

import enums.Screens;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;
    
    private static Scene login;
    private static Scene telaInicial;
    private static Scene adicionarProduto;
    private static Scene adicionarFornecedor;
    private static Scene adicionarUsuario;
    private static Scene adicionarSetor;
    private static Scene adicionarLoja;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Controle de estoque");

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        login = new Scene(fxmlLogin);
        
        Parent fxmlTelaInicial = FXMLLoader.load(getClass().getResource("/views/TelaInicial.fxml"));
        telaInicial = new Scene(fxmlTelaInicial);

        Parent fxmlAdicionarProduto = FXMLLoader.load(getClass().getResource("/views/AdicionarProduto.fxml"));
        adicionarProduto = new Scene(fxmlAdicionarProduto);

        Parent fxmlAdicionarFornecedor = FXMLLoader.load(getClass().getResource("/views/AdicionarFornecedor.fxml"));
        adicionarFornecedor = new Scene(fxmlAdicionarFornecedor);

        Parent fxmlAdicionarUsuario = FXMLLoader.load(getClass().getResource("/views/AdicionarUsuario.fxml"));
        adicionarUsuario = new Scene(fxmlAdicionarUsuario);
        
        Parent fxmlAdicionarSetor = FXMLLoader.load(getClass().getResource("/views/AdicionarSetor.fxml"));
        adicionarSetor = new Scene(fxmlAdicionarSetor);

        Parent fxmlAdicionarLoja = FXMLLoader.load(getClass().getResource("/views/AdicionarLoja.fxml"));
        adicionarLoja = new Scene(fxmlAdicionarLoja);        

        primaryStage.setScene(adicionarFornecedor);     
        primaryStage.show();
        
    }

    public static void changeScreen(Screens scr) {
        switch (scr) {
            case TELA_INICIAL:
                stage.setScene(telaInicial);
                break;

            case ADD_PRODUTO:
                stage.setScene(adicionarProduto);
                break;

            case ADD_FORNECEDOR:
                stage.setScene(adicionarFornecedor);
                break;

            case ADD_USUARIO:
                stage.setScene(adicionarUsuario);
                break;

            case ADD_SETOR:
                stage.setScene(adicionarSetor);
                break;

            case ADD_LOJA:
                stage.setScene(adicionarLoja);
                break;

        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
