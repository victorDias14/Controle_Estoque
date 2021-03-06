package alerts;

import javafx.scene.control.Alert;

public class LojaAlerts {

    public static void lojaAdicionadaAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loja adicionada");
        alert.setHeaderText("Loja adicionada com sucesso.");
        alert.show();
    }

    public static void campoVazioLojaAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo vazio");
        alert.setHeaderText("Preencha todos os campos.");
        alert.show();
    }

    public static void codigoExisteLojaAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Informação já existe");
        alert.setHeaderText("Código de loja já existe.");
        alert.show();
    }

    public static void nomeExisteLojaAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Informação já existe");
        alert.setHeaderText("Nome de loja já existe.");
        alert.show();
    }

    public static void cnpjExisteLojaAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Informação já existe");
        alert.setHeaderText("CNPJ já existe.");
        alert.show();
    }
    
    public static void codigoNaoExisteAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Loja inexistente");
        alert.setHeaderText("Esse código de loja não está cadastrado.");
        alert.show();
    }

    public static void lojaApagadaAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loja apagada");
        alert.setHeaderText("Loja apagada com sucesso.");
        alert.show();
    }

    public static void lojaJaCadastradaAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loja já cadastrada");
        alert.setHeaderText("Loja já está cadastrada.");
        alert.show();
    }

    public static void lojaNaoCadastradaAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loja não cadastrada");
        alert.setHeaderText("Essa loja não está cadastrada.");
        alert.show();
    }

    public static void erroGenerico() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro genérico");
        alert.setHeaderText("Erro genérico");
        alert.show();
    }

    public static void cnpjInvalido() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("CNPJ inválido");
        alert.show();
    }

    public static void informarCodigo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erro");
        alert.setHeaderText("Informe o código da loja");
        alert.show();
    }
    
}
