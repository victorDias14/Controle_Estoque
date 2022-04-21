package alerts;

import javafx.scene.control.Alert;

public class AddSetorAlerts {
    public static void addSetorConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Setor apagado com sucesso.");
        alert.show();
    }
}
