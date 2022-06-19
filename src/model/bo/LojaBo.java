package model.bo;

import enums.LojaEnums;
import model.dao.LojaDao;
import model.dto.LojaDto;

public class LojaBo {

    private LojaEnums verificaExisteLoja;

    public LojaEnums adicionar(LojaDto objLojaDto) {
        LojaDao objLojaDao = new LojaDao();
        verificaExisteLoja = objLojaDao.consulta(objLojaDto, LojaEnums.VERIFICA_EXISTE);

        if (verificaExisteLoja == LojaEnums.JA_CADASTRADA){
            return verificaExisteLoja;
        }

        else if(verificaExisteLoja == LojaEnums.NAO_CADASTRADA){
            return objLojaDao.adiciona(objLojaDto);
        }

        else {
            return verificaExisteLoja;
        }
    }

    public LojaEnums apagar(LojaDto objLojaDto){
        LojaDao objLojaDao = new LojaDao();
        LojaEnums apagar = objLojaDao.consulta(objLojaDto, LojaEnums.VERIFICA_EXISTE);

        if (apagar == LojaEnums.JA_CADASTRADA){
            return objLojaDao.apagar(objLojaDto);
        }

        else {
            return apagar;
        }        
    }

    public LojaEnums consultar(LojaDto objLojaDto){
        LojaDao objLojaDao = new LojaDao();
        return objLojaDao.consulta(objLojaDto, LojaEnums.CONSULTA);
    }
    
}
