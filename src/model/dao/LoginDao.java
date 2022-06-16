package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import enums.LoginEnums;
import model.dto.LoginDto;

public class LoginDao {
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public String validaUsuario(LoginDto objUsuarioDto) {
        String usuario = objUsuarioDto.getUsuario();
        String senha = objUsuarioDto.getSenha();
        String senhaCriptoBanco = null;

        String sqlValidaUsuario = DB.loadSql("validaUsuario");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlValidaUsuario);
            st.setString(1, usuario);
            rs = st.executeQuery();

            if(rs.isBeforeFirst()) {
                while (rs.next()) {
                    senhaCriptoBanco = rs.getString("senha");
                }

                if(senha.equals(senhaCriptoBanco)) {
                    DB.closeAll(st, rs);

                    return LoginEnums.VALIDADO.toString();                 
                }

                else {
                    DB.closeAll(st, rs);
                    return LoginEnums.NAO_VALIDADO.toString();
                }
            }                           
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return LoginEnums.ERRO_GENERICO.toString();
        }

        return LoginEnums.ERRO_GENERICO.toString();
    }     
}
