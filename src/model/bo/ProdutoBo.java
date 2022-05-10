package model.bo;

import alerts.AddProdutoAlerts;
import model.dao.ProdutoDao;
import model.dto.ProdutoDto;

public class ProdutoBo {
    private String codEan;
    private String codInterno ;
    private String nomeProduto;
        
    private String codInternoBanco;
    private String codEanBanco;
    private String nomeProdutoBanco;

    private int retornoCamposVazios;
    private int retornoProdutoExiste;
    
    public int verificaProdutoExiste(ProdutoDto objProdutoDto){
        codEan = objProdutoDto.getCodEan();
        codInterno = objProdutoDto.getCodInterno();
        nomeProduto = objProdutoDto.getNomeProduto();
        
        codInternoBanco = objProdutoDto.getCodInternoBanco();
        codEanBanco = objProdutoDto.getCodEanBanco();
        nomeProdutoBanco = objProdutoDto.getNomeProdutoBanco();

        if(codInternoBanco.equals(codInterno)){
            return 0;
        }

        else if(codEanBanco.equals(codEan)) { 
            return 1;
        }

        else if(nomeProdutoBanco.equals(nomeProduto)) {
            return 2;
        }

        else {
            return 3;
        }
    }

    int verificaCamposVazios(ProdutoDto objProdutoDto){
        codEan = objProdutoDto.getCodEan();
        codInterno = objProdutoDto.getCodInterno();
        nomeProduto = objProdutoDto.getNomeProduto();

        if (codEan == "" || codInterno == "" || nomeProduto == "") {
            return 0;
        }

        else {
            return 1;
        }
    }

    public void adicionar(ProdutoDto objProdutoDto){
        retornoCamposVazios = verificaCamposVazios(objProdutoDto);

        if(retornoCamposVazios == 0){
            AddProdutoAlerts.produtoErrorAlertEmptyField();
        }

        else {
            ProdutoDao objProdutoDao = new ProdutoDao();
            objProdutoDao.verificaProdutoExiste(objProdutoDto);
            retornoProdutoExiste = verificaProdutoExiste(objProdutoDto);

            if (retornoCamposVazios == 0){
                AddProdutoAlerts.codInternoExisteAlert();
            }

            else if (retornoCamposVazios == 1){
                AddProdutoAlerts.codEanExisteAlert();
            }

            else if (retornoCamposVazios == 2) {
                AddProdutoAlerts.nomeProdutoExisteAlert();
            }

            else {
                objProdutoDao.adicionaProduto(objProdutoDto);
            }            
        }        
    }

    public void consulta(ProdutoDto objProdutoDto, String tipoConsulta) {
        
        if (tipoConsulta == "interno") {
            codInterno = objProdutoDto.getCodInterno();

            if (codInterno == "") {
                AddProdutoAlerts.consultaProdutoCodigoErrorAlert();
            }

            else {
                ProdutoDao objProdutoDao = new ProdutoDao();
                objProdutoDao.consulta(objProdutoDto, "consultaInterno", codInterno);
            }
        }

        else if (tipoConsulta == "ean"){
            codEan = objProdutoDto.getCodEan();

            if (codEan == "") {
                AddProdutoAlerts.consultaProdutoCodigoErrorAlert();
            }

            else {
                ProdutoDao objProdutoDao = new ProdutoDao();
                objProdutoDao.consulta(objProdutoDto, "consultaEan", codEan);
            }
        }
    }

    public void alterar(ProdutoDto objProdutoDto) {
        retornoCamposVazios = verificaCamposVazios(objProdutoDto);

        if (retornoCamposVazios == 0){
            AddProdutoAlerts.produtoErrorAlertEmptyField();
        }

        else {
            ProdutoDao objProdutoDao = new ProdutoDao();
            retornoProdutoExiste = objProdutoDao.verificaProdutoExiste(objProdutoDto);

            if(retornoProdutoExiste == 1){
                objProdutoDao.alterar(objProdutoDto);
            }

            else {
                AddProdutoAlerts.consultaProdutoErrorAlert();
            }
        }
        
    }
}
