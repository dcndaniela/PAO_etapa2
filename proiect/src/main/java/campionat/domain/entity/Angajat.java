package main.java.campionat.domain.entity;


public abstract class Angajat {
    private int id;
    private String nume;
    private double varsta;
    private String echipa;

    public Angajat(int id,String nume,double v,String echipa){
        this.id=id;
        this.nume=nume;
        this.varsta=v;
        this.echipa=echipa;
    }
   public Angajat(){
        varsta=-1;
        id=-1;
        nume="Undefined";
        echipa="Undefined";
    }

    public void setVarsta(double varsta) {
        this.varsta = varsta;
    }
    public double getVarsta(){
        return varsta;
    }

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getNume(){
        return nume;
    }

    public void setEchipa(String echipa) {
        this.echipa = echipa;
    }
    public String getEchipa(){
        return echipa;
    }


    public String getTip(){
        return "angajat";
    }
    //metoda abstracta implementatata in subclase
    abstract public double esteApt();

    public String getEchipaLaCareAFostAngajatInTrecut(){
        return "Nu a mai fost angajat la alta echipa in trecut";
    }

}

