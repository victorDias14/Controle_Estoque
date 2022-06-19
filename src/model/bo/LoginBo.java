package model.bo;

import java.security.MessageDigest;

import enums.LoginEnums;
import model.dao.LoginDao;
import model.dto.LoginDto;

public class LoginBo {

    private String senha;

    public LoginEnums validaSenha(LoginDto objUsuarioDto) {
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
            return loginDao.validaUsuario(objUsuarioDto);
        }

        catch(Exception e) {
            e.printStackTrace();
            return LoginEnums.ERRO_GENERICO;
        }
    }
    
}
