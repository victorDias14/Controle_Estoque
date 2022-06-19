package model.bo;

import enums.SetorEnums;
import model.dao.SetorDao;
import model.dto.SetorDto;

public class SetorBo {
    
    public SetorEnums adicionarSetor(SetorDto objSetorDto){
        SetorDao objSetorDao = new SetorDao();
        SetorEnums setorExiste = objSetorDao.consulta(objSetorDto);

        if(setorExiste == SetorEnums.JA_CADASTRADO || setorExiste == SetorEnums.ERRO_GENERICO){
            return setorExiste;
        }
        
        else{
            SetorEnums retornoAddSetor = objSetorDao.adicionarSetor(objSetorDto);
            return retornoAddSetor;
        }
    }

    public SetorEnums apagarSetor(SetorDto objSetorDto){
        SetorDao objSetorDao = new SetorDao();
        SetorEnums setorExiste = objSetorDao.consulta(objSetorDto);

        if(setorExiste == SetorEnums.NAO_CADASTRADO || setorExiste == SetorEnums.ERRO_GENERICO){
            return setorExiste;
        }
        
        else{
            SetorEnums retornoDelSetor = objSetorDao.apagarSetor(objSetorDto);
            return retornoDelSetor;
        }
    }
}
