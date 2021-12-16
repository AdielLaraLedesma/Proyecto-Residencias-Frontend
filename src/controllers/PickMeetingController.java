package controllers;

import dto.Participante;
import dto.Reunion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import utils.HttpRequestUtils;
import utils.ReunionConverter;
import utils.SceneUtils;

import java.util.ArrayList;
import java.util.List;

public class PickMeetingController extends PadreController {

    private HttpRequestUtils requestUtils = new HttpRequestUtils();

    //Componente para hacer el ruteo de ventanas
    SceneUtils sceneUtils = new SceneUtils();

    private Reunion reunion = new Reunion();

    //Componentes de la vista
    @FXML
    private ComboBox<Reunion> cboxSeleccionReunion;



    public void eventSeleccionarReunion(ActionEvent actionEvent) {
        reunion = cboxSeleccionReunion.getSelectionModel().getSelectedItem();
    }

    public void eventCancelar(ActionEvent actionEvent) {
        sceneUtils.loadStage("/views/principalView.fxml", actionEvent);
    }

    public void eventAceptar(ActionEvent actionEvent) {

        //Implementar metdo POST para traer todos los campos de esa reunion

        //Cargar la vista para ver la asistencia de una rerunion
        sceneUtils.loadStage("/views/seeAttendanceView.fxml", actionEvent, reunion);

    }

    public void eventTomarAsistencia(ActionEvent actionEvent) {


        sceneUtils.loadStage("/views/pickCSVView.fxml", actionEvent);

    }

    @Override
    public void Inicializador() {
        //Metodo POST para obtener las reuniones llevadas a cabo

        List<Reunion> reuniones = requestUtils.getAttendances();

        //Asginar esas reuniones al ComboBox
        cboxSeleccionReunion.getItems().addAll(reuniones);
        cboxSeleccionReunion.setConverter(new ReunionConverter());

    }

    //Eliminar una vez implemente el metodo post que obtiene las reuniones que se han registrado
    /*private ArrayList<Reunion> createCollection(){
        ArrayList<Reunion> reuniones = new ArrayList<>();

        Participante participante = new Participante("Adiel Lara Ledesma", "Ayer", "Hoy", "Todo el dia", "adiel.lara.consultor@axa.com.mx", "Moderador");
        Participante participante2 = new Participante("Adiel Lara Ledesma 2", "Ayer", "Hoy", "Todo el dia", "2adiel.lara.consultor@axa.com.mx", "Moderador");
        ArrayList<Participante> participantes = new ArrayList<>();
        participantes.add(participante);
        participantes.add(participante2);

        reuniones.add(new Reunion(1, "42", "SDLC || QAO & SWA | Capacitación \"Mejores Prácticas para el Desarrollo de Software\"", "20-10-2021 12:04:20", "20-10-2021 01:04:20", "234-756-234", participantes));
        reuniones.add(new Reunion(2, "52", "SDLC || QAO & SWA | Capacitación \"Mejores Prácticas para el Desarrollo de Software\"", "21-10-2021 12:04:20", "21-10-2021 01:04:20", "123-234-234", participantes));
        reuniones.add(new Reunion(3, "32", "SDLC || QAO & SWA | Capacitación \"Mejores Prácticas para el Desarrollo de Software\"", "22-10-2021 12:04:20", "22-10-2021 01:04:20", "432-234-432", participantes));
        reuniones.add(new Reunion(4, "62", "SDLC || QAO & SWA | Capacitación \"Mejores Prácticas para el Desarrollo de Software\"", "23-10-2021 12:04:20", "23-10-2021 01:04:20", "123-754-453", participantes));

        return reuniones;
    }*/


}
