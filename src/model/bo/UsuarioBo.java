package model.bo;

import java.security.MessageDigest;

import enums.UsuarioEnums;
import model.dao.UsuarioDao;
import model.dto.UsuarioDto;

public class UsuarioBo {

    private UsuarioEnums consulta;
    
    public UsuarioEnums adiciona(UsuarioDto objUsuarioDto){
        UsuarioDao objUsuarioDao = new UsuarioDao();
        consulta = objUsuarioDao.consulta(objUsuarioDto);

        if(consulta == UsuarioEnums.NAO_CADASTRADO){
            String retorno = criptografia(objUsuarioDto.getSenhaUsuario());
            objUsuarioDto.setSenhaUsuario(retorno);
            return objUsuarioDao.adiciona(objUsuarioDto);
        }

        else{
            return consulta;
        }       
    }

    public UsuarioEnums apaga(UsuarioDto objUsuarioDto){
        UsuarioDao objUsuarioDao = new UsuarioDao();
        consulta = objUsuarioDao.consulta(objUsuarioDto);

        if(consulta == UsuarioEnums.JA_CADASTRADO){
            return objUsuarioDao.apaga(objUsuarioDto);
        }

        else{
            return consulta;
        }
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
