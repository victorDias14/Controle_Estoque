package controllers;

import alerts.FornecedorAlerts;
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

    private String cnpj;

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
            String retornoAdicionar = objFornecedorBo.adicionar(objFornecedorDto);

            if(retornoAdicionar == "JA_CADASTRADO"){
                FornecedorAlerts.fornecedorExisteAlert();
            }

            else if(retornoAdicionar == "ERRO_GENERICO"){
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
            String retornoApagar = objFornecedorBo.apagar(objFornecedorDto);

            if(retornoApagar == "SUCESSO_APAGAR"){
                FornecedorAlerts.apagaFornecedorAlert();
            }

            else if(retornoApagar == "NAO_CADASTRADO"){
                FornecedorAlerts.fornecedorConsultaInformationAlert();
            }

            else{
                FornecedorAlerts.fornecedorErroGenericoAlert();
            }
        }
    }

    @FXML
    void consultar(ActionEvent event) {
        if (cnpj == "") {
            FornecedorAlerts.fornecedorAddErrorAlert();
        }

        else {
            FornecedorDto objFornecedorDto = new FornecedorDto();
            objFornecedorDto.setCnpj(Long.parseLong(txfCnpj.getText()));

            FornecedorBo objFornecedorBo = new FornecedorBo();
            objFornecedorBo.consultar(objFornecedorDto);
            preencheCamposConsulta(objFornecedorDto);
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        limpaCampos();
        App.changeScreen(Screens.TELA_INICIAL);
    }

    void preencheCamposConsulta(FornecedorDto objFornecedorDto) {
        txfNomeFornecedor.setText(objFornecedorDto.getNomeFornecedor()); 
    }

    void limpaCampos(){
        txfCnpj.clear();
        txfNomeFornecedor.clear();
    }
}
