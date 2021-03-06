package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import enums.FornecedorEnums;
import model.dto.FornecedorDto;

public class FornecedorDao {
    private String nomeFornecedor;
    private Long cnpj;

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public FornecedorEnums consultar(FornecedorDto objFornecedorDto, String tipoConsulta) {
        cnpj = objFornecedorDto.getCnpj();

        String sqlVerifica = DB.loadSql("verificaExisteFornecedor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlVerifica);
            st.setLong(1, cnpj);
            rs = st.executeQuery();

            if (rs.isBeforeFirst() && tipoConsulta == "VERIFICA_EXISTE") {
                DB.closeAll(st, rs);
                return FornecedorEnums.JA_CADASTRADO;
            }

            else if(rs.isBeforeFirst() && tipoConsulta == "CONSULTA"){
                while(rs.next()){
                    objFornecedorDto.setNomeFornecedor(rs.getString("nome"));                    
                }

                DB.closeAll(st, rs);
                return FornecedorEnums.SUCESSO_CONSULTA;
            }

            else {
                DB.closeAll(st, rs);
                return FornecedorEnums.NAO_CADASTRADO;
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return FornecedorEnums.ERRO_GENERICO;
        }
    }

    public FornecedorEnums adicionar(FornecedorDto objFornecedorDto) {
        nomeFornecedor = objFornecedorDto.getNomeFornecedor();
        cnpj = objFornecedorDto.getCnpj();

        String sqlInsertFornecedor = DB.loadSql("insertFornecedor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlInsertFornecedor);
            st.setString(1, nomeFornecedor);
            st.setLong(2, cnpj);
            st.executeUpdate();

            DB.closeAll(st, rs);
            return FornecedorEnums.SUCESSO_CADASTRAR;
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return FornecedorEnums.ERRO_GENERICO;
        }
    }

    public FornecedorEnums apagar(FornecedorDto objFornecedorDto) {

        cnpj = objFornecedorDto.getCnpj();
        String sqlInsertFornecedor = DB.loadSql("deleteFornecedor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlInsertFornecedor);
            st.setLong(1, cnpj);
            st.executeUpdate();

            DB.closeAll(st, rs);
            return FornecedorEnums.SUCESSO_APAGAR;
        }

        catch (SQLException e) {
            e.printStackTrace();
            return FornecedorEnums.ERRO_GENERICO;
        }
    }
}
