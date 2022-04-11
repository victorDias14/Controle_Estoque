package controllers;

import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

        if (usuario.equals("Victor") && senha.equals("123456")){
            App.changeScreen(Screens.TELA_INICIAL);
        }

        else {
            System.out.println("Erroooooou");
        }

    }
    
}
