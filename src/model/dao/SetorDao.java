package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import enums.SetorEnums;
import model.dto.SetorDto;

public class SetorDao {
    private String nomeSetor;

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    
    public SetorEnums adicionarSetor(SetorDto objSetorDto){
        nomeSetor = objSetorDto.getNomeSetor();

        String sqlAddSetor = DB.loadSql("insertSetor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlAddSetor);
            st.setString(1, nomeSetor);
            st.executeUpdate();
            DB.closeAll(st, rs);
            return SetorEnums.SUCESSO_CADASTRO;
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return SetorEnums.ERRO_GENERICO;
        }
    }

    public SetorEnums apagarSetor(SetorDto objSetorDto){
        nomeSetor = objSetorDto.getNomeSetor();

        String sqlDelSetor = DB.loadSql("deleteSetor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlDelSetor);
            st.setString(1, nomeSetor);
            st.executeUpdate();
            DB.closeAll(st, rs);
            return SetorEnums.SUCESSO_APAGAR;
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return SetorEnums.ERRO_GENERICO;
        }
    }

    public SetorEnums consulta(SetorDto objSetorDto){
        nomeSetor = objSetorDto.getNomeSetor();

        String sqlVerificaExiste = DB.loadSql("verificaExisteSetor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlVerificaExiste);
            st.setString(1, nomeSetor);
            rs = st.executeQuery();

            if(rs.isBeforeFirst()){
                DB.closeAll(st, rs);
                return SetorEnums.JA_CADASTRADO;
            }

            else {
                DB.closeAll(st, rs);
                return SetorEnums.NAO_CADASTRADO;
            }
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return SetorEnums.ERRO_GENERICO;
        }
    }
}
