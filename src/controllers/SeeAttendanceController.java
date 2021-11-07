package controllers;

import dto.Participante;
import dto.Reunion;
import dto.TablaParticipantes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.SceneUtils;

public class SeeAttendanceController extends PadreController{

    private Reunion reunion = new Reunion();

    //Componente para hacer el ruteo de ventanas
    SceneUtils sceneUtils = new SceneUtils();

    //Componentes de la vista
    @FXML
    private TextField txtFieldNombreReunion;
    @FXML
    private TextField txtFieldTotalParticipantes;
    @FXML
    private TextField txtFieldFechaInicio;
    @FXML
    private TableColumn tColNombre;
    @FXML
    private TableColumn tColHoraUnion;
    @FXML
    private TableColumn tColHoraSalida;
    @FXML
    private TableColumn tColDuracion;
    @FXML
    private TableColumn tColCorreo;
    @FXML
    private TableColumn tColRol;
    @FXML
    private TableView<TablaParticipantes> tableParticipantes;

    private final ObservableList<TablaParticipantes> dataList = FXCollections.observableArrayList();



    public void eventAceptar(ActionEvent actionEvent) {
        sceneUtils.loadStage("/views/principalView.fxml", actionEvent);
    }

    public void eventTomarAsistencia(ActionEvent actionEvent) {
        sceneUtils.loadStage("/views/pickCSVView.fxml", actionEvent);
    }

    @Override
    public void setObjeto(Object objeto){
        this.reunion = (Reunion) objeto;
    }

    @Override
    public void Inicializador() {
        //Llenar tabla
        fillTable();

        //Llenar datos de abajo
        fillData();



    }

    private void fillData() {
        txtFieldNombreReunion.setText(reunion.getTitulo());
        txtFieldTotalParticipantes.setText(reunion.getNumeroParticipantes());
        txtFieldFechaInicio.setText(reunion.getHoraInicio());
    }

    private void fillTable() {
        for (Participante participante : reunion.getParticipantes()){


            String asistencia = participante.isAsistencia() ? "Asistió" : "No Asistió";

            TablaParticipantes record = new TablaParticipantes(
                    participante.getNombreCompleto(),
                    participante.getHoraUnion(),
                    participante.getHoraSalida(),
                    participante.getDuracion(),
                    participante.getEmail(),
                    participante.getRol(),
                    asistencia);
            dataList.add(record);
        }
        tColNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombreCompleto"));
        tColHoraUnion.setCellValueFactory(
                new PropertyValueFactory<>("horaUnion"));
        tColHoraSalida.setCellValueFactory(
                new PropertyValueFactory<>("horaSalida"));
        tColDuracion.setCellValueFactory(
                new PropertyValueFactory<>("duracion"));
        tColCorreo.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        tColRol.setCellValueFactory(
                new PropertyValueFactory<>("rol"));


        tableParticipantes.setItems(dataList);

    }


}
