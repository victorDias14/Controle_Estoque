package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.bo.LoginBo;
import model.dto.LoginDto;

public class LoginController {

    @FXML
    private Button btnlogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void fazerLogin(ActionEvent event) {

        String usuario = username.getText();
        String senha = password.getText();

        LoginDto objUsuarioDto = new LoginDto();
        objUsuarioDto.setUsuario(usuario);
        objUsuarioDto.setSenha(senha);

        LoginBo objUsuarioBo = new LoginBo();
        objUsuarioBo.verificaCamposVazios(objUsuarioDto);

    }
}
