/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.io.*;

import ihm.Interface;

/**
 *
 * @author Yoann
 */
public class LectureLog {

    public static void Lire(char type, int numFichier) {
        char chaine = '0';
        Interface data = Interface.getInstance();
        data.clearData();
        String tab[];
        tab = Log4j.NomsFichiers();
        String fichier;
        if (numFichier == 7 ^ numFichier == 0) {
            fichier = "./log/Log";
        } else {
            fichier = "./log/" + tab[numFichier];
        }
        String pri = "", dat = "", cla = "", fic = "", mes = "";

        try {
            //initialisation du buffer de lecture
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            int i, j, k, l;

            switch (type) {
                case 'D':
                case 'I':
                case 'W':
                case 'E':
                case 'F':
                    while ((ligne = br.readLine()) != null) {
                        chaine = ligne.charAt(0);
                        if (chaine == type) {
                            i = ligne.indexOf('|');
                            pri = ligne.substring(0, i);
                            ligne = ligne.substring(i + 1);
                            j = ligne.indexOf('|');
                            dat = ligne.substring(0, j);
                            ligne = ligne.substring(j + 1);
                            k = ligne.indexOf('|');
                            cla = ligne.substring(0, k);
                            ligne = ligne.substring(k + 1);
                            l = ligne.indexOf('|');
                            fic = ligne.substring(0, l);
                            ligne = ligne.substring(l + 1);
                            mes = ligne.substring(0);
                            data.addData(pri, dat, mes, cla, fic);
                        }
                    }
                    break;

                case 'A':
                default:
                    while ((ligne = br.readLine()) != null) {
                        i = ligne.indexOf('|');
                        pri = ligne.substring(0, i);
                        ligne = ligne.substring(i + 1);
                        j = ligne.indexOf('|');
                        dat = ligne.substring(0, j);
                        ligne = ligne.substring(j + 1);
                        k = ligne.indexOf('|');
                        cla = ligne.substring(0, k);
                        ligne = ligne.substring(k + 1);
                        l = ligne.indexOf('|');
                        fic = ligne.substring(0, l);
                        ligne = ligne.substring(l + 1);
                        mes = ligne.substring(0);
                        data.addData(pri, dat, mes, cla, fic);
                    }
                    break;

            }
            br.close();
        } catch (Exception e) {
            Log4j.logger.error(e);
        }
    }
}
