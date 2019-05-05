package main.java.campionat.services;

import main.java.campionat.configuration.RepositoryConfig;
import main.java.campionat.domain.CampionatException;
import main.java.campionat.domain.entity.PresedinteEchipa;
import main.java.campionat.domain.repository.PresedinteEchipaRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import static main.java.campionat.domain.ErrorCode.PRINT_ALL_JUCATORI_ERROR;

public class PresedinteService
{
    private PresedinteEchipaRepository presedinteEchipaRepository = RepositoryConfig.getInstance().getPresedinteEchipaRepository();

// 10) Afiseaza presedintele cu cele mai multe/cele mai putine actiuni
    public void detPresedintiEchipe()
    {
        PrintWriter pw = null;
        try (FileOutputStream fos = new FileOutputStream("rezultate.txt", true))
        {
            pw = new PrintWriter(fos);
            pw.printf("Ati ales optiunea 10) Presedintele cu cele mai multe,respectiv cele mai putine actiuni: \n");
            PresedinteEchipa[] presedinti = presedinteEchipaRepository.getPresedinti();
            int maxi = 0, mini = 9999;

            PresedinteEchipa p_maxi=presedinti[0];
            PresedinteEchipa p_mini=presedinti[0];
            for (int i = 1; i < presedinti.length; i++)
            {
               //PresedinteEchipa pre = presedinti[i];
                if (presedinti[i] instanceof PresedinteEchipa && presedinti[i].getNumar_actiuni() > maxi)
                {
                    maxi = presedinti[i].getNumar_actiuni();
                    p_maxi = presedinti[i];
                }
                if (presedinti[i] instanceof PresedinteEchipa&&presedinti[i].getNumar_actiuni() < mini)
                {
                    mini = presedinti[i].getNumar_actiuni();
                    p_mini = presedinti[i];
                }
            }

            pw.printf("                     Presedintele care detine cele mai multe actiuni este ");
            pw.printf("%s - %d actiuni \n", p_maxi.getNume_persoana(), p_maxi.getNumar_actiuni());
            pw.printf("                     Presedintele care detine cele mai putine actiuni este ");
            pw.printf("%s - %d actiuni \n", p_mini.getNume_persoana(), p_mini.getNumar_actiuni());
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
