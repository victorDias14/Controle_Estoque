package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.FornecedorAlerts;
import db.DB;
import model.dto.FornecedorDto;

public class FornecedorDao {
    private String nomeFornecedor;
    private Long cnpj;

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    int retorno;

    int verificaExiste(FornecedorDto objFornecedorDto) {
        cnpj = objFornecedorDto.getCnpj();

        String sqlVerifica = DB.loadSql("verificaExisteFornecedor");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlVerifica);
            st.setLong(1, cnpj);
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

    public void adicionar(FornecedorDto objFornecedorDto) {
        retorno = verificaExiste(objFornecedorDto);

        if (retorno == 0){
            FornecedorAlerts.fornecedorExisteAlert();
        }

        else if (retorno == 1) {
            nomeFornecedor = objFornecedorDto.getNomeFornecedor();
            cnpj = objFornecedorDto.getCnpj();

            String sqlInsertFornecedor = DB.loadSql("insertFornecedor");

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlInsertFornecedor);
                st.setString(1, nomeFornecedor);
                st.setLong(2, cnpj);
                st.executeUpdate();

                FornecedorAlerts.fornecedorAlert();
            }

            catch (SQLException e) {
                e.printStackTrace();
                FornecedorAlerts.fornecedorErroGenericoAlert();
            }
        }
        
        else {
            FornecedorAlerts.fornecedorErroGenericoAlert();
        }
    }

    public void apagar(FornecedorDto objFornecedorDto) {
        retorno = verificaExiste(objFornecedorDto);

        if (retorno == 0){
            cnpj = objFornecedorDto.getCnpj();
            String sqlInsertFornecedor = DB.loadSql("deleteFornecedor");

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement(sqlInsertFornecedor);
                st.setLong(1, cnpj);
                st.executeUpdate();
    
                FornecedorAlerts.apagaFornecedorAlert();
            }
    
            catch (SQLException e) {
                e.printStackTrace();
                FornecedorAlerts.fornecedorErroGenericoAlert();
            }
        }

        else if(retorno == 1){
            FornecedorAlerts.fornecedorConsultaInformationAlert();
        }

        else {
            FornecedorAlerts.fornecedorErroGenericoAlert();
        }
    }

    public void consultar(FornecedorDto objFornecedorDto){
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
