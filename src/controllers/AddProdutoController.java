package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.GenerateAlerts;
import db.DB;
import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddProdutoController {
    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField txfCodEan;

    @FXML
    private TextField txfCodInterno;

    @FXML
    private TextField txfNomeProduto;

    @FXML
    private TextField txfQuantidade;

    @FXML
    private TextField txfValorVenda;

    private Connection conn = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;

    @FXML
    void adicionar(ActionEvent event) {
        String codEan = txfCodEan.getText();
        String codInterno = txfCodInterno.getText();
        String nomeProduto = txfNomeProduto.getText();
        String valorVenda = txfValorVenda.getText();

        if (codEan == "" || codInterno == "" || nomeProduto == "") {
            GenerateAlerts.produtoErrorAlertEmptyField();
        }

        else {
            String sqlInsertProduto = "INSERT INTO produto (codigo_interno, codigo_ean, nome_produto, valor_venda) VALUES (?, ?, ?, ?)";
            String sqlInsertEan = "INSERT INTO ean (codigo_ean, codigo_interno) VALUES (?, ?)";

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlInsertProduto);
                st.setString(1, codInterno);
                st.setString(2, codEan);
                st.setString(3, nomeProduto); 
                st.setString(4, valorVenda);
                st.executeUpdate();

                st = conn.prepareStatement(sqlInsertEan);
                st.setString(1, codEan);
                st.setString(2, codInterno);
                st.executeUpdate();

                GenerateAlerts.produtoAlert();             
                    
            }

            catch (SQLException e) {
                e.printStackTrace();
                GenerateAlerts.produtoErrorAlertGeneric();
            }
        }
    }

    @FXML
    void consultarInterno(ActionEvent event) {
        String codInterno = txfCodInterno.getText();

        if (codInterno == "") {
            GenerateAlerts.consultaProdutoInternoErrorAlert();
            limpaCampos();
        }

        else {

            String sqlConsultaInterno = "SELECT * FROM produto WHERE codigo_interno = ?";

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlConsultaInterno);
                st.setString(1, codInterno);
                rs = st.executeQuery();

                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        txfCodEan.setText(rs.getString("codigo_ean"));
                        txfNomeProduto.setText(rs.getString("nome_produto"));
                        txfQuantidade.setText(rs.getString("quantidade_estoque"));
                        txfValorVenda.setText(rs.getString("valor_venda"));
                    }                    
                }
                
                else {
                    GenerateAlerts.consultaProdutoInternoInexistenteAlert();
                    limpaCampos();
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }        
    }

    @FXML
    void consultarEan(ActionEvent event) {

    }

    @FXML
    void alterar(ActionEvent event) {
        
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);

        DB.closeStatement(st);
        DB.closeResultset(rs);
        DB.closeConnection();
        
    }

    void limpaCampos(){
        txfCodEan.setText("");
        txfNomeProduto.setText("");
        txfQuantidade.setText("");
        txfValorVenda.setText("");
    }
}
