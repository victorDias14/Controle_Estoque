package controllers;

import alerts.GenerateAlerts;
import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddLojaController {
    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txfCepLoja;

    @FXML
    private TextField txfCnpjLoja;

    @FXML
    private TextField txfCodigoLoja;

    @FXML
    private TextField txfNomeLoja;

    @FXML
    private TextField txfNumeroLoja;

    @FXML
    private TextField txfRuaLoja;

    @FXML
    void confirmar(ActionEvent event) {
        GenerateAlerts.lojaAlert();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);
    }
}
