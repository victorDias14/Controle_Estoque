package controllers;

import alerts.LoginAlerts;
import enums.Screens;
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

        if(username.getText().isBlank() || password.getText().isBlank()){
            LoginAlerts.loginInformationAlert();
        }

        else {
            LoginDto objUsuarioDto = new LoginDto();
            objUsuarioDto.setUsuario(username.getText());
            objUsuarioDto.setSenha(password.getText());

            LoginBo objUsuarioBo = new LoginBo();
            int consulta = objUsuarioBo.validaSenha(objUsuarioDto);

            if(consulta == 0){
                LoginAlerts.loginErrorAlert();
            }

            else if(consulta == 1){
                App.changeScreen(Screens.TELA_INICIAL);
            }

            else {
                LoginAlerts.loginErrorConsultAlert();
            }
            
        }      

    }
}
