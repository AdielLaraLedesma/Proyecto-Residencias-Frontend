package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import utils.SceneUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController extends PadreController implements Initializable {

    private SceneUtils sceneUtils = new SceneUtils();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void eventTomarAsistencia(ActionEvent actionEvent) {
        sceneUtils.loadStage("/views/pickCSVView.fxml", actionEvent, null);
    }


    public void eventConsultarAsistencia(ActionEvent actionEvent) {
        sceneUtils.loadStage("/views/pickMeetingView.fxml", actionEvent, null);
    }

    @Override
    public void Inicializador() {

    }
}
