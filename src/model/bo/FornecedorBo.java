package model.bo;

import alerts.FornecedorAlerts;
import model.dao.FornecedorDao;
import model.dto.FornecedorDto;

public class FornecedorBo {
    private String nomeFornecedor;
    private Long cnpj;
    FornecedorDao objFornecedorDao = new FornecedorDao();

    public void adicionar(FornecedorDto objFornecedorDto){
        nomeFornecedor = objFornecedorDto.getNomeFornecedor();

        if (nomeFornecedor.isEmpty()) {
            FornecedorAlerts.fornecedorAddErrorAlert();
        }

        else {
            objFornecedorDao.adicionar(objFornecedorDto);
        }
    }
    
    public void apagar(FornecedorDto objFornecedorDto){
        objFornecedorDao.apagar(objFornecedorDto);
    }  
}
