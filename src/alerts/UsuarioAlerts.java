package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UsuarioAlerts {
    public static void criarUsuarioConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuário adicionado");
        alert.setHeaderText("Usuário adicionado com sucesso.");
        alert.show();
    }

    public static void criarUsuarioErrorAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Preencha os dois campos.");
        alert.show();
    }

    public static void apagarUsuarioErrorAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Preencha o código do usuário.");
        alert.show();
    }

    public static void apagarUsuarioConfirmationAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Usuário apagado");
        alert.setHeaderText("Usuário apagado com sucesso.");
        alert.show();
    }

    public static void usuarioExisteAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Usuário existente");
        alert.setHeaderText("Código de usuário já cadastrado.");
        alert.show();
    }

    public static void usuarioGenericAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro genérico");
        alert.setHeaderText("Erro genérico.");
        alert.show();
    }

    public static void apagarUsuarioInexistenteErrorAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Código de usuário não cadastrado.");
        alert.show();
    }
}
