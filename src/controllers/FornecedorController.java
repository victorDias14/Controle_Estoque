package controllers;

import alerts.FornecedorAlerts;
import enums.FornecedorEnums;
import enums.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.bo.FornecedorBo;
import model.dto.FornecedorDto;

public class FornecedorController {
    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txfCnpj;

    @FXML
    private TextField txfNomeFornecedor;

    @FXML
    void adicionar(ActionEvent event) {
        if (txfNomeFornecedor.getText().isBlank() || txfCnpj.getText().isBlank()) {
            FornecedorAlerts.fornecedorAddErrorAlert();
        }

        else if(txfCnpj.getText().length() != 14){
            FornecedorAlerts.fornecedorCnpjErrorAlert();
        }

        else {
            FornecedorDto objFornecedorDto = new FornecedorDto();
            objFornecedorDto.setCnpj(Long.parseLong(txfCnpj.getText()));
            objFornecedorDto.setNomeFornecedor(txfNomeFornecedor.getText());

            FornecedorBo objFornecedorBo = new FornecedorBo();
            FornecedorEnums retornoAdicionar = objFornecedorBo.adicionar(objFornecedorDto);

            if(retornoAdicionar == FornecedorEnums.JA_CADASTRADO){
                FornecedorAlerts.fornecedorExisteAlert();
            }

            else if(retornoAdicionar == FornecedorEnums.ERRO_GENERICO){
                FornecedorAlerts.fornecedorErroGenericoAlert();
            }

            else{
                FornecedorAlerts.fornecedorAlert();
            }
        }
    }

    @FXML
    void apagar(ActionEvent event) {

        if (txfCnpj.getText().isBlank()) {
            FornecedorAlerts.fornecedorDeleteErrorAlert();
        }

        else if(txfCnpj.getText().length() != 14){
            FornecedorAlerts.fornecedorCnpjErrorAlert();
        }

        else {
            FornecedorDto objFornecedorDto = new FornecedorDto();
            objFornecedorDto.setCnpj(Long.parseLong(txfCnpj.getText()));

            FornecedorBo objFornecedorBo = new FornecedorBo();
            FornecedorEnums retornoApagar = objFornecedorBo.apagar(objFornecedorDto);

            if(retornoApagar == FornecedorEnums.SUCESSO_APAGAR){
                FornecedorAlerts.apagaFornecedorAlert();
            }

            else if(retornoApagar == FornecedorEnums.NAO_CADASTRADO){
                FornecedorAlerts.fornecedorConsultaInformationAlert();
            }

            else{
                FornecedorAlerts.fornecedorErroGenericoAlert();
            }
        }
    }

    @FXML
    void consultar(ActionEvent event) {
        if (txfCnpj.getText().isBlank()) {
            FornecedorAlerts.fornecedorDeleteErrorAlert();
        }

        else if(txfCnpj.getText().length() != 14){
            FornecedorAlerts.fornecedorCnpjErrorAlert();
        }

        else {
            FornecedorDto objFornecedorDto = new FornecedorDto();
            objFornecedorDto.setCnpj(Long.parseLong(txfCnpj.getText()));

            FornecedorBo objFornecedorBo = new FornecedorBo();
            FornecedorEnums retornoConsulta = objFornecedorBo.consultar(objFornecedorDto);

            if(retornoConsulta == FornecedorEnums.SUCESSO_CONSULTA){
                txfNomeFornecedor.setText(objFornecedorDto.getNomeFornecedor());
            }

            else if(retornoConsulta == FornecedorEnums.NAO_CADASTRADO){
                FornecedorAlerts.fornecedorConsultaInformationAlert();
            }

            else{
                FornecedorAlerts.fornecedorErroGenericoAlert();
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        txfCnpj.clear();
        txfNomeFornecedor.clear();
        App.changeScreen(Screens.TELA_INICIAL);
    }
}
