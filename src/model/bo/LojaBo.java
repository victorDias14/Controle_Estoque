package model.bo;

import java.beans.VetoableChangeListener;

import alerts.LojaAlerts;
import model.dao.LojaDao;
import model.dto.LojaDto;

public class LojaBo {

    public int adicionar(LojaDto objLojaDto) {
        LojaDao objLojaDao = new LojaDao();
        int verificaExisteLoja = objLojaDao.verificaLojaExiste(objLojaDto);

        if (verificaExisteLoja == 0){
            return verificaExisteLoja;
        }

        else if(verificaExisteLoja == 1){
            return objLojaDao.adiciona(objLojaDto);
        }

        else {
            return verificaExisteLoja;
        }
    }
    
}
