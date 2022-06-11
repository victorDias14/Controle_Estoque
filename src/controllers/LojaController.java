package controllers;

import alerts.LojaAlerts;
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

    private String codigoLoja;
    private String nomeLoja;
    private String cnpjLoja;
    private String ruaLoja;
    private String numeroLoja;
    private String cepLoja;

    private int retorno;

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
                LojaAlerts.erroGenerico();
            }
        }
    }

    @FXML
    void apagar(ActionEvent event) {
        codigoLoja = txfCodigoLoja.getText();

        if(codigoLoja.isBlank()) {
            LojaAlerts.campoVazioLojaAlert();
        }

        else {
            LojaDto objLojaDto = new LojaDto();
            objLojaDto.setCodigoLoja(codigoLoja);

            LojaBo objLojaBo = new LojaBo();
            retorno = objLojaBo.apagar(objLojaDto);

            if(retorno == 0){
                LojaAlerts.lojaNaoCadastradaAlert();
            }

            else if(retorno == 1){
                LojaAlerts.lojaApagadaAlert();
            }

            else{
                LojaAlerts.erroGenerico();
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
            LojaDto objLojaDto = new LojaDto();
            objLojaDto.setCodigoLoja(codigoLoja);

            LojaBo objLojaBo = new LojaBo();
            retorno = objLojaBo.consultar(objLojaDto);

            if(retorno == 1){
                txfNomeLoja.setText((objLojaDto.getNomeLoja()));
                txfCnpjLoja.setText(objLojaDto.getCnpjLoja());
                txfRuaLoja.setText(objLojaDto.getRuaLoja());
                txfNumeroLoja.setText(objLojaDto.getNumeroLoja());
                txfCepLoja.setText(objLojaDto.getCepLoja());
            }

            else if(retorno == 2){
                LojaAlerts.lojaNaoCadastradaAlert();
                txfNomeLoja.clear();
                txfCnpjLoja.clear();
                txfRuaLoja.clear();
                txfNumeroLoja.clear();
                txfCepLoja.clear();
            }

            else{
                LojaAlerts.erroGenerico();
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {

        App.changeScreen(Screens.TELA_INICIAL);
    }
}
