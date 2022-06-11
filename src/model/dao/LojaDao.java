package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import model.dto.LojaDto;

public class LojaDao {

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public int verificaLojaExiste(LojaDto objLojaDto, int tipoConsulta) {
        String sqlConsultaExiste = DB.loadSql("verificaExisteLoja");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlConsultaExiste);
            st.setString(1, objLojaDto.getCodigoLoja());

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                if(tipoConsulta == 0) {
                    return 0;
                }
                
                else {
                    while(rs.next()){
                        objLojaDto.setNomeLoja(rs.getString("nome"));
                        objLojaDto.setCnpjLoja(rs.getString("cnpj"));
                        objLojaDto.setRuaLoja(rs.getString("rua"));
                        objLojaDto.setNumeroLoja(rs.getString("numero"));
                        objLojaDto.setCepLoja(rs.getString("cep"));
                    }                   

                    return 1;
                }
            }

            else {
                return 2;
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            return 3;
        }
    }

    public int adiciona(LojaDto objLojaDto) {
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

        catch (SQLException e) {
            e.printStackTrace();
            return 2;
        }
    }

    public int apagar(LojaDto objLojaDto) {
        String sqlDeleteLoja = DB.loadSql("deleteLoja");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlDeleteLoja);
            st.setString(1, objLojaDto.getCodigoLoja());
            st.executeUpdate();
            return 1;
        }

        catch (Exception e) {
            return 2;
        }
    }

}
