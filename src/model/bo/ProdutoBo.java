package model.bo;

import enums.ProdutoEnums;
import model.dao.ProdutoDao;
import model.dto.ProdutoDto;

public class ProdutoBo {

    private ProdutoEnums retornoExiste;

    public ProdutoEnums adicionar(ProdutoDto objProdutoDto) {
        ProdutoDao objProdutoDao = new ProdutoDao();
        ProdutoEnums retornoExiste = objProdutoDao.consultaAdicionar(objProdutoDto);

        if (retornoExiste == ProdutoEnums.NAO_EXISTE) {
            return objProdutoDao.adicionaProduto(objProdutoDto);
        }

        else {
            return retornoExiste;
        }
    }

    public ProdutoEnums consultaInterno(ProdutoDto objProdutoDto) {
        ProdutoDao objProdutoDao = new ProdutoDao();
        return objProdutoDao.consultaInterno(objProdutoDto);
    }

    public ProdutoEnums consultaEan(ProdutoDto objProdutoDto) {
        ProdutoDao objProdutoDao = new ProdutoDao();
        return objProdutoDao.consultaEan(objProdutoDto);
    }

    public ProdutoEnums alterar(ProdutoDto objProdutoDto) {
        ProdutoDao objProdutoDao = new ProdutoDao();
        retornoExiste = objProdutoDao.adicionaProduto(objProdutoDto);

        if (retornoExiste == ProdutoEnums.PRODUTO_EXISTE) {
            return objProdutoDao.alterar(objProdutoDto);
        }

        else {
            return retornoExiste;
        }

    }

    public ProdutoEnums apagar(ProdutoDto objProdutoDto) {

        ProdutoDao objProdutoDao = new ProdutoDao();
        retornoExiste = objProdutoDao.consultaAdicionar(objProdutoDto);

        if (retornoExiste == ProdutoEnums.PRODUTO_EXISTE) {
            return objProdutoDao.apagar(objProdutoDto);
        }

        else {
            return retornoExiste;
        }
    }
}
