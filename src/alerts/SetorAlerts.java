package alerts;

import javafx.scene.control.Alert;

public class SetorAlerts {
    public static void addSetorConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Setor adicionado");
        alert.setHeaderText("Setor adicionado com sucesso.");
        alert.show();
    }

    public static void addSetorErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Preencha o nome do setor.");
        alert.show();
    }

    public static void setorExisteErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Setor já cadastrado.");
        alert.show();
    }

    public static void deleteSetorConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Setor apagado com sucesso.");
        alert.show();
    }

    public static void setorGenericErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Erro genérico.");
        alert.show();
    }

    public static void setorNaoExisteErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Setor não cadastrado.");
        alert.show();
    }
}
