package model.bo;

import enums.FornecedorEnums;
import model.dao.FornecedorDao;
import model.dto.FornecedorDto;

public class FornecedorBo {

    FornecedorDao objFornecedorDao = new FornecedorDao();

    private FornecedorEnums retornoVerificaExiste;

    public FornecedorEnums adicionar(FornecedorDto objFornecedorDto){
        retornoVerificaExiste = objFornecedorDao.consultar(objFornecedorDto, FornecedorEnums.VERIFICA_EXISTE.toString());

        if(retornoVerificaExiste != FornecedorEnums.NAO_CADASTRADO){
            return retornoVerificaExiste;
        }

        else{
            FornecedorEnums retornoAdiciona = objFornecedorDao.adicionar(objFornecedorDto);
            return retornoAdiciona;
        }        
    }
    
    public FornecedorEnums apagar(FornecedorDto objFornecedorDto){
        retornoVerificaExiste = objFornecedorDao.consultar(objFornecedorDto, FornecedorEnums.VERIFICA_EXISTE.toString());

        if(retornoVerificaExiste == FornecedorEnums.JA_CADASTRADO){
            FornecedorEnums retornoApagar = objFornecedorDao.apagar(objFornecedorDto);
            return retornoApagar;
        }

        else{
            return retornoVerificaExiste;
        }        
    }
    
    public FornecedorEnums consultar(FornecedorDto objFornecedorDto){
        return objFornecedorDao.consultar(objFornecedorDto, FornecedorEnums.CONSULTA.toString());
    }
}
