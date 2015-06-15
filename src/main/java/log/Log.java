/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Yoann
 */
public class Log {

    private final SimpleStringProperty priorite;
    private final SimpleStringProperty date;
    private final SimpleStringProperty message;
    private final SimpleStringProperty classe;
    private final SimpleStringProperty fichier;

    public Log(String pPriorite, String pDate, String pMessage, String pClasse, String pFichier) {
        this.priorite = new SimpleStringProperty(pPriorite);
        this.date = new SimpleStringProperty(pDate);
        this.message = new SimpleStringProperty(pMessage);
        this.classe = new SimpleStringProperty(pClasse);
        this.fichier = new SimpleStringProperty(pFichier);

    }

    public String getPriorite() {
        return priorite.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getClasse() {
        return classe.get();
    }

    public String getFichier() {
        return fichier.get();
    }

    public String getMessage() {
        return message.get();
    }
}
