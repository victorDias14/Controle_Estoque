package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.LoginAlerts;
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

        String sqlValidaUsuario = "SELECT senha FROM usuarios WHERE login = ?";

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
                    App.changeScreen(Screens.TELA_INICIAL);

                    DB.closeResultset(rs);
                    DB.closeStatement(st);
                    DB.closeConnection();
                }

                else {
                    LoginAlerts.loginErrorAlert();
                }
            }

            else {
                LoginAlerts.loginErrorAlert();
            }                                
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    
}
