package controllers;

import java.util.ArrayList;
import java.util.List;

import alerts.ProdutoAlerts;
import enums.ProdutoEnums;
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
    private Button btnConsultarInterno;

    @FXML
    private Button btnConsultarEan;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnApagar;

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

    private ProdutoEnums retornoConsulta;

    @FXML
    void adicionar(ActionEvent event) {
        int retornoCamposVazios = verificaCamposVazios();

        if (retornoCamposVazios == 1) {
            ProdutoDto objProdutoDto = new ProdutoDto();
            objProdutoDto.setCodEan(txfCodEan.getText());
            objProdutoDto.setCodInterno(txfCodInterno.getText());
            objProdutoDto.setNomeProduto(txfNomeProduto.getText());
            objProdutoDto.setValorVenda(txfValorVenda.getText());

            ProdutoBo objProdutoBo = new ProdutoBo();
            ProdutoEnums retornoAdicionar = objProdutoBo.adicionar(objProdutoDto);

            switch (retornoAdicionar) {
                case PRODUTO_ADICIONADO:
                    ProdutoAlerts.produtoAlert();
                    break;

                case PRODUTO_EXISTE:
                    ProdutoAlerts.produtoJaCadastradoAlert();
                    break;

                case ERRO_GENERICO:
                    ProdutoAlerts.produtoErrorAlertGeneric();
                    break;

                default:
                    break;
            }
        }

        else {
            ProdutoAlerts.produtoErrorAlertEmptyField();
        }
    }

    @FXML
    void consultarInterno(ActionEvent event) {

        if(txfCodInterno.getText().isBlank()){
            ProdutoAlerts.consultaProdutoCodigoErrorAlert();
        }

        else{
            ProdutoDto objProdutoDto = new ProdutoDto();
            objProdutoDto.setCodInterno(txfCodInterno.getText());

            ProdutoBo objProdutoBo = new ProdutoBo();
            retornoConsulta = objProdutoBo.consultaInterno(objProdutoDto);

            switch(retornoConsulta){
                case PRODUTO_EXISTE:
                    preencheCamposConsulta(objProdutoDto);
                    break;

                case NAO_EXISTE:
                    ProdutoAlerts.consultaProdutoErrorAlert();
                    limpaCampos();
                    break;

                case ERRO_GENERICO:
                    ProdutoAlerts.produtoErrorAlertGeneric();
                    break;

                default:
                    break;
            }
        }        
    }

    @FXML
    void consultarEan(ActionEvent event) {

        if(txfCodEan.getText().isBlank()){
            ProdutoAlerts.consultaProdutoCodigoErrorAlert();
        }

        else{
            ProdutoDto objProdutoDto = new ProdutoDto();
            objProdutoDto.setCodEan(txfCodEan.getText());

            ProdutoBo objProdutoBo = new ProdutoBo();
            retornoConsulta = objProdutoBo.consultaEan(objProdutoDto);

            switch (retornoConsulta) {
                case PRODUTO_EXISTE:
                    preencheCamposConsulta(objProdutoDto);
                    break;

                case NAO_EXISTE:
                    ProdutoAlerts.consultaProdutoErrorAlert();
                    limpaCampos();
                    break;

                case ERRO_GENERICO:
                    ProdutoAlerts.produtoErrorAlertGeneric();
                    break;

                default:
                    break;
            }
        }

        
    }

    @FXML
    void alterar(ActionEvent event) {
        int retornoCamposVazios = verificaCamposVazios();

        if (retornoCamposVazios == 1) {
            ProdutoDto objProdutoDto = new ProdutoDto();
            objProdutoDto.setCodInterno(txfCodInterno.getText());
            objProdutoDto.setCodEan(txfCodEan.getText());
            objProdutoDto.setNomeProduto(txfNomeProduto.getText());
            objProdutoDto.setValorVenda(txfValorVenda.getText());

            ProdutoBo objProdutoBo = new ProdutoBo();
            ProdutoEnums retornoAlterar = objProdutoBo.alterar(objProdutoDto);

            switch (retornoAlterar) {
                case NAO_EXISTE:
                    ProdutoAlerts.consultaProdutoErrorAlert();
                    break;

                case PRODUTO_ALTERADO:
                    ProdutoAlerts.alteraProdutoAlert();
                    break;

                case ERRO_GENERICO:
                    ProdutoAlerts.produtoErrorAlertGeneric();
                    break;

                default:
                    break;
            }
        }

        else {
            ProdutoAlerts.produtoErrorAlertEmptyField();
        }

    }

    @FXML
    void apagar(ActionEvent event) {

        if (txfCodEan.getText().isBlank() || txfCodInterno.getText().isBlank()) {
            ProdutoAlerts.produtoErrorAlertEmptyField();
        }

        else {
            ProdutoDto objProdutoDto = new ProdutoDto();
            objProdutoDto.setCodEan(txfCodEan.getText());
            objProdutoDto.setCodInterno(txfCodInterno.getText());

            ProdutoBo objProdutoBo = new ProdutoBo();
            ProdutoEnums retornoApagar = objProdutoBo.apagar(objProdutoDto);

            switch(retornoApagar){
                case NAO_EXISTE:
                    ProdutoAlerts.consultaProdutoErrorAlert();
                    break;

                case PRODUTO_APAGADO:
                    ProdutoAlerts.apagaProdutoAlert();
                    limpaCampos();
                    break;

                case ERRO_GENERICO:
                    ProdutoAlerts.produtoErrorAlertGeneric();
                    break;

                default:
                    break;
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {

        if (txfCodEan.editableProperty().getValue().equals(false)
                || txfCodInterno.editableProperty().getValue().equals(false)) {
            txfCodInterno.setEditable(true);
            txfCodEan.setEditable(true);

            limpaCampos();
        }

        else {
            App.changeScreen(Screens.TELA_INICIAL);
        }
    }

    void preencheCamposConsulta(ProdutoDto objProdutoDto) {
        txfCodInterno.setText(objProdutoDto.getCodInterno());
        txfCodEan.setText(objProdutoDto.getCodEan());
        txfNomeProduto.setText(objProdutoDto.getNomeProduto());
        txfValorVenda.setText(objProdutoDto.getValorVenda());
    }

    void limpaCampos() {
        txfCodInterno.clear();
        txfCodEan.clear();
        txfNomeProduto.clear();
        txfQuantidade.clear();
        txfValorVenda.clear();
    }

    int verificaCamposVazios() {
        List<String> infos = new ArrayList<String>();
        infos.add(txfCodEan.getText());
        infos.add(txfCodInterno.getText());
        infos.add(txfNomeProduto.getText());
        infos.add(txfValorVenda.getText());

        for (String i : infos) {
            if (i.isBlank()) {
                return 0;
            }
        }

        return 1;
    }
}
