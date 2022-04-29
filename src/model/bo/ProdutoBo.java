package model.bo;

import alerts.AddProdutoAlerts;
import model.dto.ProdutoDto;

public class ProdutoBo {
    
    public void verificaProdutoExiste(ProdutoDto objProdutoDto){
        String codEan = objProdutoDto.getCodEan();
        String codInterno = objProdutoDto.getCodInterno();
        String nomeProduto = objProdutoDto.getNomeProduto();
        
        String codInternoBanco = objProdutoDto.getCodInternoBanco();
        String codEanBanco = objProdutoDto.getCodEanBanco();
        String nomeProdutoBanco = objProdutoDto.getNomeProdutoBanco();

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
}
