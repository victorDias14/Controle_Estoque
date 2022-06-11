package model.bo;

import model.dao.LojaDao;
import model.dto.LojaDto;

public class LojaBo {

    private int verificaExisteLoja;

    public int adicionar(LojaDto objLojaDto) {
        LojaDao objLojaDao = new LojaDao();
        verificaExisteLoja = objLojaDao.verificaLojaExiste(objLojaDto, 0);

        if (verificaExisteLoja == 0){
            return verificaExisteLoja;
        }

        else if(verificaExisteLoja == 2){
            return objLojaDao.adiciona(objLojaDto);
        }

        else {
            return verificaExisteLoja;
        }
    }

    public int apagar(LojaDto objLojaDto){
        LojaDao objLojaDao = new LojaDao();
        verificaExisteLoja = objLojaDao.verificaLojaExiste(objLojaDto, 0);

        if (verificaExisteLoja == 0){
            return objLojaDao.apagar(objLojaDto);
        }

        else if(verificaExisteLoja == 1){
            return 0;
        }

        else {
            return verificaExisteLoja;
        }        
    }

    public int consultar(LojaDto objLojaDto){
        LojaDao objLojaDao = new LojaDao();
        return objLojaDao.verificaLojaExiste(objLojaDto, 1);
    }
    
}
