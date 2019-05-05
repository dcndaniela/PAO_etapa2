package main.java.campionat.domain.entity;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class Antrenor extends Angajat {

    private double vechime_antrenor; //de cat timp lucreaza ca antrenor

    public Antrenor(int id,String nume, double v,String ech,double vechime_antrenor)
    {
        super(id,nume,v,ech);
        this.setVechime(vechime_antrenor);
    }

    public Antrenor(){
        super();
        vechime_antrenor=-1;
    }

    public double getVechime() {
        return vechime_antrenor;
    }

    public void setVechime(double vechime_antrenor) {
        this.vechime_antrenor = vechime_antrenor;
    }

    public double esteApt(){return (60-this.getVarsta());
    }


    @Override
    public String getTip()
    {return "antrenor";}

    @Override
    public String getEchipaLaCareAFostAngajatInTrecut(){
        List<String> lista_echipe= Arrays.asList("ASC Otelul Galati","FC Vaslui","CS Universitatea Craiova", "CFR Cluj");
        SecureRandom nr=new SecureRandom();
        //int x=nr.nextInt(9);
        int x=this.getId()%4; // 4 echipe
        //echipa la care a fost angajat in trecut nu poate fi aceeasi cu cea in care joaca in prezent:
        if(lista_echipe.get(x).equals(this.getEchipa())&&x<3)
            return lista_echipe.get(x+1);
        if(lista_echipe.get(x).equals(this.getEchipa())&&x==3)
            return lista_echipe.get(x-1);
        return lista_echipe.get(x);
    }

}
