package model.bo;

import alerts.AddProdutoAlerts;
import controllers.ProdutoController;
import model.dao.ProdutoDao;
import model.dto.ProdutoDto;

public class ProdutoBo {
    private String codEan;
    private String codInterno ;
    private String nomeProduto;
        
    private String codInternoBanco;
    private String codEanBanco;
    private String nomeProdutoBanco;
    
    public void verificaProdutoExiste(ProdutoDto objProdutoDto){
        codEan = objProdutoDto.getCodEan();
        codInterno = objProdutoDto.getCodInterno();
        nomeProduto = objProdutoDto.getNomeProduto();
        
        codInternoBanco = objProdutoDto.getCodInternoBanco();
        codEanBanco = objProdutoDto.getCodEanBanco();
        nomeProdutoBanco = objProdutoDto.getNomeProdutoBanco();

        if(codInternoBanco.equals(codInterno)){
            AddProdutoAlerts.codInternoExisteAlert();
        }

        else if(codEanBanco.equals(codEan)) {
            AddProdutoAlerts.codEanExisteAlert();
        }

        else if(nomeProdutoBanco.equals(nomeProduto)) {
            AddProdutoAlerts.nomeProdutoExisteAlert();
        }
    }

    public void verificaCamposVazios(ProdutoDto objProdutoDto){
        codEan = objProdutoDto.getCodEan();
        codInterno = objProdutoDto.getCodInterno();
        nomeProduto = objProdutoDto.getNomeProduto();

        if (codEan == "" || codInterno == "" || nomeProduto == "") {
            AddProdutoAlerts.produtoErrorAlertEmptyField();
        }

        else {
            ProdutoDao objProdutoDao = new ProdutoDao();
            objProdutoDao.adicionaProduto(objProdutoDto);
        }
    }

    public void consulta(ProdutoDto objProdutoDto, String tipoConsulta) {
        
        if (tipoConsulta == "interno") {
            codInterno = objProdutoDto.getCodInterno();

            if (codInterno.isEmpty()) {
                AddProdutoAlerts.consultaProdutoCodigoErrorAlert();
            }

            else {
                ProdutoDao objProdutoDao = new ProdutoDao();
                objProdutoDao.consulta(objProdutoDto, "consultaInterno", codInterno);
            }
        }

        else if (tipoConsulta == "ean"){
            codEan = objProdutoDto.getCodEan();

            if (codEan.isEmpty()) {
                AddProdutoAlerts.consultaProdutoCodigoErrorAlert();
            }

            else {
                ProdutoDao objProdutoDao = new ProdutoDao();
                objProdutoDao.consulta(objProdutoDto, "consultaEan", codEan);
            }
        }
    }

    public static void limpaCampos() {
        ProdutoController prodControl = new ProdutoController();
        prodControl.limpaCampos();
    }
}
