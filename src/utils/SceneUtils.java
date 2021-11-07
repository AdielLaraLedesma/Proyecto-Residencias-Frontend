package utils;

import java.io.IOException;


import controllers.PadreController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;


public class SceneUtils {


    public void loadStage(String url,
                          Event  event,
                          Object object){

        try {

            Object eventSource = event.getSource();

            Node sourceAsNode = (Node) eventSource ;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();

            Stage stage = (Stage) window ;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Parent root = loader.load();

            PadreController padreController = loader.getController();
            padreController.setObjeto(object);
            padreController.Inicializador();


            Scene scene = new Scene(root);

            stage.setScene(scene);

        } catch (IOException ex) {
            System.err.println("Un error ha ocurrido");
        }

    }
    public void loadStage(String url,
                          Event  event){
        loadStage(url, event, null);
    }

}
