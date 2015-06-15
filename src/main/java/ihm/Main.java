/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import log.Log4j;

/**
 *
 * @author Yoann
 */
public class Main extends Application {
    
    public Log4j log = new Log4j();
    
    // La methode start est lancee en premiere position
    // Le main n'est utile que pour les compilateurs qui ne gerent pas bien JavaFx
    @Override
    public void start(Stage principalStage) throws IOException {

        // Charge le fichier fxml et lance Initialize()
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/IHM.fxml"));
        Parent root = (Parent)loader.load();
        
        // Definit la scene et son style
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        // Creer le stage en chargeant la scene
        principalStage.setScene(scene);
        principalStage.sizeToScene();
        principalStage.setTitle("ESCRIM"); // Titre de la fenetre
        principalStage.initStyle(StageStyle.DECORATED); //definit le contour de la fenetre
        principalStage.show();
        
        Log4j.logger.info("Fin Start()");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
