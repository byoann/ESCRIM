/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.io.File;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Priority;

/**
 *
 * @author Yoann
 */
public class Log4j {

    public static Logger logger = Logger.getLogger(Log4j.class);
    static DailyRollingFileAppender appender;

    public Log4j() {
        StringBuilder motif = new StringBuilder();

        motif.append("%p | %d{yyyy/MM/dd HH:mm:ss} | %C | %F %L | %m %n");
        PatternLayout layout = new PatternLayout(motif.toString());

        try {
            appender = new DailyRollingFileAppender();
            appender.setLayout(layout);
            appender.setDatePattern("'-'yyyy-MM-dd"); //Creer un fichier chaque jour
            appender.setFile("./log/Log"); //Nomme le fichier Log
            appender.setThreshold(Priority.DEBUG); //fixe le niveau de priorite pour le log
            appender.activateOptions();
            logger.addAppender(appender);

        } catch (Exception e) {
            logger.error(e);
            System.out.println(e);
        }
    }

    //Recupere les noms des fichiers contenus dans le repertoire log
    public static String[] NomsFichiers() {
        File repertoire = new File("./log/");
        String[] tab = repertoire.list();
        String[] tab2 = {"Vide", "Vide", "Vide", "Vide", "Vide", "Vide", "Vide"};
        int i;
        for (i = 0; i != tab.length; i++) {
            if (tab[i].equalsIgnoreCase("Log")) {
                tab2[0] = tab[i];
            }
        }

        int x = tab.length - 1;
        int y = 1;
        //On test si le nom du fichier commence par "Log", sinon on le supprime
        do {
            if (!tab[x].equalsIgnoreCase("Log")) {
                if (tab[x].startsWith("Log")) {
                    tab2[y] = tab[x];
                    y++;
                    x--;
                } else {
                    File supprimer = new File("./log/" + tab[x]);
                    supprimer.delete();
                    x--;
                }
            } else {
                x--;
            }
        } while ((x != -1) ^ (y == 7));

        if (x != -1) {
            int a;
            for (a = x; a != -1; a--) {
                if ((!tab[a].equalsIgnoreCase("Log")) || (!tab[a].startsWith("Log"))) {
                    File supprimer = new File("./log/" + tab[a]);
                    supprimer.delete();
                }
            }
        }

        return tab2;
    }
}
