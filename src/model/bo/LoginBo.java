package model.bo;

import java.security.MessageDigest;

import alerts.LoginAlerts;
import model.dao.LoginDao;
import model.dto.LoginDto;

public class LoginBo {

    private String usuario;
    private String senha;

    public void criptografaSenha(LoginDto objUsuarioDto) {
        senha = objUsuarioDto.getSenha();

        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();

            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }

            objUsuarioDto.setSenha(hexString.toString());
            LoginDao loginDao = new LoginDao();
            loginDao.validaUsuario(objUsuarioDto);
        }

        catch(Exception e) {
            e.printStackTrace();

        }
    }

    public void verificaCamposVazios(LoginDto objLoginDto){
        usuario = objLoginDto.getUsuario();
        senha = objLoginDto.getSenha();

        if(usuario == "" || senha == "") {
            LoginAlerts.loginInformationAlert();
        }

        else {
            criptografaSenha(objLoginDto);
        }
    }
    
}
