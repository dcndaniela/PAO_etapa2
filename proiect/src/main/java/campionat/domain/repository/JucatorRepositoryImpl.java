package main.java.campionat.domain.repository;

import main.java.campionat.domain.CampionatException;
import main.java.campionat.domain.entity.Jucator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import static main.java.campionat.domain.ErrorCode.JUCATOR_NOT_FOUND;
import static main.java.campionat.domain.ErrorCode.PRINT_ALL_JUCATORI_ERROR;

public class JucatorRepositoryImpl implements JucatorRepository {
    private Jucator[] jucatori=new Jucator[]{
            new Jucator(1,"Gica Hagi",34.4  ,"CSA Steaua","atacant"),
            new Jucator(2,"Marius Pop",30,"Otelul","atacant"),
            new Jucator(3,"Ciprian Tatarusanu",23,"CSA Steaua","portar"),
            new Jucator(4,"Vlad Chiriches",32,"Dinamo","fundas"),
            new Jucator(5,"Ion Pop",27,"Rapid","fundas"),
            new Jucator(6,"Marius Radu",25,"Rapid","atacant"),
            new Jucator(7,"Alex Chirita",26,"Otelul","atacant"),
            new Jucator(8,"Ion Marian",21,"Astra","fundas"),
            new Jucator(9,"Paul Nastase",22,"CSA Steaua","atacant"),
            new Jucator(10,"Cosmin Alexei",23,"Dinamo","portar"),
            new Jucator(11,"Dorin Doru",24,"Viitorul","portar"),
            new Jucator(12,"Marian Cretu",25,"Rapid","portar"),
            new Jucator(13,"Ilie Timofte",26,"CSA Steaua","fundas"),
            new Jucator(14,"George Radu",27,"Rapid","fundas"),
            new Jucator(15,"Stefan Stan",22,"Astra","portar"),
            new Jucator(16,"Mihai Radulescu",26,"Otelul","portar")
    };


    @Override
    public Jucator getJucatorById(int jucatorid) {
        Jucator selectedJucator = null;
        for(int i = 0; i< jucatori.length; i++){
            if(jucatori[i].getId() == jucatorid){
                selectedJucator = jucatori[i];
            }
        }
        if (selectedJucator==null)
            throw new CampionatException(JUCATOR_NOT_FOUND," Nu s-a putut gasi jucatorul cu id-ul "+jucatorid);
        return selectedJucator;
    }




    @Override
    public Jucator getJucatorByName(String JucatorName) {
        int i;
        Jucator selectedJucator=null;
        for (i = 0; i < jucatori.length; i++)
            if (jucatori[i].getNume().equals(JucatorName))
                selectedJucator=jucatori[i];
        if(selectedJucator==null)
            throw new CampionatException(JUCATOR_NOT_FOUND," Nu s-a putut gasi jucatorul cu numele-ul "+JucatorName);
        return selectedJucator;
    }

    @Override
    public boolean existsByName(String JucatorName) {
        for(int i=0;i<jucatori.length;i++)
            if(jucatori[i].getNume().equals(JucatorName))
                return true;
        return false; // snu exista jucatorul cu numele JucatorNume
    }

    @Override
   public boolean existsByEchipaName(String EchipaName){
        int i;
        for (i = 0; i < jucatori.length; i++)
            if (jucatori[i].getEchipa().equals(EchipaName))
                return true;
        return false; //nu exista ehipa EchipaName
    }



    // 9) Atacantii din echipa ech
    public void detAtacantiiDinEchipa(String ech) {
        if(!existsByEchipaName(ech))
        {System.out.println(ech+" este un nume invalid de echipa! Verificati lista echipelor din campionat");
        return;}

        PrintWriter pw = null;
        try (FileOutputStream fos = new FileOutputStream("rezultate.txt", true)) {
            pw = new PrintWriter(fos);
            pw.printf("Ati ales optiunea 9) Atacantii din echipa %s :\n", ech);


            for (int i = 0; i < jucatori.length; i++)
                if (jucatori[i].getEchipa().equals(ech) && jucatori[i].getTip().equals("atacant"))
                    pw.printf("                     %s - %.2f ani \n", jucatori[i].getNume(), jucatori[i].getVarsta());
            // daca nu inchid fisierul => NU pot scrie in el!!!!!!!!!!!!!!
            pw.close();
        }
        catch (IOException e)
        {
            throw new CampionatException(PRINT_ALL_JUCATORI_ERROR, e.getMessage());
        }

        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        afis_detalii_CSV(nameofCurrMethod);
    }


    private void afis_detalii_CSV(String nameofCurrMethod) {

        PrintWriter pw = null;
        try (FileOutputStream fos = new FileOutputStream("serviciiCSV.txt", true))
        {
            pw = new PrintWriter(fos);
            String className = this.getClass().getSimpleName();
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            //pw.println(ts);
            pw.printf("%s ,%s, %s \n", ts, className, nameofCurrMethod);
            pw.close();
        }
        catch (IOException e)
        {
            throw new CampionatException(PRINT_ALL_JUCATORI_ERROR, e.getMessage());
        }

    }

    @Override
    public Jucator[] getJucatori(){return jucatori;}

}
