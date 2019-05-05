package main.java.campionat.domain.entity;

public class Suporter extends Persoana {

    private String jucator_favorit;
    //private static int nr_suporteri=0;

    public Suporter(int id, String nume_persoana, String jucator_favorit) {
        super(id, nume_persoana);
        this.jucator_favorit = jucator_favorit;
        //nr_suporteri++;
    }

    public Suporter(){
        super();
        jucator_favorit="Undefined";
       // nr_suporteri++;
    }

   // protected static int getNr_suporteri() {return nr_suporteri;}
    public String getJucatorFavorit(){
        return jucator_favorit;
    }
    public void setJucatorFavorit(String jucator_favorit){
        this.jucator_favorit=jucator_favorit;
    }



}
