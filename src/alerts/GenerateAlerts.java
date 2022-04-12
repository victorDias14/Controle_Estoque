package alerts;

import javafx.scene.control.Alert;

public class GenerateAlerts {
    
    private static String messageOne = "adicionada com sucesso";
    private static String messageTwo = "adicionado com sucesso";

    public static void loginAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de login");
        alert.setHeaderText("Usuário ou senha inválidos ou inexistentes");
        alert.show();        
    }

    public static void fornecedorAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fornecedor adicionado");
        alert.setHeaderText("Fornecedor " + messageTwo);
        alert.show();
    }

    public static void lojaAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loja adicionada");
        alert.setHeaderText("Loja " + messageOne);
        alert.show();
    }

    public static void produtoAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produto adicionado");
        alert.setHeaderText("Produto " + messageTwo);
        alert.show();
    }

    public static void setorAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Setor adicionado");
        alert.setHeaderText("Setor " + messageTwo);
        alert.show();
    }

    public static void usuarioAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuário adicionado");
        alert.setHeaderText("Usuário " + messageTwo);
        alert.show();
    }
    
}
