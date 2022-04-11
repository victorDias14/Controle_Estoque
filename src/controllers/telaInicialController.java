package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class telaInicialController {
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
        App.changeScreen("addFornecedor");
    }

    @FXML
    void cadLoja(ActionEvent event) {
        App.changeScreen("addLoja");
    }

    @FXML
    void cadProduto(ActionEvent event) {
        App.changeScreen("addProduto");
    }

    @FXML
    void cadSetor(ActionEvent event) {
        App.changeScreen("addSetor");
    }

    @FXML
    void cadUsuario(ActionEvent event) {
        App.changeScreen("addUsuario");
    }
}
