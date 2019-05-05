package main.java.campionat.domain.entity;

public class PresedinteEchipa extends Persoana {

    private String echipa_condusa;
    private int numar_actiuni;

    public PresedinteEchipa(int id, String nume_persoana, String echipa_condusa,int numar_actiuni) {
        super(id, nume_persoana);
        this.echipa_condusa = echipa_condusa;
        this.numar_actiuni=numar_actiuni;
    }

    public PresedinteEchipa() {
        super();
        echipa_condusa="Unknown";
        numar_actiuni=0;
    }


    public void setNumar_actiuni(int numar_actiuni) {
        this.numar_actiuni = numar_actiuni;
    }
    public void setEchipa_condusa(String echipa_condusa) {
        this.echipa_condusa = echipa_condusa;
    }

    public String getEchipa_condusa() {
        return echipa_condusa;
    }
    public int getNumar_actiuni() {
        return numar_actiuni;
    }

}
