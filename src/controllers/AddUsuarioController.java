package controllers;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.AddUsuarioAlerts;
import db.DB;
import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddUsuarioController {
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

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    @FXML
    void confirmar(ActionEvent event) {
        codigoUsuario = txfCodigoUsuario.getText();
        senhaUsuario = txfSenhaUsuario.getText();

        if (codigoUsuario == "" && senhaUsuario == "") {
            AddUsuarioAlerts.criarUsuarioErrorAlert();
        }

        else {
            String sqlInsertUsuario = "INSERT INTO usuarios (login, senha) VALUES (?, ?)";

            try {
                String senhaCripto = criptografia(senhaUsuario);
                
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlInsertUsuario);
                st.setString(1, codigoUsuario);
                st.setString(2, senhaCripto);
                st.executeUpdate();
                
                AddUsuarioAlerts.criarUsuarioConfirmationAlert();
            }

            catch (SQLException e) {
                 e.printStackTrace();
            }            
        }
    }

    @FXML
    void apagar(ActionEvent event) {
        codigoUsuario = txfCodigoUsuario.getText();

        if (codigoUsuario == "") {
            AddUsuarioAlerts.apagarUsuarioErrorAlert();
        }

        else {
            String sqlDeleteUsuario = "DELETE FROM usuarios WHERE login = ?";

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlDeleteUsuario);
                st.setString(1, codigoUsuario);
                st.executeUpdate();

                AddUsuarioAlerts.apagarUsuarioConfirmationAlert();
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        DB.closeStatement(st);
        DB.closeResultset(rs);
        DB.closeConnection();

        App.changeScreen(Screens.TELA_INICIAL);
    }

    String criptografia(String senha) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();

            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }

            return hexString.toString();
        }

        catch(Exception e) {
            e.printStackTrace();
        }
        return senha;        
    }
}
