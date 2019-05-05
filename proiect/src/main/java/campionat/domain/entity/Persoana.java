package main.java.campionat.domain.entity;

public class Persoana {

    private int id;
    private String nume_persoana;

    public Persoana(int id, String nume_persoana) {
        this.id = id;
        this.nume_persoana = nume_persoana;
    }

    public Persoana(){
        id=-1;
        nume_persoana="Undefined";
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setNume_persoana(String nume_persoana) {
        this.nume_persoana = nume_persoana;
    }
    public String getNume_persoana() {
        return nume_persoana;
    }

}
