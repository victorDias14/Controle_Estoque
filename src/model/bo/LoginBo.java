package model.bo;

import java.security.MessageDigest;

import model.dto.LoginDto;

public class LoginBo {

    public int criptografaSenha(LoginDto objUsuarioDto) {
        String senha = objUsuarioDto.getSenha();

        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();

            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }

            objUsuarioDto.setSenha(hexString.toString());
        }

        catch(Exception e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }
    
}
