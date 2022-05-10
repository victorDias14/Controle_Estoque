package model.bo;

import alerts.FornecedorAlerts;
import model.dao.FornecedorDao;
import model.dto.FornecedorDto;

public class FornecedorBo {
    private String nomeFornecedor;
    private String cnpj;
    FornecedorDao objFornecedorDao = new FornecedorDao();

    public void adicionar(FornecedorDto objFornecedorDto){
        if (nomeFornecedor == "" || cnpj == "") {
            FornecedorAlerts.fornecedorAddErrorAlert();
        }

        else {
            objFornecedorDao.adicionar(objFornecedorDto);
        }
    }    
}
