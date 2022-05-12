package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import model.dto.SetorDto;

public class SetorDao {
    private String nomeSetor;

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    
    public int adicionarSetor(SetorDto objSetorDto){
        nomeSetor = objSetorDto.getNomeSetor();

        String sqlAddSetor = DB.loadSql("insertSetor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlAddSetor);
            st.setString(1, nomeSetor);
            st.executeUpdate();
            return 1;
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
            return 2;
        }
    }

    public int apagarSetor(SetorDto objSetorDto){
        nomeSetor = objSetorDto.getNomeSetor();

        String sqlDelSetor = DB.loadSql("deleteSetor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlDelSetor);
            st.setString(1, nomeSetor);
            st.executeUpdate();
            return 0;
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
            return 2;
        }
    }

    public int verificaExisteSetor(SetorDto objSetorDto){
        nomeSetor = objSetorDto.getNomeSetor();

        String sqlVerificaExiste = DB.loadSql("verificaExisteSetor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlVerificaExiste);
            st.setString(1, nomeSetor);
            rs = st.executeQuery();

            if(rs.isBeforeFirst()){
                return 0;
            }

            else {
                return 1;
            }
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
            return 2;
        }
    }
}
