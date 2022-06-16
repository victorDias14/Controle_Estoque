package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FornecedorAlerts {
    public static void fornecedorAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fornecedor adicionado");
        alert.setHeaderText("Fornecedor adicionado com sucesso");
        alert.show();
    }

    public static void fornecedorAddErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo não preenchido");
        alert.setHeaderText("É preciso preencher os dois campos.");
        alert.show();
    }

    public static void fornecedorDeleteErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo não preenchido");
        alert.setHeaderText("Preencha o CNPJ.");
        alert.show();
    }

    public static void fornecedorConsultaInformationAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fornecedor não encontrado");
        alert.setHeaderText("CNPJ não encontrado.");
        alert.show();
    }

    public static void apagaFornecedorAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Fornecedor apagado com sucesso.");
        alert.show();
    }

    public static void fornecedorExisteAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("CNPJ já cadastrado.");
        alert.show();
    }

    public static void fornecedorErroGenericoAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Erro genérico.");
        alert.show();
    }

    public static void fornecedorCnpjErrorAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("CNPJ inválido.");
        alert.show();
    }
}
