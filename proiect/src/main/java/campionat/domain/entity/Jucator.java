package main.java.campionat.domain.entity;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class Jucator extends Angajat {

    String tip;//atacant, fundas, portar

    public Jucator(int id, String nume, double v, String ech, String tip) {
        super(id, nume, v, ech);
        this.tip = tip;
    }

    public Jucator(){
        super();
        tip="Undefined";
    }

    public double esteApt() {
        return (35 - this.getVarsta());
    }

    public void setTip(String tip)
    {
        this.tip=tip;
    }

    @Override
    public String getTip()
    {return tip;}

    @Override
    public String getEchipaLaCareAFostAngajatInTrecut(){
        List<String> lista_echipe= Arrays.asList("CSM Ramnicu Valcea", "Gaz Metan Medias","FC Voluntari",
                "Astra Giurgiu","FCM Dunarea Galati"  );
        SecureRandom nr=new SecureRandom();
        //int x=nr.nextInt(5);
        int x=this.getId()%5; //5 echipe
        //echipa la care a fost angajat in trecut nu poate fi aceeasi cu cea in care joaca in prezent:
        if(lista_echipe.get(x).equals(this.getEchipa())&&x<4)
            return lista_echipe.get(x+1);
        if(lista_echipe.get(x).equals(this.getEchipa())&&x==4)
            return lista_echipe.get(x-1);
        return lista_echipe.get(x);
    }

}










