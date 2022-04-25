package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.AddLojaAlerts;
import db.DB;
import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddLojaController {
    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnApagar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txfCepLoja;

    @FXML
    private TextField txfCnpjLoja;

    @FXML
    private TextField txfCodigoLoja;

    @FXML
    private TextField txfNomeLoja;

    @FXML
    private TextField txfNumeroLoja;

    @FXML
    private TextField txfRuaLoja;

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    private String codigoLoja;
    private String nomeLoja;
    private String cnpjLoja;
    private String ruaLoja;
    private String numeroLoja;
    private String cepLoja;

    @FXML
    void adicionar(ActionEvent event) {
        codigoLoja = txfCodigoLoja.getText();
        nomeLoja = txfNomeLoja.getText();
        cnpjLoja = txfCnpjLoja.getText();
        ruaLoja = txfRuaLoja.getText();
        numeroLoja = txfNumeroLoja.getText();
        cepLoja = txfCepLoja.getText();

        if (codigoLoja == "" || nomeLoja == "" || cnpjLoja == "" || ruaLoja == "" || numeroLoja == "" || cepLoja == "") {
            AddLojaAlerts.campoVazioLojaAlert();
        }

        else {
            String sqlLojaExiste = "SELECT codigo, nome, cnpj FROM loja WHERE codigo = ? AND nome = ? AND cnpj = ?";

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlLojaExiste);
                st.setString(1, codigoLoja);
                st.setString(2, nomeLoja);
                st.setString(3, cnpjLoja);
                rs = st.executeQuery();

                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        if (rs.getString("codigo").equals(codigoLoja)) {
                            AddLojaAlerts.codigoExisteLojaAlert();
                        }
                    }
                }

                else {
                    String sqlInsertLoja = "INSERT INTO loja VALUES (?, ?, ?, ?, ?, ?)";

                    try {
                        st = conn.prepareStatement(sqlInsertLoja);
                        st.setString(1, codigoLoja);
                        st.setString(2, nomeLoja);
                        st.setString(3, cnpjLoja);
                        st.setString(4, ruaLoja);
                        st.setString(5, numeroLoja);
                        st.setString(6, cepLoja);
                        st.executeUpdate();

                        AddLojaAlerts.lojaAdicionadaAlert();                        
                    }

                    catch (SQLException e) {
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

    }
        
    @FXML
    void consultar(ActionEvent event) {
        codigoLoja = txfCodigoLoja.getText();

        if (codigoLoja.isBlank()) {
            AddLojaAlerts.campoVazioLojaAlert();
        }
        
        else {
            String sqlConsultaLoja = "SELECT * FROM loja WHERE codigo = ?";

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlConsultaLoja);
                st.setString(1, codigoLoja);
                rs = st.executeQuery();

                if (rs.isBeforeFirst()) {
                    while(rs.next()) {
                        txfNomeLoja.setText(rs.getString("nome"));
                        txfCnpjLoja.setText(rs.getString("cnpj"));
                        txfRuaLoja.setText(rs.getString("rua"));
                        txfNumeroLoja.setText(rs.getString("numero"));
                        txfCepLoja.setText(rs.getString("cep"));
                    }
                }

                else {
                    AddLojaAlerts.codigoNaoExisteAlert();
                }

            } 
            
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        DB.closeResultset(rs);
        DB.closeStatement(st);
        DB.closeConnection();

        App.changeScreen(Screens.TELA_INICIAL);
    }
}
