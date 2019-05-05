package main.java.campionat.domain.entity;

public class Sponsor extends Persoana {
    private double sponsorizare;
    private double durata_sponsorizare; //de cat timp sponsorizeaza echipa

    public Sponsor(int id, String nume_persoana, double sponsorizare, double durata_sponsorizare) {
        super(id, nume_persoana);
        this.sponsorizare = sponsorizare;
        this.durata_sponsorizare = durata_sponsorizare;
    }

    public Sponsor(){
        super();
        sponsorizare=-1;
        durata_sponsorizare=-1;
    }

    public double getSponsorizare() {
        return sponsorizare;
    }

    public void setSponsorizare(double sponsorizare) {
        this.sponsorizare = sponsorizare;
    }

    public double getDurata_sponsorizare() {
        return durata_sponsorizare;
    }

    public void setDurata_sponsorizare(double durata_sponsorizare) {
        this.durata_sponsorizare = durata_sponsorizare;
    }

}
