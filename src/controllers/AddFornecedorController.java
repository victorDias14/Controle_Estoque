package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.AddFornecedorAlerts;
import db.DB;
import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddFornecedorController {
    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txfCnpj;

    @FXML
    private TextField txfNomeFornecedor;

    private String nomeFornecedor;
    private String cnpj;
    private Long cnpjLong;
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    @FXML
    void adicionar(ActionEvent event) {
        
        nomeFornecedor = txfNomeFornecedor.getText();
        cnpj = txfCnpj.getText();

        if (nomeFornecedor == "" || cnpj == "") {
            AddFornecedorAlerts.fornecedorAddErrorAlert();
        }

        else {
            cnpjLong = Long.parseLong(cnpj);
            String sqlInsertFornecedor = "INSERT INTO fornecedores (nome, cnpj) VALUES (?, ?)";

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlInsertFornecedor);
                st.setString(1, nomeFornecedor);
                st.setLong(2, cnpjLong);
                st.executeUpdate();
            } 
            
            catch (SQLException e) {
                e.printStackTrace();
            }

            AddFornecedorAlerts.fornecedorAlert();
        }        
    }

    @FXML
    void apagar(ActionEvent event) {
        cnpj = txfCnpj.getText();

        if (cnpj == "") {
            AddFornecedorAlerts.fornecedorConsultaErrorAlert();
        }

        else {
            String sqlDeleteFornecedor = "DELETE FROM fornecedores WHERE cnpj = ?";
            cnpjLong = Long.parseLong(cnpj);

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlDeleteFornecedor);
                st.setLong(1, cnpjLong);
                st.executeUpdate();
                
            } 
            
            catch (SQLException e) {
                e.printStackTrace();
            }

            AddFornecedorAlerts.apagaFornecedorAlert();
        }
    }

    @FXML
    void consultar(ActionEvent event) {
        cnpj = txfCnpj.getText();

        if (cnpj == "") {
            AddFornecedorAlerts.fornecedorConsultaErrorAlert();
        }

        else {

            String sqlConsultaFornecedor = "SELECT nome FROM fornecedores WHERE cnpj = ?";

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlConsultaFornecedor);
                st.setString(1, cnpj);
                rs = st.executeQuery();

                if(rs.isBeforeFirst()) {
                    while(rs.next()) {
                        txfNomeFornecedor.setText(rs.getString("nome"));
                    }
                }

                else {
                    AddFornecedorAlerts.fornecedorConsultaInformationAlert();
                    txfCnpj.clear();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }        
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);

        DB.closeStatement(st);
        DB.closeResultset(rs);
        DB.closeConnection();
    }
}
