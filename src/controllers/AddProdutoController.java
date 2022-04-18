package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.AddProdutoAlerts;
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

    private String codEan = null;
    private String codInterno = null;
    private String nomeProduto = null;
    private String valorVenda = null;

    private String codInternoOld = null;
    private String codEanOld = null;
    private String nomeProdutoOld = null;
    private String valorVendaOld = null;

    private String sqlAlteraProduto;

    @FXML
    void adicionar(ActionEvent event) {
        codEan = txfCodEan.getText();
        codInterno = txfCodInterno.getText();
        nomeProduto = txfNomeProduto.getText();
        valorVenda = txfValorVenda.getText();

        if (codEan == "" || codInterno == "" || nomeProduto == "") {
            AddProdutoAlerts.produtoErrorAlertEmptyField();
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

                AddProdutoAlerts.produtoAlert();             
                    
            }

            catch (SQLException e) {
                e.printStackTrace();
                AddProdutoAlerts.produtoErrorAlertGeneric();
            }
        }
    }

    @FXML
    void consultarInterno(ActionEvent event) {
        codInterno = txfCodInterno.getText();

        if (codInterno == "") {
            AddProdutoAlerts.consultaProdutoInternoErrorAlert();
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
                        preencheCamposConsulta(rs);
                    }
                    
                }
                
                else {
                    AddProdutoAlerts.consultaProdutoErrorAlert();
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
        codEan = txfCodEan.getText();

        if (codEan == "") {
            AddProdutoAlerts.consultaProdutoErrorAlert();
            limpaCampos();
        }

        else {

            String sqlConsultaEan = "SELECT * FROM produto WHERE codigo_ean = ?";

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlConsultaEan);
                st.setString(1, codEan);
                rs = st.executeQuery();

                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        txfCodInterno.setText(rs.getString("codigo_interno"));
                        preencheCamposConsulta(rs);
                        
                    }                    
                }
                
                else {
                    AddProdutoAlerts.consultaProdutoErrorAlert();
                    limpaCampos();
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void alterar(ActionEvent event) {

        codInterno = txfCodInterno.getText();
        codEan = txfCodEan.getText();
        nomeProduto = txfNomeProduto.getText();
        valorVenda = txfValorVenda.getText();
        
        if (codInterno == "" || codEan == "" || nomeProduto == "") {
            AddProdutoAlerts.produtoErrorAlertEmptyField();
        }
        
        else {

            double valorVendaConvertido = Double.parseDouble(valorVenda);

            int codInternoOldConvertido = Integer.parseInt(codInternoOld);
            Long codEanOldConvertido = Long.parseLong(codEanOld);
            double valorVendaOldConvertido = Double.parseDouble(valorVendaOld);
            
            sqlAlteraProduto = "UPDATE produto SET nome_produto = ?, valor_venda = ? WHERE codigo_interno = ? AND codigo_ean = ? AND nome_produto = ? AND valor_venda = ?";            

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlAlteraProduto);
                st.setString(1, nomeProduto);
                st.setDouble(2, valorVendaConvertido);
                st.setInt(3, codInternoOldConvertido);
                st.setLong(4, codEanOldConvertido);
                st.setString(5, nomeProdutoOld);
                st.setDouble(6, valorVendaOldConvertido);
                st.executeUpdate();

                AddProdutoAlerts.alteraProdutoAlert();
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void apagar(ActionEvent event) {
        codEan = txfCodEan.getText();
        codInterno = txfCodInterno.getText();

        if (codEan == "" || codInterno == "" || nomeProduto == "") {
            AddProdutoAlerts.apagaProdutoErrorAlert();
        }

        else {
            String sqlDeleteProduto = "DELETE FROM produto WHERE codigo_interno = ?;";
            String sqlDeleteEan = "DELETE FROM ean WHERE codigo_ean = ?;";

            Long codEanConvertido = Long.parseLong(codEan);
            int codInternoConvertido = Integer.parseInt(codInterno);

            try {
                conn = DB.getConnection();

                st = conn.prepareStatement(sqlDeleteEan);
                st.setLong(1, codEanConvertido);
                st.executeUpdate();

                st = conn.prepareStatement(sqlDeleteProduto);
                st.setInt(1, codInternoConvertido);
                st.executeUpdate();

                AddProdutoAlerts.produtoAlert();             
                    
            }

            catch (SQLException e) {
                e.printStackTrace();
                AddProdutoAlerts.produtoErrorAlertGeneric();
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

    void limpaCampos(){
        txfCodInterno.setText("");
        txfCodEan.setText("");
        txfNomeProduto.setText("");
        txfQuantidade.setText("");
        txfValorVenda.setText("");
    }

    void preencheCamposConsulta(ResultSet rs) {
        try {
            this.rs = rs;
            txfNomeProduto.setText(this.rs.getString("nome_produto"));
            txfQuantidade.setText(rs.getString("quantidade_estoque"));
            txfValorVenda.setText(rs.getString("valor_venda"));

            codInternoOld = txfCodInterno.getText();
            codEanOld = txfCodEan.getText();
            nomeProdutoOld = txfNomeProduto.getText();
            valorVendaOld = txfValorVenda.getText();
        }

        catch (Exception e) {
            e.printStackTrace();
        }        
    }
}
