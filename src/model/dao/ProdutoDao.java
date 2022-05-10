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

    private String codInterno;
    private String codEan;
    private String nomeProduto;
    private String valorVenda;

    public int verificaProdutoExiste(ProdutoDto objProdutoDto) {
        codInterno = objProdutoDto.getCodInterno();
        codEan = objProdutoDto.getCodEan();
        nomeProduto = objProdutoDto.getNomeProduto();
        valorVenda = objProdutoDto.getValorVenda();

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
                }

                return 1;
            }

            else {
                return 0;
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void adicionaProduto(ProdutoDto objProdutoDto) {
        
        codInterno = objProdutoDto.getCodInterno();
        codEan = objProdutoDto.getCodEan();
        nomeProduto = objProdutoDto.getNomeProduto();
        valorVenda = objProdutoDto.getValorVenda();

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

        catch (SQLException e) {
            e.printStackTrace();
            AddProdutoAlerts.produtoErrorAlertGeneric();
        }
    }

    public void consulta(ProdutoDto objProdutoDto, String tipoConsulta, String codConsulta){
        String sqlConsulta = DB.loadSql(tipoConsulta);

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlConsulta);
            st.setString(1, codConsulta);
            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    objProdutoDto.setCodInterno(rs.getString("codigo_interno"));
                    objProdutoDto.setCodEan(rs.getString("codigo_ean"));
                    objProdutoDto.setNomeProduto(rs.getString("nome_produto"));
                    objProdutoDto.setValorVenda(rs.getString("valor_venda"));
                }                
            }
            
            else {
                AddProdutoAlerts.consultaProdutoErrorAlert();
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(ProdutoDto objProdutoDto){
        String sqlAltera = DB.loadSql("alteraProduto");

        codInterno = objProdutoDto.getCodInterno();
        codEan = objProdutoDto.getCodEan();
        nomeProduto = objProdutoDto.getNomeProduto();
        valorVenda = objProdutoDto.getValorVenda();

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(sqlAltera);
            st.setString(1, nomeProduto);
            st.setString(2, valorVenda);
            st.setString(3, codInterno);
            st.setString(4, codEan);
            st.executeUpdate();

            AddProdutoAlerts.alteraProdutoAlert();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
