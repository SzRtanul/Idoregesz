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
    private static List<Utvonal> utvonalak = new ArrayList<Utvonal>();
    private static List<Targy> targyak = new ArrayList<Targy>();
    // Paraméterek:Karakter
    private static int eletero = 10;
    private static int maxEletero = 10;
    //private static List<String> targyak;
    private static int ugrasSzam = 0;
    private static String leiras;
    
    // Előkészület
    public static void Restart(boolean reupload){
        aktualisHelyszin = 1;
        eletero = 10;
        maxEletero = 10;
        ugrasSzam = 0;
        if(reupload) 
        {
            helyszinek.clear();
            utvonalak.clear();
            targyak.clear();
            //Helyszín
            int i = 0;
            for(String item : uploadList("helyszinek.txt")){
                try {
                    String[] sp = item.split(";");
                    helyszinek.add(new Helyszin(Integer.parseInt(sp[0]), sp[1], Byte.parseByte(sp[2]), Integer.parseInt(sp[3]), sp[4], sp[5])); 
                } catch (Exception e) {
                    System.out.println(String.format("A helyszinek.txt fájl %d. sorával probléma akadt.", i));
                }
                i++;
            }
            // Útvonal
            i=0;
            for(String item : uploadList("utvonalak.txt")){
                try {
                    String[] sp = item.split(";");
                    utvonalak.add(new Utvonal(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]), sp[2])); 
                } catch (Exception e) {
                    System.out.println(String.format("Az utvonalak.txt fájl %d. sorával probléma akadt.", i));
                }
                i++;
            }
            
            // Tárgy
            i=0;
            for(String item : uploadList("targyak.txt")){
                try {
                    String[] sp = item.split(";");
                    //helyszinek.add(new Helyszin(Integer.parseInt(sp[0]), sp[1], Byte.parseByte(sp[2]), Integer.parseInt(sp[3]), sp[4], sp[5])); 
                } catch (Exception e) {
                    System.out.println(String.format("A targyak.txt fájl %d. sorával probléma akadt.", i));
                }
                i++;
            }
        }
        /*for(Helyszin item: helyszinek){
            System.out.println(String.format("%d%s%s", item.getID(), item.getNev(), item.getLeiras()));
        }*/
        leiras = helyszinek.stream()
                .filter(item -> item.getID() == aktualisHelyszin)
                .findFirst().get().getLeiras();
        update();
    }
    
    private static List<String> uploadList(String filename){
        //Helyszinek
        File f = new File(filename);
        List<String> items = new ArrayList<String>();
        try(Scanner sc = new Scanner(f)){
            for(int i=0; sc.hasNextLine(); i++){
                items.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Nem található a fájl.");
            //Logger.getLogger(MusicDriveL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    private static boolean testCheck(){      
        return true;
    }

    
    // Játék
    public static boolean Command(String[] args){
        switch(args[0].toLowerCase()){
            case "megy":
                if(eletero > 0){
                    List<Utvonal> idg = utvonalak.stream().filter(x -> x.getStartID() == aktualisHelyszin).toList();
                    aktualisHelyszin = idg.stream()
                            .filter(x -> x.getEgtaj().toLowerCase().equals(args[1].toLowerCase()))
                            .findFirst().get().getCelID();
                    leiras = helyszinek.stream().filter(x -> x.getID() == aktualisHelyszin).findFirst().get().getLeiras();
                    // életerőcsökkentés, ha helyszínváltás történt
                }
                else{
                    leiras = "A játéknak vége! Elpatkoltál.";
                }
                //Helyszin.mutat átnézése
                break;
            case "ad":
            case "használ":
                // rendelkezésre áll a tárgy?
                break;
            case "felvesz":
                if(true){
                    leiras = String.format("Rendben, a következő tárgyat felvetted: %s", "");                }
                break;
            default:
                break;
        }
        if (args.length > 1 && testCheck()){
            //helyszinek.get(0).getClass();
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
    public static List<Targy> getTargyak(){
        return targyak;
    } 
    public static String getLeiras(){
        return leiras;
    }
}

class Helyszin{
    private int id;
    private String nev;
    private String leiras;
    private String megkozelitesiFeltetel;
    
    private int megkozelites = 0;
    private int maxMegkozelites;
    private byte zart;
    
    private int eleteroAr;
    private String[] targyAr;
    // private List<String> talaltTargyak;
    
    
    
   public Helyszin(int id, String nev, byte zart, int maxMegkozelites, String megkozelitesiFeltetel, String leiras){
       this.megkozelites = 0;
       
       this.id = id;
       this.nev = nev;
       this.leiras = leiras;
       
       this.megkozelitesiFeltetel = megkozelitesiFeltetel;
       this.maxMegkozelites = maxMegkozelites;
       this.zart = zart;
        
    }
    
    public boolean megy(){
        megkozelites++;
        return false;
    }
    
    public boolean megkozelites(String feltetel){
        return false;
    }
    
    public int getID(){
        return id;
    }
    
    public String getNev(){
        return nev;
    }
    public String getLeiras(){
        return leiras;
    }
   /* public List<String> getTargyak(){
        return talaltTargyak;
    }*/
    public String getMegkozelitesiFeltetel(){
        return megkozelitesiFeltetel;
    }
}

class Utvonal{
    int startID = -1;
    int celID = -1;
    String egtaj;
    
    public Utvonal(int start, int cel, String egtaj){
        this.startID = start;
        this.celID = cel;
        this.egtaj = egtaj;
    }
    
    public int getStartID(){
        return startID;
    }
    public int getCelID(){
        return celID;
    }
    public String getEgtaj(){
        return egtaj;
    }
}

class Targy{
    int id;
    String nev;
    
    public Targy(int id, String nev){
        this.id = id;
        this.nev = nev;
    }
}
class Talal{
    int helyszinID;
    int targyID;
    int menny;
        
    public Talal(int helyszinID, int targyID, int menny){
        this.helyszinID = helyszinID;
        this.targyID = targyID;
        this.menny = menny;
    }
}

class Tortenhet{

}


enum Irany{
    Kelet,
    DelKelet,
    EszakKelet,
    Nyugat,
    DelNyugat,
    EszakNyugat,
    Del,
    Eszak
}