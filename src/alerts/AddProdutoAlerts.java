package alerts;

import javafx.scene.control.Alert;

public class AddProdutoAlerts {

    private static String message = "adicionado com sucesso";

    public static void produtoAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produto adicionado");
        alert.setHeaderText("Produto " + message);
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
        alert.setHeaderText("Erro ao adicionar produto");
        alert.show();
    }

    public static void consultaProdutoInternoErrorAlert() {
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
    
}
