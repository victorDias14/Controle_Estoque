package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.AddProdutoAlerts;
import db.DB;
import model.dto.ProdutoDto;

public class ProdutoDao {

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public void adicionaProduto(ProdutoDto objProdutoDto) {
        String codInterno = objProdutoDto.getCodInterno();
        String codEan = objProdutoDto.getCodEan();
        String nomeProduto = objProdutoDto.getNomeProduto();
        String valorVenda = objProdutoDto.getValorVenda();

        String sqlConsultaExiste = "SELECT codigo_interno, codigo_ean, nome_produto FROM produto WHERE codigo_interno = ? OR codigo_ean = ? OR nome_produto = ?";

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlConsultaExiste);
            st.setString(1, codInterno);
            st.setString(2, codEan);
            st.setString(3, nomeProduto);
            rs = st.executeQuery();

            if(rs.isBeforeFirst()) {
                while(rs.next()) {
                    if(rs.getString("codigo_interno").equals(codInterno)) {
                        AddProdutoAlerts.codInternoExisteAlert();
                    }

                    else if(rs.getString("codigo_ean").equals(codEan)) {
                        AddProdutoAlerts.codEanExisteAlert();
                    }

                    else if(rs.getString("nome_produto").equals(nomeProduto)) {
                        AddProdutoAlerts.nomeProdutoExisteAlert();
                    }
                }
            }

            else {
                String sqlInsertProduto = "INSERT INTO produto (codigo_interno, codigo_ean, nome_produto, valor_venda) VALUES (?, ?, ?, ?)";
                String sqlInsertEan = "INSERT INTO ean (codigo_ean, codigo_interno) VALUES (?, ?)";

                try {
                        
                    st = conn.prepareStatement(sqlInsertProduto);
                    st.setString(1, codInterno);
                    st.setString(2, codEan);
                    st.setString(3, nomeProduto); 
                    st.setString(4, valorVenda);
                    st.executeUpdate();

                    st = conn.prepareStatement(sqlInsertEan);
                    st.setString(1, codEan);
                    st.setString(2, codInterno);
                    st.executeUpdate();

                    AddProdutoAlerts.produtoAlert();
                }

                catch(SQLException e) {
                    e.printStackTrace();
                }
            }            
        }

        catch (SQLException e) {
            e.printStackTrace();
            AddProdutoAlerts.produtoErrorAlertGeneric();
        }
    }
}
