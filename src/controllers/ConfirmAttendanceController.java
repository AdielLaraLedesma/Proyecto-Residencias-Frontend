package controllers;

import dto.Participante;
import dto.Reunion;
import dto.TablaParticipantes;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.SceneUtils;


import java.util.List;

public class ConfirmAttendanceController extends PadreController{

    //Objeto obtenido al llamar ventana
    private Reunion reunion = new Reunion();

    //Componente para hacer el ruteo de ventanas
    SceneUtils sceneUtils = new SceneUtils();

    //private int posicionSeleccionadaEnTabla;

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
    private TableColumn tColAsistio;

    @FXML
    private TableView<TablaParticipantes> tableParticipantes;

    private final ObservableList<TablaParticipantes> dataList = FXCollections.observableArrayList();


    public void eventConsultarAsistencia(ActionEvent actionEvent) {
        sceneUtils.loadStage("/views/pickMeetingView.fxml", actionEvent);
    }


    public void eventConfirmar(ActionEvent actionEvent) {
        //Aqui llamar el POST del backend para guardar asistencia.


    }


    public void eventCancelar(ActionEvent actionEvent) {
        sceneUtils.loadStage("/views/principalView.fxml", actionEvent);
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


            String asistencia = participante.isAsistencia() ? "Asistió" : "No asistió";

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
        tColAsistio.setCellValueFactory(
                new PropertyValueFactory<>("asistencia"));

        tableParticipantes.setItems(dataList);

        final ObservableList<TablaParticipantes> tablaPersonaSel = tableParticipantes.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaPersonms);
    }


    //Para seleccionar una celda de la tabla
    private final ListChangeListener<TablaParticipantes> selectorTablaPersonms =
            new ListChangeListener<TablaParticipantes>() {
                @Override
                public void onChanged(Change<? extends TablaParticipantes> c) {
                    cambiarAsistencia();
                }
            };

    private void cambiarAsistencia() {
        final TablaParticipantes participante = getTablaPersonasSeleccionada();

        if(participante != null){
            participante.setAsistencia(participante.getAsistencia() == "Asistió" ? "No asistió" : "Asistió");

            for(Participante participante2 : reunion.getParticipantes())
                if (participante2.getNombreCompleto() == participante.getNombreCompleto())
                    participante2.setAsistencia(participante.getAsistencia().equals("No asistió") ? false : true);



            tableParticipantes.refresh();

        }

    }

    public TablaParticipantes getTablaPersonasSeleccionada(){
        if(tableParticipantes != null){
            List<TablaParticipantes> tabla = tableParticipantes.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1){
                final TablaParticipantes participantes = tabla.get(0);
                return participantes;
            }
        }
        return null;
    }


    @Override
    public void setObjeto(Object objeto){
        this.reunion = (Reunion) objeto;
    }

}
