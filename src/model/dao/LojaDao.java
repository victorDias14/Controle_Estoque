package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import model.dto.LojaDto;

public class LojaDao {

    private String codigoLoja;
    private String nomeLoja;
    private String cnpjLoja;
    private String ruaLoja;
    private String numeroLoja;
    private String cepLoja;

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public int verificaLojaExiste(LojaDto objLojaDto){
        String sqlConsultaExiste = DB.loadSql("verificaExisteLoja");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlConsultaExiste);
            st.setString(1, objLojaDto.getCodigoLoja());

            rs = st.executeQuery();

            if(rs.isBeforeFirst()){
                return 0;
            }

            else {
                return 1;
            }
        }

        catch(SQLException e){
            e.printStackTrace();
            return 2;
        }
    }

    public int adiciona(LojaDto objLojaDto){
        String sqlAdicionaLoja = DB.loadSql("insertLoja");
        
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlAdicionaLoja);
            st.setString(1, objLojaDto.getCodigoLoja());
            st.setString(2, objLojaDto.getNomeLoja());
            st.setString(3, objLojaDto.getCnpjLoja());
            st.setString(4, objLojaDto.getRuaLoja());
            st.setString(5, objLojaDto.getNumeroLoja());
            st.setString(6, objLojaDto.getCepLoja());
            st.executeUpdate();
            return 1;
        }

        catch (SQLException e){
            e.printStackTrace();
            return 2;
        }
    }

}
