package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.FornecedorAlerts;
import db.DB;
import enums.FornecedorEnums;
import model.dto.FornecedorDto;

public class FornecedorDao {
    private String nomeFornecedor;
    private Long cnpj;

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    int retorno;

    public String verificaExiste(FornecedorDto objFornecedorDto) {
        cnpj = objFornecedorDto.getCnpj();

        String sqlVerifica = DB.loadSql("verificaExisteFornecedor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlVerifica);
            st.setLong(1, cnpj);
            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                DB.closeAll(st, rs);
                return FornecedorEnums.JA_CADASTRADO.toString();
            }

            else {
                DB.closeAll(st, rs);
                return FornecedorEnums.NAO_CADASTRADO.toString();
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return FornecedorEnums.ERRO_GENERICO.toString();
        }
    }

    public String adicionar(FornecedorDto objFornecedorDto) {
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
            return FornecedorEnums.SUCESSO_CADASTRAR.toString();
        }

        catch (SQLException e) {
            e.printStackTrace();
            DB.closeAll(st, rs);
            return FornecedorEnums.ERRO_GENERICO.toString();
        }
    }

    public String apagar(FornecedorDto objFornecedorDto) {

        cnpj = objFornecedorDto.getCnpj();
        String sqlInsertFornecedor = DB.loadSql("deleteFornecedor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlInsertFornecedor);
            st.setLong(1, cnpj);
            st.executeUpdate();

            DB.closeAll(st, rs);
            return FornecedorEnums.SUCESSO_APAGAR.toString();
        }

        catch (SQLException e) {
            e.printStackTrace();
            return FornecedorEnums.ERRO_GENERICO.toString();
        }
    }

    public void consultar(FornecedorDto objFornecedorDto) {
        cnpj = objFornecedorDto.getCnpj();

        String sqlVerifica = DB.loadSql("verificaExiste");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlVerifica);
            st.setLong(1, cnpj);
            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    objFornecedorDto.setNomeFornecedor(rs.getString("nome"));
                    objFornecedorDto.setCnpj(Long.parseLong(rs.getString("cnpj")));
                }
            }

            else {
                FornecedorAlerts.fornecedorConsultaInformationAlert();
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            FornecedorAlerts.fornecedorErroGenericoAlert();
        }
    }
}
