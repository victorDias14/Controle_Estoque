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
import model.bo.ProdutoBo;
import model.dto.ProdutoDto;

public class ProdutoController {
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

        ProdutoDto objProdutoDto = new ProdutoDto();
        objProdutoDto.setCodEan(codEan);
        objProdutoDto.setCodInterno(codInterno);
        objProdutoDto.setNomeProduto(nomeProduto);
        objProdutoDto.setValorVenda(valorVenda);

        ProdutoBo objProdutoBo = new ProdutoBo();
        objProdutoBo.verificaCamposVazios(objProdutoDto);
    }

    @FXML
    void consultarInterno(ActionEvent event) {
        codInterno = txfCodInterno.getText();

        ProdutoDto objProdutoDto = new ProdutoDto();
        objProdutoDto.setCodInterno(codInterno);

        ProdutoBo objProdutoBo = new ProdutoBo();
        objProdutoBo.consulta(objProdutoDto, "interno");

        txfCodInterno.setText(objProdutoDto.getCodInterno());
        txfCodEan.setText(objProdutoDto.getCodEan());
        txfNomeProduto.setText(objProdutoDto.getNomeProduto());
        txfValorVenda.setText(objProdutoDto.getValorVenda());
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

                AddProdutoAlerts.apagaProdutoAlert();             
                    
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

    public void limpaCampos(){
        txfCodInterno.clear();
        txfCodEan.clear();
        txfNomeProduto.clear();
        txfQuantidade.clear();
        txfValorVenda.clear();
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
