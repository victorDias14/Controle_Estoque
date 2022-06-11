package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import alerts.LojaAlerts;
import db.DB;
import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.bo.LojaBo;
import model.dto.LojaDto;

public class LojaController {
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

        if (codigoLoja.isBlank() || nomeLoja.isBlank() || cnpjLoja.isBlank() || ruaLoja.isBlank() || numeroLoja.isBlank() || cepLoja.isBlank()) {
            LojaAlerts.campoVazioLojaAlert();
        }

        else {
            
            LojaDto objLojaDto = new LojaDto();
            objLojaDto.setCodigoLoja(codigoLoja);
            objLojaDto.setNomeLoja(nomeLoja);
            objLojaDto.setCnpjLoja(cnpjLoja);
            objLojaDto.setRuaLoja(ruaLoja);
            objLojaDto.setNumeroLoja(numeroLoja);
            objLojaDto.setCepLoja(cepLoja);

            LojaBo objLojaBo = new LojaBo(); 
            int retorno = objLojaBo.adicionar(objLojaDto);

            if(retorno == 0){
                LojaAlerts.lojaJaCadastradaAlert();
            }

            else if(retorno == 1){
                LojaAlerts.lojaAdicionadaAlert();
            }

            else{

            }
        }
    }

    @FXML
    void apagar(ActionEvent event) {
        codigoLoja = txfCodigoLoja.getText();

        if(codigoLoja == "") {
            LojaAlerts.campoVazioLojaAlert();
        }

        else {

            String sqlDeleteLoja = "DELETE FROM loja WHERE codigo = ?";

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlDeleteLoja);
                st.setString(1, codigoLoja);
                st.executeUpdate();

                LojaAlerts.lojaApagadaAlert();
            } 
            
            catch (Exception e) {
                
            }
        }

    }
        
    @FXML
    void consultar(ActionEvent event) {
        codigoLoja = txfCodigoLoja.getText();

        if (codigoLoja.isBlank()) {
            LojaAlerts.campoVazioLojaAlert();
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
                    LojaAlerts.codigoNaoExisteAlert();
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
