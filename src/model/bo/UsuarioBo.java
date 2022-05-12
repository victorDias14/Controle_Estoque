package model.bo;

import java.security.MessageDigest;

import model.dao.UsuarioDao;
import model.dto.UsuarioDto;

public class UsuarioBo {
    UsuarioDao objUsuarioDao = new UsuarioDao();
    
    public void adiciona(UsuarioDto objUsuarioDto){
        String retorno = criptografia(objUsuarioDto.getSenhaUsuario());
        objUsuarioDto.setSenhaUsuario(retorno);
        objUsuarioDao.adiciona(objUsuarioDto);
    }

    public void apaga(UsuarioDto objUsuarioDto){
        objUsuarioDao.apaga(objUsuarioDto);
    }

    String criptografia(String senha) {

        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();

            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }

            return hexString.toString();
        }

        catch(Exception e) {
            e.printStackTrace();
        }
        return senha;        
    }
}
