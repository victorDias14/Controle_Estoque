package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class adicionarFornecedorController {

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txfCnpj;

    @FXML
    private TextField txfNomeFornecedor;

    @FXML
    void confirmar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fornecedor adicionado");
        alert.setHeaderText("Fornecedor adicionado com sucesso");
        alert.show();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen("main");

    }
}
