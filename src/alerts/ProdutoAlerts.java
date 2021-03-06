package alerts;

import javafx.scene.control.Alert;

public class ProdutoAlerts {

    public static void produtoAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produto adicionado");
        alert.setHeaderText("Produto adicionado com sucesso");
        alert.show();
    }

    public static void produtoErrorAlertEmptyField() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Apenas o campo 'Valor de venda' pode ficar vazio.");
        alert.show();
    }

    public static void produtoErrorAlertGeneric() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Erro ao executar ação");
        alert.show();
    }

    public static void consultaProdutoCodigoErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Nenhum código informado.");
        alert.show();
    }

    public static void consultaProdutoErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Produto não cadastrado.");
        alert.show();
    }

    public static void apagaProdutoAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Produto apagado com sucesso.");
        alert.show();
    }

    public static void apagaProdutoErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Consulte um produto primeiro.");
        alert.show();
    }

    public static void alteraProdutoAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produto alterado");
        alert.setHeaderText("Produto alterado com sucesso.");
        alert.show();
    }

    public static void codInternoExisteAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produto já existe");
        alert.setHeaderText("Código interno já cadastrado.");
        alert.show();
    }

    public static void codEanExisteAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produto já existe");
        alert.setHeaderText("Código EAN já cadastrado.");
        alert.show();
    }

    public static void nomeProdutoExisteAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produto já existe");
        alert.setHeaderText("Nome de produto já cadastrado.");
        alert.show();
    }

    public static void produtoJaCadastradoAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produto já existe");
        alert.setHeaderText("Produto já cadastrado.");
        alert.show();
    }
    
}
