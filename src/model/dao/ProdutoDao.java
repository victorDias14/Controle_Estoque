package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alerts.AddProdutoAlerts;
import db.DB;
import model.bo.ProdutoBo;
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

        String sqlConsultaExiste = DB.loadSql("selectProduto");

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlConsultaExiste);
            st.setString(1, codInterno);
            st.setString(2, codEan);
            st.setString(3, nomeProduto);
            rs = st.executeQuery();

            if(rs.isBeforeFirst()) {
                while(rs.next()) {
                    objProdutoDto.setCodInternoBanco(rs.getString("codigo_interno"));
                    objProdutoDto.setCodEanBanco(rs.getString("codigo_ean"));
                    objProdutoDto.setNomeProdutoBanco(rs.getString("nome_produto"));

                    ProdutoBo objProdutoBo = new ProdutoBo();
                    objProdutoBo.verificaProdutoExiste(objProdutoDto);
                }
            }

            else {
                String sqlInsertProduto = DB.loadSql("insertProduto");
                String sqlInsertEan = DB.loadSql("insertEan");

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