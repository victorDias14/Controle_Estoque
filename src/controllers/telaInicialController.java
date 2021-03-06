package controllers;

import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class TelaInicialController {

    @FXML
    private MenuItem btnFornecedor;

    @FXML
    private MenuItem btnLoja;

    @FXML
    private MenuItem btnProduto;

    @FXML
    private MenuItem btnSetor;

    @FXML
    private MenuItem btnUsuario;

    @FXML
    void cadFornecedor(ActionEvent event) {
        App.changeScreen(Screens.ADD_FORNECEDOR);
    }

    @FXML
    void cadLoja(ActionEvent event) {
        App.changeScreen(Screens.ADD_LOJA);
    }

    @FXML
    void cadProduto(ActionEvent event) {
        App.changeScreen(Screens.ADD_PRODUTO);
    }

    @FXML
    void cadSetor(ActionEvent event) {
        App.changeScreen(Screens.ADD_SETOR);
    }

    @FXML
    void cadUsuario(ActionEvent event) {
        App.changeScreen(Screens.ADD_USUARIO);
    }
    
}
