package controllers;

import alerts.GenerateAlerts;
import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddFornecedorController {
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
        GenerateAlerts.fornecedorAlert();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);

    }
}
