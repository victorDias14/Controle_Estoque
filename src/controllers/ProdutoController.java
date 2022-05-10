package controllers;

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

    private String codEan = null;
    private String codInterno = null;
    private String nomeProduto = null;
    private String valorVenda = null;

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
        objProdutoBo.adicionar(objProdutoDto);
    }

    @FXML
    void consultar(ActionEvent event) {

        ProdutoDto objProdutoDto = new ProdutoDto();
        String tipoConsulta = null;

        txfCodInterno.setEditable(false);
        txfCodEan.setEditable(false);

        if (event.getSource() == btnConsultarInterno) {
            codInterno = txfCodInterno.getText();
            objProdutoDto.setCodInterno(codInterno);
            tipoConsulta = "interno";
        }

        else if (event.getSource() == btnConsultarEan) {
            codEan = txfCodEan.getText();
            objProdutoDto.setCodEan(codEan);
            tipoConsulta = "ean";
        }

        ProdutoBo objProdutoBo = new ProdutoBo();
        objProdutoBo.consulta(objProdutoDto, tipoConsulta);

        preencheCamposConsulta(objProdutoDto);        
    }

    @FXML
    void alterar(ActionEvent event) {

        codInterno = txfCodInterno.getText();
        codEan = txfCodEan.getText();
        nomeProduto = txfNomeProduto.getText();
        valorVenda = txfValorVenda.getText();

        ProdutoDto objProdutoDto = new ProdutoDto();
        objProdutoDto.setCodInterno(codInterno);
        objProdutoDto.setCodEan(codEan);
        objProdutoDto.setNomeProduto(nomeProduto);
        objProdutoDto.setValorVenda(valorVenda);

        ProdutoBo objProdutoBo = new ProdutoBo();
        objProdutoBo.alterar(objProdutoDto);
        
    }

    @FXML
    void apagar(ActionEvent event) {
        codEan = txfCodEan.getText();
        codInterno = txfCodInterno.getText();

        ProdutoDto objProdutoDto = new ProdutoDto();
        objProdutoDto.setCodEan(codEan);
        objProdutoDto.setCodInterno(codInterno);

        ProdutoBo objProdutoBo = new ProdutoBo();
        objProdutoBo.apagar(objProdutoDto);

        limpaCampos();
    }            

    @FXML
    void voltar(ActionEvent event) {

        if(txfCodEan.editableProperty().getValue().equals(false) || txfCodInterno.editableProperty().getValue().equals(false)){
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

    void limpaCampos(){
        txfCodInterno.clear();
        txfCodEan.clear();
        txfNomeProduto.clear();
        txfQuantidade.clear();
        txfValorVenda.clear();
    }
}
