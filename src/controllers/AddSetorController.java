package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.AddSetorAlerts;
import db.DB;
import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddSetorController {

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnApagar;

    @FXML
    private TextField txfSetor;

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    private String nomeSetor;

    @FXML
    void confirmar(ActionEvent event) {
        nomeSetor = txfSetor.getText();

        if (nomeSetor == "") {
            AddSetorAlerts.addSetorErrorAlert();
        }

        else {
            String sqlConfereExiste = "SELECT nome FROM setores WHERE nome = ?";

            try {
                conn = DB.getConnection();

                st = conn.prepareStatement(sqlConfereExiste);
                st.setString(1, nomeSetor);
                rs = st.executeQuery();

                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        if (rs.getString("nome").equals(nomeSetor)) {
                            AddSetorAlerts.setorExisteErrorAlert();
                        }
                    }
                }

                else {
                    String sqlInsertSetor = "INSERT INTO setores (nome) VALUES (?)";
                    
                    try {
                        st = conn.prepareStatement(sqlInsertSetor);
                        st.setString(1, nomeSetor);
                        st.executeUpdate();

                        AddSetorAlerts.addSetorConfirmationAlert();

                    }

                    catch(SQLException e) {
                        e.printStackTrace();
                    }
                    
                }                
            }
            
            catch (SQLException e) {
               e.printStackTrace();
            }
        }
    }

    @FXML
    void apagar(ActionEvent event) {
        nomeSetor = txfSetor.getText();

        if (nomeSetor == "") {
            AddSetorAlerts.addSetorErrorAlert();
        }

        else {
            try {
                String sqlDeleteSetor = "DELETE FROM setores WHERE nome = ?";

                conn = DB.getConnection();
                st = conn.prepareStatement(sqlDeleteSetor);
                st.setString(1, nomeSetor);
                st.executeUpdate();

                AddSetorAlerts.deleteSetorConfirmationAlert();
            } 
            
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);

        DB.closeResultset(rs);
        DB.closeStatement(st);
        DB.closeConnection();
    }
    
}
