package controllers;

import alerts.SetorAlerts;
import enums.Screens;
import enums.SetorEnums;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.bo.SetorBo;
import model.dto.SetorDto;

public class SetorController {

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnApagar;

    @FXML
    private TextField txfSetor;

    @FXML
    void confirmar(ActionEvent event) {
        if (txfSetor.getText().isBlank()) {
            SetorAlerts.addSetorErrorAlert();
        }

        else {
            SetorDto objSetorDto = new SetorDto();
            objSetorDto.setNomeSetor(txfSetor.getText());

            SetorBo objSetorBo = new SetorBo();
            SetorEnums retornoAddSetor = objSetorBo.adicionarSetor(objSetorDto);

            switch(retornoAddSetor){
                case JA_CADASTRADO:
                    SetorAlerts.setorExisteErrorAlert();
                    break;

                case SUCESSO_CADASTRO:
                    SetorAlerts.addSetorConfirmationAlert();
                    break;

                case ERRO_GENERICO:
                    SetorAlerts.setorGenericErrorAlert();
                    break;

                default:
                    break;
            }
        }
    }

    @FXML
    void apagar(ActionEvent event) {
        if (txfSetor.getText().isBlank()) {
            SetorAlerts.addSetorErrorAlert();
        }

        else {
            SetorDto objSetorDto = new SetorDto();
            objSetorDto.setNomeSetor(txfSetor.getText());

            SetorBo objSetorBo = new SetorBo();
            SetorEnums retornoDelSetor = objSetorBo.apagarSetor(objSetorDto);

            switch(retornoDelSetor){
                case SUCESSO_APAGAR:
                    SetorAlerts.deleteSetorConfirmationAlert();
                    break;

                case NAO_CADASTRADO:
                    SetorAlerts.setorNaoExisteErrorAlert();
                    break;

                case ERRO_GENERICO:
                    SetorAlerts.setorGenericErrorAlert();
                    break;

                default:
                    break;
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);
    }
    
}
