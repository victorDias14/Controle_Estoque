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

    public LojaEnums consulta(LojaDto objLojaDto, LojaEnums tipoConsulta) {
        String sqlConsulta = DB.loadSql("consultaLoja");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlConsulta);
            st.setString(1, objLojaDto.getCodigoLoja());

            rs = st.executeQuery();
            
            if(rs.isBeforeFirst()){
                if(tipoConsulta == LojaEnums.VERIFICA_EXISTE){
                    DB.closeAll(st, rs);
                    return LojaEnums.JA_CADASTRADA;                    
                }

                else{
                    while(rs.next()){
                        objLojaDto.setNomeLoja(rs.getString("nome"));
                        objLojaDto.setCnpjLoja(rs.getString("cnpj"));
                        objLojaDto.setRuaLoja(rs.getString("rua"));
                        objLojaDto.setNumeroLoja(rs.getString("numero"));
                        objLojaDto.setCepLoja(rs.getString("cep"));
                        DB.closeAll(st, rs);
                        return LojaEnums.CONSULTADA;
                    }   
                }
            }

            else{
                DB.closeAll(st, rs);
                return LojaEnums.NAO_CADASTRADA;
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return LojaEnums.ERRO_GENERICO;
        }

        DB.closeAll(st, rs);
        return LojaEnums.ERRO_GENERICO;
    }

    public LojaEnums adiciona(LojaDto objLojaDto) {
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
            return LojaEnums.SUCESSO_CADASTRO;
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return LojaEnums.ERRO_GENERICO;
        }
    }

    public LojaEnums apagar(LojaDto objLojaDto) {
        String sqlDeleteLoja = DB.loadSql("deleteLoja");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlDeleteLoja);
            st.setString(1, objLojaDto.getCodigoLoja());
            st.executeUpdate();
            DB.closeAll(st, rs);
            return LojaEnums.LOJA_APAGADA;
        }

        catch (Exception e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return LojaEnums.ERRO_GENERICO;
        }
    }

}
