package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.LoginAlerts;
import db.DB;
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

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    @FXML
    void fazerLogin(ActionEvent event) {
        String usuario = username.getText();
        String senha = password.getText();

        if (usuario == "" && senha == "") {
            LoginAlerts.loginInformationAlert();
        }

        else {
            var criptografia = new AddUsuarioController();
            String senhaCriptoDigitada = criptografia.criptografia(senha);
            String senhaCriptoBanco = null;
            String sqlLogin = "SELECT senha FROM usuarios WHERE login = ?";

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlLogin);
                st.setString(1, usuario);
                rs = st.executeQuery();

                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        senhaCriptoBanco = rs.getString("senha");
                    }

                    if (senhaCriptoDigitada.equals(senhaCriptoBanco)) {
                        App.changeScreen(Screens.TELA_INICIAL);

                        DB.closeResultset(rs);
                        DB.closeStatement(st);
                        DB.closeConnection();
                    }

                    else {
                        LoginAlerts.loginErrorAlert();
                    }
                }

                else {
                    LoginAlerts.loginErrorAlert();
                }                                
            }

            catch (SQLException e) {
                e.printStackTrace();
            }

        }        
    }
}
