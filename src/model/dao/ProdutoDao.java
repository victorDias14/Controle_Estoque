package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import enums.ProdutoEnums;
import model.dto.ProdutoDto;

public class ProdutoDao {

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public ProdutoEnums adicionaProduto(ProdutoDto objProdutoDto) {

        String sqlInsertProduto = DB.loadSql("insertProduto");
        String sqlInsertEan = DB.loadSql("insertEan");

        try {
            conn = DB.getConnection();                        
            st = conn.prepareStatement(sqlInsertProduto);
            st.setString(1, objProdutoDto.getCodInterno());
            st.setString(2, objProdutoDto.getCodEan());
            st.setString(3, objProdutoDto.getNomeProduto()); 
            st.setString(4, objProdutoDto.getValorVenda());
            st.executeUpdate();

            st = conn.prepareStatement(sqlInsertEan);
            st.setString(1, objProdutoDto.getCodEan());
            st.setString(2, objProdutoDto.getCodInterno());
            st.executeUpdate();
            
            DB.closeAll(st, rs);
            return ProdutoEnums.PRODUTO_ADICIONADO;
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return ProdutoEnums.ERRO_GENERICO;
        }
    }

    public ProdutoEnums consultaAdicionar(ProdutoDto objProdutoDto){
        String sqlConsulta = DB.loadSql("consulta");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlConsulta);

            st.setString(1, objProdutoDto.getCodEan());
            st.setString(2, objProdutoDto.getCodInterno());
            rs = st.executeQuery();

            if(rs.isBeforeFirst()){
                DB.closeAll(st, rs);
                return ProdutoEnums.PRODUTO_EXISTE;
            }

            else{
                DB.closeAll(st, rs);
                return ProdutoEnums.NAO_EXISTE;
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return ProdutoEnums.ERRO_GENERICO;
        }
    }

    public ProdutoEnums consultaInterno(ProdutoDto objProdutoDto){
        String sqlConsulta = DB.loadSql("consultaInterno");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlConsulta);

            st.setString(1, objProdutoDto.getCodInterno());
            rs = st.executeQuery();

            if(rs.isBeforeFirst()){
                while(rs.next()){
                    objProdutoDto.setCodEan(rs.getString("codigo_ean"));
                    objProdutoDto.setNomeProduto(rs.getString("nome_produto"));
                    objProdutoDto.setValorVenda(rs.getString("valor_venda"));
                }
                
                DB.closeAll(st, rs);
                return ProdutoEnums.PRODUTO_EXISTE;
            }

            else{
                DB.closeAll(st, rs);
                return ProdutoEnums.NAO_EXISTE;
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return ProdutoEnums.ERRO_GENERICO;
        }
    }

    public ProdutoEnums consultaEan(ProdutoDto objProdutoDto){
        String sqlConsulta = DB.loadSql("consultaEan");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlConsulta);

            st.setString(1, objProdutoDto.getCodEan());
            rs = st.executeQuery();

            if(rs.isBeforeFirst()){
                while(rs.next()){
                    objProdutoDto.setCodInterno(rs.getString("codigo_interno"));
                    objProdutoDto.setNomeProduto(rs.getString("nome_produto"));
                    objProdutoDto.setValorVenda(rs.getString("valor_venda"));
                }
                
                DB.closeAll(st, rs);
                return ProdutoEnums.PRODUTO_EXISTE;
            }

            else{
                DB.closeAll(st, rs);
                return ProdutoEnums.NAO_EXISTE;
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return ProdutoEnums.ERRO_GENERICO;
        }
    }

    public ProdutoEnums alterar(ProdutoDto objProdutoDto){
        String sqlAltera = DB.loadSql("alteraProduto");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlAltera);
            st.setString(1, objProdutoDto.getNomeProduto());
            st.setString(2, objProdutoDto.getValorVenda());
            st.setString(3, objProdutoDto.getCodInterno());
            st.setString(4, objProdutoDto.getCodEan());
            st.executeUpdate();

            DB.closeAll(st, rs);
            return ProdutoEnums.PRODUTO_ALTERADO;
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return ProdutoEnums.ERRO_GENERICO;
        }
    }

    public ProdutoEnums apagar(ProdutoDto objProdutoDto){
        String sqlDeleteInterno = DB.loadSql("deletaInterno");
        String sqlDeleteEan = DB.loadSql("deletaEan");

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(sqlDeleteEan);
            st.setString(1, objProdutoDto.getCodEan());
            st.executeUpdate();
            
            st = conn.prepareStatement(sqlDeleteInterno);
            st.setString(1, objProdutoDto.getCodInterno());
            st.executeUpdate();

            DB.closeAll(st, rs);
            return ProdutoEnums.PRODUTO_APAGADO;
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return ProdutoEnums.ERRO_GENERICO;
        }
    }
}
