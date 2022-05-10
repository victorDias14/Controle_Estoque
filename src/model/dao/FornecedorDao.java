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

    public void adicionar(FornecedorDto objFornecedorDto) {
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
        }
    }
}
