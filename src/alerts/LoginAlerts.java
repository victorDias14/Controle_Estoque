package alerts;

import javafx.scene.control.Alert;

public class LoginAlerts {
    public static void loginErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de login");
        alert.setHeaderText("Usuário ou senha inválidos ou inexistentes.");
        alert.show();        
    }

    public static void loginInformationAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erro de login");
        alert.setHeaderText("Preencha os dois campos.");
        alert.show();        
    }

    public static void loginErrorConsultAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erro de login");
        alert.setHeaderText("Preencha os dois campos.");
        alert.show();        
    }
}
