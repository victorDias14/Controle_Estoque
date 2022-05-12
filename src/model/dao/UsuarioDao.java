package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.UsuarioAlerts;
import db.DB;
import model.dto.UsuarioDto;

public class UsuarioDao {

    private String usuario;
    private String senha;

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    int retorno;
    
    public void adiciona(UsuarioDto objUsuarioDto){
        usuario = objUsuarioDto.getCodigoUsuario();

        retorno = verificaExiste(usuario);

        if(retorno == 0){
            UsuarioAlerts.usuarioExisteAlert();
        }

        else if(retorno == 1){
            senha = objUsuarioDto.getSenhaUsuario();

            String sqlInsertUsuario = DB.loadSql("insertUsuario");

            try { 
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlInsertUsuario);
                st.setString(1, usuario);
                st.setString(2, senha);
                st.executeUpdate();
                
                UsuarioAlerts.criarUsuarioConfirmationAlert();
                fechaConexao();
            }

            catch (SQLException e) {
                e.printStackTrace();
                UsuarioAlerts.usuarioGenericAlert();
                fechaConexao();
            }
        } 
        
        else {
            UsuarioAlerts.usuarioGenericAlert();
            fechaConexao();
        }
    }

    public void apaga(UsuarioDto objUsuarioDto){
        usuario = objUsuarioDto.getCodigoUsuario();

        retorno = verificaExiste(usuario);

        if(retorno == 0){
            String sqlDeleteUsuario = DB.loadSql("deleteUsuario");

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlDeleteUsuario);
                st.setString(1, usuario);
                st.executeUpdate();

                UsuarioAlerts.apagarUsuarioConfirmationAlert();
                fechaConexao();
            }

            catch (SQLException e) {
                e.printStackTrace();
                UsuarioAlerts.usuarioGenericAlert();
                fechaConexao();
            }
        }

        else if(retorno == 1){
            UsuarioAlerts.apagarUsuarioInexistenteErrorAlert();
        }

        else {
            UsuarioAlerts.usuarioGenericAlert();
        }
    }

    int verificaExiste(String usuario) {
        String sqlVerifica = DB.loadSql("verificaExisteUsuario");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlVerifica);
            st.setString(1, usuario);
            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
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

    void fechaConexao(){
        DB.closeStatement(st);
        DB.closeResultset(rs);
        DB.closeConnection();
    }
}
