package model.bo;

import model.dao.SetorDao;
import model.dto.SetorDto;

public class SetorBo {
    
    public int adicionarSetor(SetorDto objSetorDto){
        SetorDao objSetorDao = new SetorDao();
        int setorExiste = objSetorDao.verificaExisteSetor(objSetorDto);

        if(setorExiste == 0 || setorExiste == 2){
            return setorExiste;
        }
        
        else{
            int retornoAddSetor = objSetorDao.adicionarSetor(objSetorDto);
            return retornoAddSetor;
        }
    }

    public int apagarSetor(SetorDto objSetorDto){
        SetorDao objSetorDao = new SetorDao();
        int setorExiste = objSetorDao.verificaExisteSetor(objSetorDto);

        if(setorExiste == 1 || setorExiste == 2){
            return setorExiste;
        }
        
        else{
            int retornoDelSetor = objSetorDao.apagarSetor(objSetorDto);
            return retornoDelSetor;
        }
    }
}
