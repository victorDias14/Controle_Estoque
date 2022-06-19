package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import enums.UsuarioEnums;
import model.dto.UsuarioDto;

public class UsuarioDao {

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public UsuarioEnums adiciona(UsuarioDto objUsuarioDto) {
        String sqlInsertUsuario = DB.loadSql("insertUsuario");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlInsertUsuario);
            st.setString(1, objUsuarioDto.getCodigoUsuario());
            st.setString(2, objUsuarioDto.getSenhaUsuario());
            st.executeUpdate();
            DB.closeAll(st, rs);
            return UsuarioEnums.SUCESSO_CADASTRO;
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return UsuarioEnums.ERRO_GENERICO;
        }
    }

    public UsuarioEnums apaga(UsuarioDto objUsuarioDto) {

        try {
            String sqlDeleteUsuario = DB.loadSql("deleteUsuario");
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlDeleteUsuario);
            st.setString(1, objUsuarioDto.getCodigoUsuario());
            st.executeUpdate();
            DB.closeAll(st, rs);
            return UsuarioEnums.SUCESSO_APAGAR;
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return UsuarioEnums.ERRO_GENERICO;
        }
    }

    public UsuarioEnums consulta(UsuarioDto objUsuarioDto) {
        String sqlVerifica = DB.loadSql("verificaExisteUsuario");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlVerifica);
            st.setString(1, objUsuarioDto.getCodigoUsuario());
            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                DB.closeAll(st, rs);
                return UsuarioEnums.JA_CADASTRADO;
            }

            else {
                DB.closeAll(st, rs);
                return UsuarioEnums.NAO_CADASTRADO;
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return UsuarioEnums.ERRO_GENERICO;
        }
    }
}
