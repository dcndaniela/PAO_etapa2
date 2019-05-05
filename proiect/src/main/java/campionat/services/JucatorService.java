package main.java.campionat.services;

import main.java.campionat.configuration.RepositoryConfig;
import main.java.campionat.domain.CampionatException;
import main.java.campionat.domain.entity.Jucator;
import main.java.campionat.domain.repository.JucatorRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import static java.lang.Math.abs; //pentru functia abs() -modul
import static main.java.campionat.domain.ErrorCode.PRINT_ALL_JUCATORI_ERROR;

public class JucatorService
{

    private JucatorRepository jucatorRepository = RepositoryConfig.getInstance().getJucatorRepository();

    // 1)Determina numarul de jucatori din echipa ech
    public void detNrJucatorDinEchipa(String ech)
    {
        if(!jucatorRepository.existsByEchipaName(ech))
        {System.out.println(ech+" este un nume invalid de echipa! Verificati lista echipelor din campionat");
            return;}

        PrintWriter pw = null;
        try (FileOutputStream fos = new FileOutputStream("rezultate.txt", true))
        {
            pw = new PrintWriter(fos);
            pw.printf("Ati ales optiunea 1) Numarul de jucatori din echipa %s :\n", ech);
            Jucator[] jucatori = jucatorRepository.getJucatori();
            int nr = 0;
            for (int i = 0; i < jucatori.length; i++)
                if (jucatori[i].getEchipa().equals(ech))
                    nr++;

            pw.printf("                     Echipa are %d jucatori \n", nr);
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

    // 4) Cat timp mai poate sa activeze 1 jucator
    public void detPerioadaJucator(String numeJucator)
    {
        if(!jucatorRepository.existsByName(numeJucator)) //nu exista jucatorul cu numele numeJucator
        {System.out.println("Nu exista jucatorul cu numele "+numeJucator+ " !");
            return;}

        PrintWriter pw = null;
        try (FileOutputStream fos = new FileOutputStream("rezultate.txt", true))
        {
            pw = new PrintWriter(fos);
            pw.printf("Ati ales optiunea 4) Cat timp mai poate sa activeze jucatorul %s : \n", numeJucator);

            //iau jucatorul cu numele numeJucator
            Jucator jucator = jucatorRepository.getJucatorByName(numeJucator);

            pw.printf("                     Jucatorul %s %s", jucator.getTip(), jucator.getNume());
            double nr = jucator.esteApt();

            if (nr <= 0)
                pw.printf("si-a incheiat activitatea acum %.2f ani \n", abs(jucator.esteApt()));
            else if (nr < 1)
                pw.printf(" mai poate activa timp de %.2f luni \n", nr * 10);
            else
                pw.printf(" mai poate activa timp de %.2f ani \n", nr);
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

            pw.printf("%s ,%s, %s \n", ts, className, nameofCurrMethod);
            pw.close();
        }
        catch (IOException e)
        {
            throw new CampionatException(PRINT_ALL_JUCATORI_ERROR, e.getMessage());
        }

    }

}