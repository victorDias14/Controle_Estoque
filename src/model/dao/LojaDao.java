package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import enums.LojaEnums;
import model.dto.LojaDto;

public class LojaDao {

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public String consulta(LojaDto objLojaDto, String tipoConsulta) {
        String sqlConsulta = DB.loadSql("consultaLoja");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlConsulta);
            st.setString(1, objLojaDto.getCodigoLoja());

            rs = st.executeQuery();
            
            if(rs.isBeforeFirst()){
                if(tipoConsulta == "VERIFICA_EXISTE"){
                    DB.closeAll(st, rs);
                    return LojaEnums.JA_CADASTRADA.toString();                    
                }

                else{
                    while(rs.next()){
                        objLojaDto.setNomeLoja(rs.getString("nome"));
                        objLojaDto.setCnpjLoja(rs.getString("cnpj"));
                        objLojaDto.setRuaLoja(rs.getString("rua"));
                        objLojaDto.setNumeroLoja(rs.getString("numero"));
                        objLojaDto.setCepLoja(rs.getString("cep"));
                        DB.closeAll(st, rs);
                        return LojaEnums.CONSULTADA.toString();
                    }   
                }
            }

            else{
                DB.closeAll(st, rs);
                return LojaEnums.NAO_CADASTRADA.toString();
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return LojaEnums.ERRO_GENERICO.toString();
        }

        DB.closeAll(st, rs);
        return LojaEnums.ERRO_GENERICO.toString();
    }

    public String adiciona(LojaDto objLojaDto) {
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
            DB.closeAll(st, rs);
            return LojaEnums.SUCESSO_CADASTRO.toString();
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return LojaEnums.ERRO_GENERICO.toString();
        }
    }

    public String apagar(LojaDto objLojaDto) {
        String sqlDeleteLoja = DB.loadSql("deleteLoja");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlDeleteLoja);
            st.setString(1, objLojaDto.getCodigoLoja());
            st.executeUpdate();
            DB.closeAll(st, rs);
            return LojaEnums.LOJA_APAGADA.toString();
        }

        catch (Exception e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return LojaEnums.ERRO_GENERICO.toString();
        }
    }

}
