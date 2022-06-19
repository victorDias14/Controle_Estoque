package controllers;

import java.util.ArrayList;
import java.util.List;

import alerts.LojaAlerts;
import enums.LojaEnums;
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

    @FXML
    void adicionar(ActionEvent event) {

        List<String> infos = new ArrayList<String>();
        infos.add(txfCodigoLoja.getText());
        infos.add(txfNomeLoja.getText());
        infos.add(txfCnpjLoja.getText());
        infos.add(txfRuaLoja.getText());
        infos.add(txfNumeroLoja.getText());
        infos.add(txfCepLoja.getText());

        int v = 1;

        if(infos.get(2).length() != 14){
            LojaAlerts.cnpjInvalido();
        }

        else{
            for(String i : infos){
                if(i.isBlank()){
                    v = 0;
                    break;
                }
            }            

            if(v == 1){
                LojaDto objLojaDto = new LojaDto();
                objLojaDto.setCodigoLoja(infos.get(0));
                objLojaDto.setNomeLoja(infos.get(1));
                objLojaDto.setCnpjLoja(infos.get(2));
                objLojaDto.setRuaLoja(infos.get(3));
                objLojaDto.setNumeroLoja(infos.get(4));
                objLojaDto.setCepLoja(infos.get(5));

                LojaBo objLojaBo = new LojaBo(); 
                LojaEnums retornoAdd = objLojaBo.adicionar(objLojaDto);

                if(retornoAdd == LojaEnums.JA_CADASTRADA){
                    LojaAlerts.lojaJaCadastradaAlert();
                }
    
                else if(retornoAdd == LojaEnums.SUCESSO_CADASTRO){
                    LojaAlerts.lojaAdicionadaAlert();
                }
    
                else{
                    LojaAlerts.erroGenerico();
                }
            }

            else{
                LojaAlerts.campoVazioLojaAlert();
            }           
        }           
    }

    @FXML
    void apagar(ActionEvent event) {
        if (txfCodigoLoja.getText().isBlank()) {
            LojaAlerts.informarCodigo();
        }

        else {
            LojaDto objLojaDto = new LojaDto();
            objLojaDto.setCodigoLoja(txfCodigoLoja.getText());

            LojaBo objLojaBo = new LojaBo();
            LojaEnums retornoDel = objLojaBo.apagar(objLojaDto);

            if (retornoDel == LojaEnums.NAO_CADASTRADA) {
                LojaAlerts.lojaNaoCadastradaAlert();
            }

            else if (retornoDel == LojaEnums.LOJA_APAGADA) {
                LojaAlerts.lojaApagadaAlert();
            }

            else {
                LojaAlerts.erroGenerico();
            }
        }
    }

    @FXML
    void consultar(ActionEvent event) {
        if (txfCodigoLoja.getText().isBlank()) {
            LojaAlerts.informarCodigo();
        }

        else {
            LojaDto objLojaDto = new LojaDto();
            objLojaDto.setCodigoLoja(txfCodigoLoja.getText());

            LojaBo objLojaBo = new LojaBo();
            LojaEnums consulta = objLojaBo.consultar(objLojaDto);

            if (consulta == LojaEnums.CONSULTADA) {
                txfNomeLoja.setText((objLojaDto.getNomeLoja()));
                txfCnpjLoja.setText(objLojaDto.getCnpjLoja());
                txfRuaLoja.setText(objLojaDto.getRuaLoja());
                txfNumeroLoja.setText(objLojaDto.getNumeroLoja());
                txfCepLoja.setText(objLojaDto.getCepLoja());
            }

            else if (consulta == LojaEnums.NAO_CADASTRADA) {
                LojaAlerts.lojaNaoCadastradaAlert();
                txfNomeLoja.clear();
                txfCnpjLoja.clear();
                txfRuaLoja.clear();
                txfNumeroLoja.clear();
                txfCepLoja.clear();
            }

            else {
                LojaAlerts.erroGenerico();
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);
    }
}
