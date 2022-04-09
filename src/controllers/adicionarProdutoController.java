package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class adicionarProdutoController {

    @FXML
    private Button btnVoltar;

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen("main");
    }
    
}
