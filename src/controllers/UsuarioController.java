package controllers;

import alerts.UsuarioAlerts;
import enums.Screens;
import enums.UsuarioEnums;
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
    private TextField txfSenhaUsuario;

    @FXML
    void confirmar(ActionEvent event) {

        if (txfCodigoUsuario.getText().isBlank() || txfSenhaUsuario.getText().isBlank()) {
            UsuarioAlerts.criarUsuarioErrorAlert();
        }

        else {
            UsuarioDto objUsuarioDto = new UsuarioDto();
            objUsuarioDto.setCodigoUsuario(txfCodigoUsuario.getText());
            objUsuarioDto.setSenhaUsuario(txfSenhaUsuario.getText());

            UsuarioBo objUsuarioBo = new UsuarioBo();
            UsuarioEnums retornoAdd = objUsuarioBo.adiciona(objUsuarioDto);

            switch(retornoAdd){
                case JA_CADASTRADO:
                    UsuarioAlerts.usuarioExisteAlert();
                    break;

                case SUCESSO_CADASTRO:
                    UsuarioAlerts.criarUsuarioConfirmationAlert();
                    break;

                case ERRO_GENERICO:
                    UsuarioAlerts.usuarioGenericAlert();
                    break;

                default:
                    break;
            }
        }
    }

    @FXML
    void apagar(ActionEvent event) {
        if (txfCodigoUsuario.getText().isBlank()) {
            UsuarioAlerts.apagarUsuarioErrorAlert();
        }

        else {
            UsuarioDto objUsuarioDto = new UsuarioDto();
            objUsuarioDto.setCodigoUsuario(txfCodigoUsuario.getText());

            UsuarioBo objUsuarioBo = new UsuarioBo();
            UsuarioEnums retornoDel = objUsuarioBo.apaga(objUsuarioDto);
            
            switch(retornoDel){
                case SUCESSO_APAGAR:
                    UsuarioAlerts.apagarUsuarioConfirmationAlert();
                    break;

                case NAO_CADASTRADO:
                    UsuarioAlerts.apagarUsuarioInexistenteErrorAlert();
                    break;

                case ERRO_GENERICO:
                    UsuarioAlerts.usuarioGenericAlert();
                    break;

                default:
                    break;
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);
    }
}
