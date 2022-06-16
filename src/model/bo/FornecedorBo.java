package model.bo;

import model.dao.FornecedorDao;
import model.dto.FornecedorDto;

public class FornecedorBo {

    FornecedorDao objFornecedorDao = new FornecedorDao();

    private String retornoVerificaExiste;

    public String adicionar(FornecedorDto objFornecedorDto){
        retornoVerificaExiste = objFornecedorDao.verificaExiste(objFornecedorDto);

        if(retornoVerificaExiste != "NAO_CADASTRADO"){
            return retornoVerificaExiste;
        }

        else{
            String retornoAdiciona = objFornecedorDao.adicionar(objFornecedorDto);
            return retornoAdiciona;
        }        
    }
    
    public String apagar(FornecedorDto objFornecedorDto){
        retornoVerificaExiste = objFornecedorDao.verificaExiste(objFornecedorDto);

        if(retornoVerificaExiste == "JA_CADASTRADO"){
            String retornoApagar = objFornecedorDao.apagar(objFornecedorDto);
            return retornoApagar;
        }

        else{
            return retornoVerificaExiste;
        }        
    }
    
    public void consultar(FornecedorDto objDto){
        objFornecedorDao.consultar(objDto);
    }
}
