package controllers;

import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddProdutoController {
    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField txfCodEan;

    @FXML
    private TextField txfCodInterno;

    @FXML
    private TextField txfDescricao;

    @FXML
    private TextField txfNomeProduto;

    @FXML
    private TextField txfQuantidade;

    @FXML
    private TextField txfValorMedio;

    @FXML
    private TextField txfValorVenda;

    @FXML
    void confirmar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produto adicionado");
        alert.setHeaderText("Produto adicionado com sucesso");
        alert.show();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);
    }
}
