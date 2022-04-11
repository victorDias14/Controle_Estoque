package controllers;

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loja adicionada");
        alert.setHeaderText("Loja adicionada com sucesso");
        alert.show();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);
    }
}
