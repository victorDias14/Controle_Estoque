package controllers;

import alerts.SetorAlerts;
import enums.Screens;
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

    private String nomeSetor;

    @FXML
    void confirmar(ActionEvent event) {
        nomeSetor = txfSetor.getText();

        if (nomeSetor == "") {
            SetorAlerts.addSetorErrorAlert();
        }

        else {
            SetorDto objSetorDto = new SetorDto();
            objSetorDto.setNomeSetor(nomeSetor);

            SetorBo objSetorBo = new SetorBo();
            int retornoAddSetor = objSetorBo.adicionarSetor(objSetorDto);

            switch(retornoAddSetor){
                case 0:
                    SetorAlerts.setorExisteErrorAlert();
                    break;

                case 1:
                    SetorAlerts.addSetorConfirmationAlert();
                    break;

                case 2:
                    SetorAlerts.setorGenericErrorAlert();
                    break;
            }
        }
    }

    @FXML
    void apagar(ActionEvent event) {
        nomeSetor = txfSetor.getText();

        if (nomeSetor == "") {
            SetorAlerts.addSetorErrorAlert();
        }

        else {
            SetorDto objSetorDto = new SetorDto();
            objSetorDto.setNomeSetor(nomeSetor);

            SetorBo objSetorBo = new SetorBo();
            int retornoDelSetor = objSetorBo.apagarSetor(objSetorDto);

            switch(retornoDelSetor){
                case 0:
                    SetorAlerts.deleteSetorConfirmationAlert();
                    break;

                case 1:
                    SetorAlerts.setorNaoExisteErrorAlert();
                    break;

                case 2:
                    SetorAlerts.setorGenericErrorAlert();
                    break;
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.changeScreen(Screens.TELA_INICIAL);
    }
    
}
