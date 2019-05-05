package main.java.campionat.domain.repository;

import main.java.campionat.domain.CampionatException;
import main.java.campionat.domain.entity.Antrenor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import static main.java.campionat.domain.ErrorCode.ANTRENOR_NOT_FOUND;
import static main.java.campionat.domain.ErrorCode.PRINT_ALL_JUCATORI_ERROR;

public class AntrenorRepositoryImpl implements AntrenorRepository {

    private Antrenor[] antrenori = new Antrenor[]{
            new Antrenor(1, "Antrenor1", 45, "CSA Steaua", 7.3),
            new Antrenor(2, "Antrenor2", 50, "Dinamo", 12),
            new Antrenor(3, "Antrenor3", 39, "Rapid", 3),
            new Antrenor(4, "Antrenor4", 39, "Viitorul", 2.4),
            new Antrenor(5, "Antrenor5", 39, "Astra", 6),
            new Antrenor(6, "Antrenor6", 39, "Otelul", 5.1)
    };

    @Override
    public Antrenor getAntrenorById(int AntrenorId) {
        Antrenor selectedAntrenor = null;
        for (int i = 0; i < antrenori.length; i++)
            if (antrenori[i].getId() == AntrenorId)
                selectedAntrenor = (Antrenor) antrenori[i];

        if (selectedAntrenor == null)
            throw new CampionatException(ANTRENOR_NOT_FOUND, " Nu s-a putut gasi jucatorul cu id-ul " + AntrenorId);

        return selectedAntrenor;
    }

    @Override
    public Antrenor getAntrenorByName(String AntrenorName) {
        int i;
        Antrenor selectedAntrenor=null;
        for (i = 0; i < antrenori.length; i++)
            if (antrenori[i].getNume().equals(AntrenorName))
                selectedAntrenor=antrenori[i];

        if(selectedAntrenor==null)
            throw new CampionatException(ANTRENOR_NOT_FOUND," Nu s-a putut gasi antrenorul cu numele-ul "+AntrenorName);
        return selectedAntrenor;
    }

    @Override
    public boolean existsByName(String AntrenorName) {
        for (int i = 0; i < antrenori.length; i++)
            if (antrenori[i].getNume().equals(AntrenorName))
                return true;
        return false; // nu exista antrenorul AntrenorNume

    }

    @Override
    //nivelul de performanta al unui antrenor
    public String getNivelPerformanta(Antrenor a) {
        String mesaj ="";
        if (a.getVechime() < 2)
            mesaj = "incepator, avand o vechime mai mica de 2 ani";
        if (a.getVechime() > 2 && a.getVechime() < 5.5)
            mesaj = "avansat, cu o vechime intre 2 si 5 ani jumatate";
        if (a.getVechime() > 5.5)
            mesaj = "experimentat, cu o vechime care depaseste 5 ani jumatate";
        return mesaj;
    }

    @Override

// 3) Performanta unui antrenor
    public void detPerformantaAntrenor(String numeAntrenor) {

            if(!existsByName(numeAntrenor)) //nu exista antrenorul cu numele numeAntrenor
            {System.out.println("Nu exista antrenorul cu numele "+numeAntrenor+ " !");
            return;}

        PrintWriter pw = null;
        try (FileOutputStream fos = new FileOutputStream("rezultate.txt", true)) {
            pw = new PrintWriter(fos);
            pw.printf("Ati ales optiunea 3) Performanta antrenorului %s \n", numeAntrenor);
            int i;
            //determin antrenorul care are numele numeAntrenor
            for (i = 0; i < antrenori.length; i++)
                if (antrenori[i].getNume().equals(numeAntrenor)) {
                    // ii afiseaza performanta
                    pw.printf("                      %s al echipei %s", numeAntrenor, antrenori[i].getEchipa());
                    pw.printf(" ca performanta este %s \n", getNivelPerformanta(antrenori[i]));

                }
            pw.close();
        } catch (IOException e) {
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
    public Antrenor[] getAntrenori(){
        return antrenori;
    }

}