package controllers;

import alerts.UsuarioAlerts;
import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.bo.UsuarioBo;
import model.dto.UsuarioDto;

public class UsuarioController {
    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txfCodigoUsuario;

    @FXML
    private TextField txfNomeUsuario;

    @FXML
    private TextField txfSenhaUsuario;

    private String codigoUsuario;
    private String senhaUsuario;

    @FXML
    void confirmar(ActionEvent event) {
        UsuarioDto objUsuarioDto = new UsuarioDto();

        codigoUsuario = txfCodigoUsuario.getText();
        senhaUsuario = txfSenhaUsuario.getText();

        if (codigoUsuario == "" || senhaUsuario == "") {
            UsuarioAlerts.criarUsuarioErrorAlert();
        }

        else {
            objUsuarioDto.setCodigoUsuario(codigoUsuario);
            objUsuarioDto.setSenhaUsuario(senhaUsuario);

            UsuarioBo objUsuarioBo = new UsuarioBo();
            objUsuarioBo.adiciona(objUsuarioDto);
        }
    }

    @FXML
    void apagar(ActionEvent event) {
        codigoUsuario = txfCodigoUsuario.getText();

        if (codigoUsuario == "") {
            UsuarioAlerts.apagarUsuarioErrorAlert();
        }

        else {
            UsuarioDto objUsuarioDto = new UsuarioDto();
            objUsuarioDto.setCodigoUsuario(codigoUsuario);

            UsuarioBo objUsuarioBo = new UsuarioBo();
            objUsuarioBo.apaga(objUsuarioDto);           
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);
    }
}
