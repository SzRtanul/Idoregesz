/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
/**
 *
 * @author SzabóRoland(SZF_2023
 */
public class Idoregesz {
    
     // <editor-fold defaultstate="collapsed" desc="Event implmentation">
    
    private static Set<EI.charachterListener> listeners = new HashSet();
    
    public static void addListener(EI.charachterListener listener) {
        listeners.add(listener);
    }
    
    public static void removeListener(EI.charachterListener listener) {
        listeners.remove(listener);
    }
    
    private static void update(){
       listeners.forEach(x -> x.charachterUpdate());
    }
    // </editor-fold>
    // Paraméterek
    static int aktualisHelyszin = 1;
    private static List<Helyszin> helyszinek = new ArrayList<Helyszin>();
    // Paraméterek:Karakter
    private static int eletero = 10;
    private static int maxEletero = 10;
    private static List<String> targyak;
    private static int ugrasSzam = 0;
    
    // Előkészület
    public static void Restart(boolean reupload){
        aktualisHelyszin = 1;
        if(reupload) uploadHelyszinList();
        update();
    }
    
    private static void uploadHelyszinList(){
        File f = new File("helyszinek.txt");
        try(Scanner sc = new Scanner(f)){
            while(sc.hasNextLine()){
                String[] a = sc.nextLine().split(";");
                helyszinek.add(new Helyszin());
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(MusicDriveL.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private static boolean testCheck(){
        return true;
    }
    
    
    // Játék
    public static boolean Command(String[] args){
        switch(args[0]){
            case "megy":
                //Helyszin.mutat átnézése
                break;
            case "eszik":
                break;
            case "felvesz":
               break;
            case "használ":
                break;
            default:
                break;
        }
        if (args.length > 1 && testCheck()){
            helyszinek.get(0).getClass();
        }
        update();
        return false;
    }
    
    private boolean setHelyszin(){
        // aktualisHelyszin = helyszinek.get(aktualisHelyszin);
        return false;
    }
    // Lekérdezés függvények
    public static int getEletero(){
        return eletero;
    }
    public static int getMaxEletero(){
        return maxEletero;
    }
    public static List<String> getTargyak(){
        return targyak;
    } 
    public static String getLeiras(){
        return helyszinek.get(aktualisHelyszin).getLeiras();
    }
}

class Helyszin{
    private String nev;
    private String leiras;
    private List<Mutat> mutat;
    private List<String> targyak;
    private List<String> megkozelitesiFeltetel;
    private boolean zart;
    
    
    public Helyszin(){
        
    }
    
    public String getNev(){
        return nev;
    }
    public String getLeiras(){
        return leiras;
    }
    public List<String> getTargyak(){
        return targyak;
    }
    public List<String> getMegkozelitesiFeltetel(){
        return megkozelitesiFeltetel;
    }
}

class Mutat{
    int helyszinSzam;
    String egtaj;
    
    public Mutat(){

    }
    
    public int getHelyszinSzam(){
        return helyszinSzam;
    }
}