package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.App;
import db.DB;
import enums.Screens;
import model.dto.LoginDto;

public class LoginDao {
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public void validaUsuario(LoginDto objUsuarioDto) {
        String usuario = objUsuarioDto.getUsuario();
        String senha = objUsuarioDto.getSenha();
        String senhaCriptoBanco = null;

        String sqlValidaUsuario = DB.loadSql("validaUsuario");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlValidaUsuario);
            st.setString(1, usuario);
            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    senhaCriptoBanco = rs.getString("senha");
                }

                if (senha.equals(senhaCriptoBanco)) {
                    
                    DB.closeResultset(rs);
                    DB.closeStatement(st);
                    DB.closeConnection();
                    App.changeScreen(Screens.TELA_INICIAL);
                }    
            }                           
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    
}
