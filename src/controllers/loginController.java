package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        String sqlLogin = "SELECT * FROM usuarios WHERE login = " + usuario + " AND senha = " + senha;
        String usuarioBanco = null;
        String senhaBanco = null;

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sqlLogin);            
            
            if (rs != null) {
                while (rs.next()) {
                    usuarioBanco = rs.getString("login");
                    senhaBanco = rs.getString("senha");
                }                

                if (usuarioBanco != null && usuario.equals(usuarioBanco)) {
                    if (senha.equals(senhaBanco)) {
                        App.changeScreen(Screens.TELA_INICIAL);
                        DB.closeResultset(rs);
                        DB.closeStatement(st);
                        DB.closeConnection();
                    }

                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erro de login");
                        alert.setHeaderText("Usuário ou senha inválidos ou inexistentes");
                        alert.show(); 
                    }
                }

                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro de login");
                    alert.setHeaderText("Usuário ou senha inválidos ou inexistentes");
                    alert.show(); 
                }
            }
        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
