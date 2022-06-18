package model.bo;

import enums.LojaEnums;
import model.dao.LojaDao;
import model.dto.LojaDto;

public class LojaBo {

    private String verificaExisteLoja;

    public String adicionar(LojaDto objLojaDto) {
        LojaDao objLojaDao = new LojaDao();
        verificaExisteLoja = objLojaDao.consulta(objLojaDto, LojaEnums.VERIFICA_EXISTE.toString());

        if (verificaExisteLoja == LojaEnums.JA_CADASTRADA.toString()){
            return verificaExisteLoja;
        }

        else if(verificaExisteLoja == LojaEnums.NAO_CADASTRADA.toString()){
            return objLojaDao.adiciona(objLojaDto);
        }

        else {
            return verificaExisteLoja;
        }
    }

    public String apagar(LojaDto objLojaDto){
        LojaDao objLojaDao = new LojaDao();
        String apagar = objLojaDao.consulta(objLojaDto, LojaEnums.VERIFICA_EXISTE.toString());

        if (apagar == LojaEnums.JA_CADASTRADA.toString()){
            return objLojaDao.apagar(objLojaDto);
        }

        else {
            return apagar;
        }        
    }

    public String consultar(LojaDto objLojaDto){
        LojaDao objLojaDao = new LojaDao();
        return objLojaDao.consulta(objLojaDto, LojaEnums.CONSULTA.toString());
    }
    
}
