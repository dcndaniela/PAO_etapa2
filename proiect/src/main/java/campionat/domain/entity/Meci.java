package main.java.campionat.domain.entity;

public class Meci {

    private String arbitru;
    private String echipa1;
    private String echipa2;
    private String scor;
    private String data_meci;

    public String getData_meci() {
        return data_meci;
    }

    public void setData_meci(String data_meci) {
        this.data_meci = data_meci;
    }


    public Meci(String arbitru, String ech1, String ech2, String scor, String data_meci){
        this.arbitru=arbitru;
        this.echipa1=ech1;
        this.echipa2=ech2;
        this.scor=scor;
        this.data_meci=data_meci;
    }

    public Meci(){
        echipa1="Undefined";
        echipa2="Undefined";
        scor="Undefined";
        data_meci="Undefined";
    }

    public String getArbitru() {
        return arbitru;
    }
    public void setArbitru(String arbitru) {
        this.arbitru = arbitru;
    }

    public String getEchipa1() {
        return echipa1;
    }
    public void setEchipa1(String echipa1) {
        this.echipa1 = echipa1;
    }

    public String getEchipa2() {
        return echipa2;
    }
    public void setEchipa2(String echipa2) {
        this.echipa2 = echipa2;
    }

    public String getScor() {
        return scor;
    }
    public void setScor(String scor) {
        this.scor = scor;
    }

}
