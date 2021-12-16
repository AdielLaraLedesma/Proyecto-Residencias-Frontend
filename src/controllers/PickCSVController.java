package controllers;

import dto.Participante;
import dto.dtoPrueba;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import dto.Reunion;
import utils.DateUtils;
import utils.HttpRequestUtils;
import utils.SceneUtils;
import utils.StringUtils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class PickCSVController extends PadreController{

    private HttpRequestUtils requestUtils = new HttpRequestUtils();

    //DTO
    private Reunion reunion = new Reunion();

    //Componente para hacer el ruteo de ventanas
    SceneUtils sceneUtils = new SceneUtils();

    //Componentes de la vista
    @FXML
    private TextField txtfieldRuta;
    @FXML
    private VBox vboxDerecha;
    @FXML
    private CheckBox cboxConfirmar;



    public void eventConsultarAsistencia(ActionEvent actionEvent) {
        sceneUtils.loadStage("/views/pickMeetingView.fxml", actionEvent, null);
    }

    public void eventSeleccionarArchivo(ActionEvent actionEvent) throws IOException {
        //Open Window to Read a CSV file
        File file = readCSVFile();

        //Logic to convert the csv file into Reunion Data Transfer Object
        convertCSVFileToReunionDto(file);

        if(file != null)
            txtfieldRuta.setText(file.toString());


    }

    private File readCSVFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos CSV", "*.csv"));

        File file = fileChooser.showOpenDialog(vboxDerecha.getScene().getWindow());

        if (file  != null) {
            return file;
        }

        return null;
    }

    private void convertCSVFileToReunionDto(File file) {

        //System.out.println(file);
        List<Participante> participanteList = new ArrayList<>();

        BufferedReader br = null;
        try {
            String[] fields = null;
            br = new BufferedReader(new FileReader(file.toString()));

            String line = "";
            line = br.readLine();
            line = br.readLine();
            fields = line.split("\t");
            reunion.setNumeroParticipantes(fields[1]);


            line = br.readLine();
            fields = line.split("\t");
            reunion.setTitulo(fields[1]);

            line = br.readLine();
            fields = line.split("\t");
            reunion.setHoraInicio(fields[1]);
            //System.out.println(line);
            line = br.readLine();
            fields = line.split("\t");
            reunion.setHoraFin(fields[1]);
            //System.out.println(line);
            line = br.readLine();
            fields = line.split("\t");
            reunion.setIdDepuracion(fields[1]);
            //System.out.println(line);
            line = br.readLine();
            line = br.readLine();;

            while ((line = br.readLine()) != null) {
                fields = line.split("\t");
                //System.out.println(fields[1]);
                Participante participante = new Participante(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], false);

                participanteList.add(participante);
            }

        }catch (Exception ex){

        }finally {
            reunion.setParticipantes(participanteList);
        }





    }

    public void eventCancelar(ActionEvent actionEvent) {
        sceneUtils.loadStage("/views/principalView.fxml", actionEvent, null);
    }

    public void eventAceptar(ActionEvent actionEvent) {

        if( cboxConfirmar.isSelected() ) {


            //Envar POST al backend que solo valida las asistencias
            reunion = requestUtils.postTakeAttendance(reunion, true);

            sceneUtils.loadStage("/views/confirmAttendanceView.fxml", actionEvent, reunion);

            return;
        }
        //Enviar POST al backend
        requestUtils.postTakeAttendance(reunion, false);

        sceneUtils.loadStage("/views/principalView.fxml", actionEvent, null);


    }


    @Override
    public void Inicializador() {

    }
}
