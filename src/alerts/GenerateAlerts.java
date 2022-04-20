package alerts;

import javafx.scene.control.Alert;

public class GenerateAlerts {
    
    private static String messageOne = "adicionada com sucesso";
    private static String messageTwo = "adicionado com sucesso";

    



    public static void lojaAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loja adicionada");
        alert.setHeaderText("Loja " + messageOne);
        alert.show();
    }

    

    public static void setorAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Setor adicionado");
        alert.setHeaderText("Setor " + messageTwo);
        alert.show();
    }

    
    
}
