package model.bo;

import enums.FornecedorEnums;
import model.dao.FornecedorDao;
import model.dto.FornecedorDto;

public class FornecedorBo {

    FornecedorDao objFornecedorDao = new FornecedorDao();

    private String retornoVerificaExiste;

    public String adicionar(FornecedorDto objFornecedorDto){
        retornoVerificaExiste = objFornecedorDao.consultar(objFornecedorDto, FornecedorEnums.VERIFICA_EXISTE.toString());

        if(retornoVerificaExiste != "NAO_CADASTRADO"){
            return retornoVerificaExiste;
        }

        else{
            String retornoAdiciona = objFornecedorDao.adicionar(objFornecedorDto);
            return retornoAdiciona;
        }        
    }
    
    public String apagar(FornecedorDto objFornecedorDto){
        retornoVerificaExiste = objFornecedorDao.consultar(objFornecedorDto, FornecedorEnums.VERIFICA_EXISTE.toString());

        if(retornoVerificaExiste == "JA_CADASTRADO"){
            String retornoApagar = objFornecedorDao.apagar(objFornecedorDto);
            return retornoApagar;
        }

        else{
            return retornoVerificaExiste;
        }        
    }
    
    public String consultar(FornecedorDto objFornecedorDto){
        return objFornecedorDao.consultar(objFornecedorDto, FornecedorEnums.CONSULTA.toString());
    }
}
