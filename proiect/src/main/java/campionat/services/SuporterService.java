package main.java.campionat.services;

import main.java.campionat.configuration.RepositoryConfig;
import main.java.campionat.domain.CampionatException;
import main.java.campionat.domain.entity.Jucator;
import main.java.campionat.domain.entity.Suporter;
import main.java.campionat.domain.repository.JucatorRepository;
import main.java.campionat.domain.repository.SuporterRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import static main.java.campionat.domain.ErrorCode.PRINT_ALL_JUCATORI_ERROR;

public class SuporterService
{
    private JucatorRepository jucatorRepository = RepositoryConfig.getInstance().getJucatorRepository();
    private SuporterRepository suporterRepository = RepositoryConfig.getInstance().getSuporterRepository();


// 2) Echipa pe care o sustine 1 suporter(echipa pe care o sustine e cea din care face parte jucatorul preferat al lui)

    public void detEchipaSustinuta(int suporterId)
    {

        PrintWriter pw = null;
        try (FileOutputStream fos = new FileOutputStream("rezultate.txt", true))
        {
            pw = new PrintWriter(fos);
            pw.printf("Ati ales optiunea 2) Echipa pe care o sustine suporterul cu ID-ul  %d \n", suporterId);

            Suporter suporter = suporterRepository.getSuporterById(suporterId);
            pw.printf("                     Suporterul %s cu ID-ul %d", suporter.getNume_persoana(), suporterId);

            Jucator jucator = jucatorRepository.getJucatorByName(suporter.getJucatorFavorit());

            pw.printf(" il are ca jucator favorit pe %s cu ID-ul %d, deci sustine echipa %s \n", jucator.getNume(), jucator.getId(), jucator.getEchipa());
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
