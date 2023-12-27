/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private static List<InventoryItem> eszkoztar = new ArrayList<InventoryItem>();
    private static List<Helyszin> helyszinek = new ArrayList<Helyszin>();
    private static List<Utvonal> utvonalak = new ArrayList<Utvonal>();
    private static List<Targy> targyak = new ArrayList<Targy>();
    private static List<TargyAr> targyar = new ArrayList<TargyAr>();
    private static List<Feltetel> feltetelek = new ArrayList<Feltetel>();
    private static List<Talal> talal = new ArrayList<Talal>();
    
    // Paraméterek:Karakter
    private static int eletero = 10;
    private static int maxEletero = 10;
    private static List<String> hibak;
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
            eszkoztar.clear();
            helyszinek.clear();
            utvonalak.clear();
            targyak.clear();
            targyar.clear();
            feltetelek.clear();
            talal.clear();
            //Helyszín
            int i = 0;
            for(String item : uploadList("helyszin.txt")){
                try {
                    String[] sp = item.split(";");
                    helyszinek.add(new Helyszin(Integer.parseInt(sp[0]), sp[1], 
                            Integer.parseInt(sp[2]) == 1, Integer.parseInt(sp[3]) == 1, 
                            Integer.parseInt(sp[4]) == 1, Integer.parseInt(sp[5]),
                            Byte.parseByte(sp[6]) ,sp[7], sp[8])); 
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
                    targyak.add(new Targy(Integer.parseInt(sp[0]), sp[1])); 
                } catch (Exception e) {
                    System.out.println(String.format("A targyak.txt fájl %d. sorával probléma akadt.", i));
                }
                i++;
            }
            // TargyAr
            for(String item : uploadList("targyar.txt")){
                try {
                    String[] sp = item.split(";");
                    targyar.add(new TargyAr(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]), Integer.parseInt(sp[2]))); 
                } catch (Exception e) {
                    System.out.println(String.format("A targyak.txt fájl %d. sorával probléma akadt.", i));
                }
                i++;
            }
            // Feltetel
            for(String item : uploadList("feltetelek.txt")){
                try {
                    String[] sp = item.split(";");
                    feltetelek.add(new Feltetel(Integer.parseInt(sp[0]), sp[1])); 
                } catch (Exception e) {
                    System.out.println(String.format("A targyak.txt fájl %d. sorával probléma akadt.", i));
                }
                i++;
            }
            // Talal
            for(String item : uploadList("talal.txt")){
                try {
                    String[] sp = item.split(";");
                    //helyszinek.add(new Helyszin(Integer.parseInt(sp[0]), sp[1], Byte.parseByte(sp[2]), Integer.parseInt(sp[3]), sp[4], sp[5])); 
                } catch (Exception e) {
                    System.out.println(String.format("A targyak.txt fájl %d. sorával probléma akadt.", i));
                }
                i++;
            }
        }
       /* for(Helyszin item: helyszinek){
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
                //System.out.println(items.get(i).toString());
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
                    try {
                        aktualisHelyszin = idg.stream()
                            .filter(x -> x.getEgtaj().toLowerCase().equals(args[1].toLowerCase()))
                            .findFirst().get().getCelID();
                        Helyszin hly = helyszinek.stream().filter(x -> x.getID() == aktualisHelyszin).findFirst().get();
                        if(hly.megy()){
                            setHelyszin();
                        }   
                    } catch (Exception e) {
                        hibak.add("HIBA: Ebbe az irányba nem mehetsz!");
                    }
                    
                    // életerőcsökkentés, ha helyszínváltás történt
                }
                else{
                    leiras = "A játéknak vége! Elpatkoltál.";
                }
                //Helyszin.mutat átnézése
                break;
            case "felvesz":
                List<Talal> tl = talal.stream().filter(x -> x.getHelyszinID() == aktualisHelyszin).toList();
                List<Targy> tr = targyak.stream().filter(x -> x.getNev().toLowerCase() == args[1].toLowerCase()).toList();
                
                for (Talal item : tl){
                    for (Targy item2 : tr){
                        if(item.getTargyID() == item2.getID()){
                            if(item.Felvesz()){
                                if(eszkoztar.stream().filter(x -> x.getTargyID() == item.getTargyID()).count() == 0){
                                    eszkoztar.add(new InventoryItem(item.getTargyID()));
                                }
                                eszkoztar.stream().filter(x -> x.getTargyID() == item2.getID()).findFirst().get().ad(1);
                                break;
                            }
                        }
                    }
                }
                if(true){
                    leiras = String.format("Rendben, a következő tárgyat felvetted: %s", "");                }
                break;
            default:
                Helyszin hly = helyszinek.stream().filter(x -> x.getID() == aktualisHelyszin).findFirst().get();
                String s = "";
                for(String item : args){
                    s += item+ " ";
                }
                StringBuffer sb = new StringBuffer(s);
                sb.deleteCharAt(s.length() - 1);
                List<Utvonal> utv = utvonalak.stream()
                        .filter(x -> x.getStartID() == aktualisHelyszin).toList();
                //System.out.println("" + );
                Feltetel felt = feltetelek.stream()
                        .filter(x -> x.getMegkozelitesiFeltetel().toLowerCase().equals(sb.toString().toLowerCase())).findFirst().get();
                for (Utvonal item : utv){
                    if(item.getCelID() == felt.getHelyszinID()){
                        System.out.println(item.getCelID() == felt.getHelyszinID());
                        aktualisHelyszin = item.getCelID();
                        setHelyszin();
                        break;
                    }
                }
                //Helyszin dönt, ad-e, vagy nem
                break;
        }
        if (args.length > 1 && testCheck()){
            //helyszinek.get(0).getClass();
        }
        update();
        return false;
    }
    
    private static boolean setHelyszin(){
        Helyszin hly = helyszinek.stream().filter(x -> x.getID() == aktualisHelyszin).findFirst().get();
        // aktualisHelyszin = helyszinek.get(aktualisHelyszin);
        leiras = hly.getLeiras();
        setEletero(hly.getAdEletero());
        ugrasSzam++;
        if(ugrasSzam > 2){
            eletero--;
        }
        return false;
    }
    
    private static boolean setEletero(int adEletero){
        eletero += eletero + adEletero > maxEletero ? maxEletero : eletero + adEletero;
        return false;
    }
    // Lekérdezés függvények
    public static int getEletero(){
        return eletero;
    }
    public static int getMaxEletero(){
        return maxEletero;
    }
    public static String[] getEszkoztarGUI(){
        List<String> st = new ArrayList<String>();
        for (InventoryItem item : eszkoztar){
            Targy t = targyak.stream().filter(x-> x.getID() == item.getTargyID()).findFirst().get();
            st.add(t.getNev() + ": " + item.getMenny());
        }
        String[] starr = Arrays.copyOf(st.toArray(), st.toArray().length, String[].class);
        return starr;
    }
    
    public static String[] getTalal(){
        List<String> st = new ArrayList<String>();
        List<Talal> tl = talal.stream().filter(x -> x.getHelyszinID() == aktualisHelyszin).toList();
        for(Talal item : tl){
            st.add(targyak.stream().filter(x -> x.getID() == item.getTargyID()).findFirst().get().getNev() + ": " + item.getMenny());
        }
        String[] starr = Arrays.copyOf(st.toArray(), st.toArray().length, String[].class);
        System.out.println(starr);
        return starr;
    }
    
    public static String getLeiras(){
        return leiras;
    }
    
    public static String getKep(){
        return helyszinek.stream().filter(x -> x.getID() == aktualisHelyszin).findFirst().get().getKep();
    }
}

class InventoryItem{
    private int targyID;
    private int menny;
    
    public InventoryItem(int targyID){
        this.menny = 0;
        this.targyID = targyID;
    }
    
    public int getTargyID(){
        return targyID;
    }
    
    public int getMenny(){
        return menny;
    }
    
    public boolean ad(int menny){
        boolean both = false;
        if(this.menny + menny >= 0){
            this.menny += menny;
            both = true;
        }
        return both;
        
    }
}

class Helyszin{
    private int id;
    private String nev;
    private String pathImage;
    private String leiras;
    
    private int megkozelites = 0;
    private int maxMegkozelites;
    private int eleteroAr;
    private boolean zart; // Útvonalon keresztül nem megközelíthető
    private boolean nevZart;
    private boolean korlatlanMegkozelites;
    
    
    // private List<String> talaltTargyak;
    
    
    
   public Helyszin(int id, String nev, boolean zart, boolean nevZart, boolean korlatlanMegkoz, 
           int maxMegkoz, byte adEletero, String pathImage, String leiras){ 
       this.id = id;
       this.nev = nev;
       this.pathImage = pathImage;
       this.leiras = leiras;
       
       this.korlatlanMegkozelites = korlatlanMegkoz;
       this.megkozelites = 0;
        this.maxMegkozelites = maxMegkoz;
        this.eleteroAr = adEletero;
        this.zart = zart;
        this.nevZart = nevZart;
    }
    
    public boolean megy(){
        boolean both = false;
        if((maxMegkozelites < 0 || maxMegkozelites - megkozelites > 0) && !zart){
            both = true;
            megkozelites++;
        }
        return both;
    }
    
    public boolean mFeltetel(String feltetel){
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
    public String getKep(){
        return pathImage;
    }
    
    public int getAdEletero(){
        return eleteroAr;
    }
   /* public List<String> getTargyak(){
        return talaltTargyak;
    }*/
   /* public String getMegkozelitesiFeltetel(){
        return megkozelitesiFeltetel;
    }*/
}

class Utvonal{
    private int startID = -1;
    private int celID = -1;
    private String egtaj;
    
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
    private int id;
    private String nev;
    
    public Targy(int id, String nev){
        this.id = id;
        this.nev = nev;
    }
    
    public int getID(){
        return id;
    }
    
    public String getNev(){
        return nev;
    }
}
class Talal{
    private int helyszinID;
    private int targyID;
    private int menny;
        
    public Talal(int helyszinID, int targyID, int menny){
        this.helyszinID = helyszinID;
        this.targyID = targyID;
        this.menny = menny;
    }
    
    public int getHelyszinID(){
        return helyszinID;
    }
    
    public int getTargyID(){
        return targyID;
    }
    
    public int getMenny(){
        return menny;
    }
    
    public boolean Felvesz(){
        boolean both = false;
        if(menny -1 >= 0){
            menny--;
            both = true;
        }
        return both;
    }
}

class Feltetel{
    private int helyszinID;
    private String megkozelitesiFeltetel;
    
    public Feltetel(int helyszinID, String megkozelitesiFeltetel){
        this.helyszinID = helyszinID;
        this.megkozelitesiFeltetel = megkozelitesiFeltetel;
    }
    
    public int getHelyszinID(){
        return helyszinID;
    }
    
    public String getMegkozelitesiFeltetel(){
        return megkozelitesiFeltetel;
    }
    
    public boolean testFeltetel(String feltetel){
        return megkozelitesiFeltetel.equals(feltetel);
    }
}

class TargyAr{
    private int helyszinID;
    private int targyID;
    private int ar;
    
    public TargyAr(int helyszinID, int targyID, int ar){
        this.helyszinID = helyszinID;
        this.targyID = targyID;
        this.ar = ar;
    }
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