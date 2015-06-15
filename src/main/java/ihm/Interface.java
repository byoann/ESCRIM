/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import log.LectureLog;
import log.Log;
import log.Log4j;

/**
 *
 * @author Yoann
 */
public class Interface implements javafx.fxml.Initializable {

    /*  APPAREILS   */
    @FXML
    private CheckBox A300B4;
    @FXML
    private CheckBox An12;
    @FXML
    private CheckBox An22;
    @FXML
    private CheckBox An124;
    @FXML
    private CheckBox An225;
    @FXML
    private CheckBox BAe146;
    @FXML
    private CheckBox B707;
    @FXML
    private CheckBox B727200;
    @FXML
    private CheckBox B737200;
    @FXML
    private CheckBox B747100;
    @FXML
    private CheckBox B747200;
    @FXML
    private CheckBox B747400;
    @FXML
    private CheckBox B757F;
    @FXML
    private CheckBox B767300F;
    @FXML
    private CheckBox MD11;
    @FXML
    private CheckBox DC6;
    @FXML
    private CheckBox DC85455;
    @FXML
    private CheckBox DC862;
    @FXML
    private CheckBox DC871;
    @FXML
    private CheckBox DC873;
    @FXML
    private CheckBox DC1030;
    @FXML
    private CheckBox IL76;
    @FXML
    private CheckBox L10030;
    @FXML
    private CheckBox L188Electra;
    @FXML
    private CheckBox L1011Tristar;

    /*  LOGS  */
    @FXML
    private RadioButton RdALL;
    @FXML
    private MenuItem Log1;
    @FXML
    private MenuItem Log2;
    @FXML
    private MenuItem Log3;
    @FXML
    private MenuItem Log4;
    @FXML
    private MenuItem Log5;
    @FXML
    private MenuItem Log6;
    @FXML
    private MenuItem Log7;
    @FXML
    private TableView<Log> tableauLog = new TableView<>();
    @FXML
    private TableColumn<Log, String> tablePri;
    @FXML
    private TableColumn<Log, String> tableDat;
    @FXML
    private TableColumn<Log, String> tableCla;
    @FXML
    private TableColumn<Log, String> tableFic;
    @FXML
    private TableColumn<Log, String> tableMes;
    private static ObservableList<Log> data = FXCollections.observableArrayList();
    private static int numFichierLog = 7;
    private static Interface instance;

    // Instance pour n'avoir qu'un seul objet 
    public static Interface getInstance() {
        if (null == instance) {
            // Si premier appel
            instance = new Interface();
        }
        return instance;
    }

    //Nettoyer les donnees du tableau logs
    public void clearData() {
        data.clear();
    }

    //Ajouter des donnees au tableau logs
    public void addData(String pri, String dat, String mes, String cla, String fic) {
        data.add(0, new Log(pri, dat, mes, cla, fic)); //Dernier log (en date) en premier (dans le tableau)
    }

    //Creation du tableau des logs
    public void CreationTableauLog() {
        tablePri = new TableColumn<>("Priorite");
        tablePri.setMaxWidth(70);
        tablePri.setMinWidth(70);
        tablePri.setSortable(true);
        tablePri.setEditable(true);
        tablePri.setCellValueFactory(new PropertyValueFactory<Log, String>("priorite"));

        tablePri.setCellFactory(new Callback<TableColumn<Log, String>, TableCell<Log, String>>() {
            @Override
            public TableCell<Log, String> call(TableColumn<Log, String> p) {
                return new TableCell<Log, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            switch (item) {
                                case "DEBUG ":
                                    setStyle("-fx-text-fill: blue");
                                    break;
                                case "INFO ":
                                    setStyle("-fx-text-fill: green");
                                    break;
                                case "WARN ":
                                    setStyle("-fx-text-fill: yellow");
                                    break;
                                case "ERROR ":
                                    setStyle("-fx-text-fill: orange");
                                    break;
                                case "FATAL ":
                                    setStyle("-fx-text-fill: red");
                                    break;
                            }
                            setText(item);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        tableDat = new TableColumn<>("Date");
        tableDat.setMaxWidth(155);
        tableDat.setMinWidth(155);
        tableDat.setSortable(true);
        tableDat.setEditable(true);
        tableDat.setCellValueFactory(new PropertyValueFactory<Log, String>("date"));

        tableMes = new TableColumn<>("Message");
        tableMes.setMinWidth(400);
        tableMes.setSortable(true);
        tableMes.setEditable(true);
        tableMes.setCellValueFactory(new PropertyValueFactory<Log, String>("message"));

        tableCla = new TableColumn<>("Classe");
        tableCla.setMinWidth(140);
        tableCla.setMaxWidth(300);
        tableCla.setSortable(true);
        tableCla.setEditable(true);
        tableCla.setCellValueFactory(new PropertyValueFactory<Log, String>("classe"));

        tableFic = new TableColumn<>("Fichier");
        tableFic.setMinWidth(140);
        tableFic.setMaxWidth(300);
        tableFic.setSortable(true);
        tableFic.setEditable(true);
        tableFic.setCellValueFactory(new PropertyValueFactory<Log, String>("fichier"));

        tableauLog.setEditable(true);
        tableauLog.setItems(data);
        tableauLog.getColumns().addAll(tablePri, tableDat, tableMes, tableCla, tableFic);
    }

    // Initialise au lancement les variables liees a l'IHM
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Instancie l'objet IHM pour le recuperer ailleurs
        instance = this;
        CreationTableauLog();
        Log4j.logger.debug("Debug!");
        Log4j.logger.error("Error!");
        Log4j.logger.info("Info!");
        Log4j.logger.warn("Warn!");
        Log4j.logger.fatal("Fatality!");
    }

    /*  APPAREILS   */
    //Bouton Deselectionner les appareils
    @FXML
    private void ButDeselectAction() {
        A300B4.setSelected(false);
        An12.setSelected(false);
        An22.setSelected(false);
        An124.setSelected(false);
        An225.setSelected(false);
        BAe146.setSelected(false);
        B707.setSelected(false);
        B727200.setSelected(false);
        B737200.setSelected(false);
        B747100.setSelected(false);
        B747200.setSelected(false);
        B747400.setSelected(false);
        B757F.setSelected(false);
        B767300F.setSelected(false);
        MD11.setSelected(false);
        DC6.setSelected(false);
        DC85455.setSelected(false);
        DC862.setSelected(false);
        DC871.setSelected(false);
        DC873.setSelected(false);
        DC1030.setSelected(false);
        IL76.setSelected(false);
        L10030.setSelected(false);
        L188Electra.setSelected(false);
        L1011Tristar.setSelected(false);
    }

    /*  LOGS    */
    // Radio debug
    @FXML
    private void RdDEBUGAction() {
        LectureLog.Lire('D', numFichierLog);
    }

    // Radio info
    @FXML
    private void RdINFOAction() {
        LectureLog.Lire('I', numFichierLog);
    }

    // Radio warn
    @FXML
    private void RdWARNAction() {
        LectureLog.Lire('W', numFichierLog);
    }

    // Radio error
    @FXML
    private void RdERRORAction() {
        LectureLog.Lire('E', numFichierLog);
    }

    // Radio fatal
    @FXML
    private void RdFATALAction() {
        LectureLog.Lire('F', numFichierLog);
    }

    // Radio all
    @FXML
    private void RdALLAction() {
        LectureLog.Lire('A', numFichierLog);
    }

    // Menu tab Logs
    @FXML
    private void MenuLogsAction() {
        LectureLog.Lire('A', numFichierLog);
    }

    // Menu deroulant de la liste des fichiers logs
    @FXML
    private void MenuFichiersLogsAction() {
        //On recupere les noms des fichiers dans le dossier log
        String tab[];
        tab = Log4j.NomsFichiers();
        //On affecte les noms au MenuItem
        Log1.setText(tab[0] + " actuel");
        Log2.setText(tab[1]);
        if (tab[1].equalsIgnoreCase("Vide")) {
            Log2.setDisable(true);
        } else {
            Log2.setDisable(false);
        }
        Log3.setText(tab[2]);
        if (tab[2].equalsIgnoreCase("Vide")) {
            Log3.setDisable(true);
        } else {
            Log3.setDisable(false);
        }
        Log4.setText(tab[3]);
        if (tab[3].equalsIgnoreCase("Vide")) {
            Log4.setDisable(true);
        } else {
            Log4.setDisable(false);
        }
        Log5.setText(tab[4]);
        if (tab[4].equalsIgnoreCase("Vide")) {
            Log5.setDisable(true);
        } else {
            Log5.setDisable(false);
        }
        Log6.setText(tab[5]);
        if (tab[5].equalsIgnoreCase("Vide")) {
            Log6.setDisable(true);
        } else {
            Log6.setDisable(false);
        }
        Log7.setText(tab[6]);
        if (tab[6].equalsIgnoreCase("Vide")) {
            Log7.setDisable(true);
        } else {
            Log7.setDisable(false);
        }
    }

    // Item log1
    @FXML
    private void Log1Action() {
        numFichierLog = 0;
        LectureLog.Lire('A', numFichierLog);
        RdALL.setSelected(true);
    }

    // Item log2
    @FXML
    private void Log2Action() {
        numFichierLog = 1;
        LectureLog.Lire('A', numFichierLog);
        RdALL.setSelected(true);
    }

    // Item log3
    @FXML
    private void Log3Action() {
        numFichierLog = 2;
        LectureLog.Lire('A', numFichierLog);
        RdALL.setSelected(true);
    }

    // Item log4
    @FXML
    private void Log4Action() {
        numFichierLog = 3;
        LectureLog.Lire('A', numFichierLog);
        RdALL.setSelected(true);
    }

    // Item log5
    @FXML
    private void Log5Action() {
        numFichierLog = 4;
        LectureLog.Lire('A', numFichierLog);
        RdALL.setSelected(true);
    }

    // Item log6
    @FXML
    private void Log6Action() {
        numFichierLog = 5;
        LectureLog.Lire('A', numFichierLog);
        RdALL.setSelected(true);
    }

    // Item log7
    @FXML
    private void Log7Action() {
        numFichierLog = 6;
        LectureLog.Lire('A', numFichierLog);
        RdALL.setSelected(true);
    }

}
