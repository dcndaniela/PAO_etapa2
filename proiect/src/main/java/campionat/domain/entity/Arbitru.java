package main.java.campionat.domain.entity;

public class Arbitru extends Persoana {

    private String facultate;
    private double salariu;

    public Arbitru(int id, String nume, String facultate,double salariu){
        super(id, nume);
        this.facultate=facultate;
        this.salariu=salariu;
    }
    public Arbitru(){
        super();
        facultate="Undefined";
        salariu=0;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }
    public String getFacultate() {
        return facultate;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }
    public double getSalariu() {
        return salariu;
    }




}
