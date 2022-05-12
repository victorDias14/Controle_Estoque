package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import alerts.FornecedorAlerts;
import db.DB;
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
    private PreparedStatement st;
    private ResultSet rs;

    @FXML
    void adicionar(ActionEvent event) {
        if (txfCnpj.getText() == "") {
            FornecedorAlerts.fornecedorConsultaErrorAlert();
        }

        else {
            FornecedorDto objFornecedorDto = new FornecedorDto();
            objFornecedorDto.setCnpj(Long.parseLong(txfCnpj.getText()));
            objFornecedorDto.setNomeFornecedor(txfNomeFornecedor.getText());

            FornecedorBo objFornecedorBo = new FornecedorBo();
            objFornecedorBo.adicionar(objFornecedorDto);
        }
    }

    @FXML
    void apagar(ActionEvent event) {

        if (cnpj == "") {
            FornecedorAlerts.fornecedorConsultaErrorAlert();
        }

        else {
            FornecedorDto objFornecedorDto = new FornecedorDto();
            objFornecedorDto.setCnpj(Long.parseLong(txfCnpj.getText()));

            FornecedorBo objFornecedorBo = new FornecedorBo();
            objFornecedorBo.apagar(objFornecedorDto);
        }
    }

    @FXML
    void consultar(ActionEvent event) {
        if (cnpj == "") {
            FornecedorAlerts.fornecedorConsultaErrorAlert();
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
        App.changeScreen(Screens.TELA_INICIAL);

        DB.closeStatement(st);
        DB.closeResultset(rs);
        DB.closeConnection();
    }

    void preencheCamposConsulta(FornecedorDto objFornecedorDto) {
        txfNomeFornecedor.setText(objFornecedorDto.getNomeFornecedor()); 
    }
}
