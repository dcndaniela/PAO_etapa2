package main.java.campionat.services;

import main.java.campionat.configuration.RepositoryConfig;
import main.java.campionat.domain.CampionatException;
import main.java.campionat.domain.entity.Arbitru;
import main.java.campionat.domain.entity.Jucator;
import main.java.campionat.domain.entity.Meci;
import main.java.campionat.domain.entity.Sponsor;
import main.java.campionat.domain.repository.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

import static main.java.campionat.domain.ErrorCode.PRINT_ALL_JUCATORI_ERROR;

public class CampionatService {
    private MeciRepository meciRepository = RepositoryConfig.getInstance().getMeciRepository();
    private JucatorRepository jucatorRepository = RepositoryConfig.getInstance().getJucatorRepository();
    private ArbitruRepository arbitruRepository = RepositoryConfig.getInstance().getArbitruRepository();
    private SponsorRepository sponsorRepository = RepositoryConfig.getInstance().getSponsorRepository();


    // 5) echipa castigatoare
    public void detEchipaCastigatoare() {

        PrintWriter pw = null;
        try (FileOutputStream fos = new FileOutputStream("rezultate.txt", true)) {
            pw = new PrintWriter(fos);
            pw.printf("Ati ales optiunea 5) Echipa care a castigat campionatul : \n");

            Meci[] meciuriCampionat = meciRepository.getMeciuri();
            String ech = "Unknown";
            String castigatoare = "Unknown";
            int nrS = 0, nrD = 0, nrA = 0, nrV = 0, nrO = 0, nrR = 0, x = 0, maxi = 0, nr;

            for (int i = 0; i < meciuriCampionat.length; i++) {
                String sc = meciuriCampionat[i].getScor();
                String[] sir = sc.split("-");
                nr = 0;

                for (int w = 0; w < 2; w++) // scorul este de forma sir[0]-sir[1]
                {
                    x = new Integer(sir[w]);
                    nr++;
                    if (nr == 1)
                        ech = meciuriCampionat[i].getEchipa1();
                    else
                        ech = meciuriCampionat[i].getEchipa2();

                    switch (ech) {
                        case "CSA Steaua":
                            nrS += x;
                            if (nrS > maxi) {
                                maxi = nrS;
                                castigatoare = "CSA Steaua";
                            }
                            break;
                        case "Dinamo":
                            nrD += x;
                            if (nrD > maxi) {
                                maxi = nrD;
                                castigatoare = "Dinamo";
                            }
                            break;
                        case "Viitorul":
                            nrV += x;
                            if (nrV > maxi) {
                                maxi = nrV;
                                castigatoare = "Viitorul";
                            }
                            break;
                        case "Astra":
                            nrA += x;
                            if (nrA > maxi) {
                                maxi = nrA;
                                castigatoare = "Astra";
                            }
                            break;
                        case "Otelul":
                            nrO += x;
                            if (nrO > maxi) {
                                maxi = nrO;
                                castigatoare = "Otelul";
                            }
                            break;
                        case "Rapid":
                            nrR += x;
                            if (nrR > maxi) {
                                maxi = nrR;
                                ech = "Rapid";
                            }
                            break;
                        default:
                            castigatoare = "Unknown";
                            break;
                    }
                }
            }

            pw.printf("                     Echipa castigatoare este %s \n", castigatoare);
            pw.close();
        } catch (IOException e) {
            throw new CampionatException(PRINT_ALL_JUCATORI_ERROR, e.getMessage());
        }

        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        afis_detalii_CSV(nameofCurrMethod);

    }

    public double detSalariu(Arbitru arb) {
        return arb.getSalariu();
    }


    // 6) sponsorul pentru un meci
    public void detSponsorMeci(String meciul) {

        String[] echipe = meciul.split("-");
        if (!meciRepository.existsByName(echipe[0]) || !meciRepository.existsByName(echipe[1]))//nu exista echipa1 sau echipa2
        {
            System.out.println("Ati introdus gresit denumirile echipelor! ");
            return;
        }

        PrintWriter pw = null;

        try (FileOutputStream fos = new FileOutputStream("rezultate.txt", true)) {
            pw = new PrintWriter(fos);
            pw.printf("Ati ales optiunea 6) Sponsorul meciului %s \n", meciul);
            Sponsor[] sponsori = sponsorRepository.getSponsori();
            //generez nr aleator de la 5 la 10(inclusiv)
            // int x = (int) (Math.random() * ((10 - 5) + 1)) + 5;
            int x = (int) (Math.random() * (5) + 1);
            pw.printf("                     Sponsorul %s a facut o donatie de %.2f euro sponsorizare  \n", sponsori[x].getNume_persoana(), sponsori[x].getSponsorizare());
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


    // 7) Cel mai bine platit arbitru si meciul pe care l-a arbitrat
    public void detCelMaiBinePlatitArbitru() {

        PrintWriter pw = null;
        try (FileOutputStream fos = new FileOutputStream("rezultate.txt", true)) {
            pw = new PrintWriter(fos);
            pw.printf("Ati ales optiunea 7) Cel mai bine platit arbitru: \n");

            Arbitru a = new Arbitru();
            String ech1 = "";
            String ech2 = "";
            String scor = "";
            Meci[] meciuriCampionat = meciRepository.getMeciuri();

            int i;
            Arbitru[] arbitri = arbitruRepository.getArbitri();
            double maxi = -1;

            //determin arbitrul cel mai bine platit
            for (i = 0; i < arbitri.length; i++)
                if (arbitri[i] instanceof Arbitru && maxi < arbitri[i].getSalariu()) // daca nu pun conditia cu instanceof =>NullPointerException
                {
                    maxi = arbitri[i].getSalariu();
                    a = arbitri[i];// arbitrul cu cel mai mare salariu
                }
            int indice = 0;
            //determin meciul pe care l-a arbitrat
            for (i = 0; i < meciuriCampionat.length; i++)
                if (a.getNume_persoana().equals(meciuriCampionat[i].getArbitru())) {
                    indice = i;
                    break;
                }

            pw.printf("                     Arbitrul %s are cel mai mare salariu, anume %.3f lei si a arbitrat meciul %s - %s care s-a terminat cu scorul de %s \n ",
                    a.getNume_persoana(), a.getSalariu(), meciuriCampionat[indice].getEchipa1(),
                    meciuriCampionat[indice].getEchipa2(), meciuriCampionat[indice].getScor());

            pw.close();
        } catch (IOException e) {
            throw new CampionatException(PRINT_ALL_JUCATORI_ERROR, e.getMessage());
        }

//scriu in fisierul CSV daca am apelat aceasta metoda
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        afis_detalii_CSV(nameofCurrMethod); //iau numele metodei si il pasez functiei de scriere in fisierul CSV

    }

// 8) Jucatorii dintr-un meci

    public void detJucatoriiDinMeci(String meciul) {
        String[] echipe = meciul.split("-");
        if (!meciRepository.existsByName(echipe[0]) || !meciRepository.existsByName(echipe[1]))//nu exista echipa1 sau echipa2
        {
            System.out.println("Ati introdus gresit denumirile echipelor! ");
            return;
        }

        PrintWriter pw = null;
        try (FileOutputStream fos = new FileOutputStream("rezultate.txt", true)) {
            pw = new PrintWriter(fos);
            pw.printf("Ati ales optiunea 8) Jucatorii dintr-un meci \n");
            Jucator[] jucatori = jucatorRepository.getJucatori();

            String sir[] = meciul.split("-");
            for (String a : sir) {
                pw.printf("                     Jucatorii din echipa %s sunt: \n ", a);

                for (int i = 0; i < jucatori.length; i++)
                    if (jucatori[i].getEchipa().equals(a))
                        pw.printf("                                                %s \n", jucatori[i].getNume());

            }

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
        try (FileOutputStream fos = new FileOutputStream("serviciiCSV.txt", true)) {
            pw = new PrintWriter(fos);
            String className = this.getClass().getSimpleName();
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            pw.printf("%s ,%s, %s \n", ts, className, nameofCurrMethod);
            pw.close();
        } catch (IOException e) {
            throw new CampionatException(PRINT_ALL_JUCATORI_ERROR, e.getMessage());
        }

    }

    /*
    private void det_punctaj(Integer nrS, Integer nrD, Integer nrR, Integer nrO, Integer nrV, Integer nrA)
    {
            Meci[] meciuriCampionat = meciRepository.getMeciuri();


            for (int i = 0; i < meciuriCampionat.length; i++) {
                String sc = meciuriCampionat[i].getScor();
                String[] sir = sc.split("-");
                int nr = 0;
                int x;
                String ech;

                for (int w = 0; w < 2; w++) // scorul este de forma sir[0]-sir[1]
                {
                    x = new Integer(sir[w]);
                    nr++;
                    if (nr == 1)
                        ech = meciuriCampionat[i].getEchipa1();
                    else
                        ech = meciuriCampionat[i].getEchipa2();

                    switch (ech) {
                        case "CSA Steaua":
                            nrS += x;
                            break;
                        case "Dinamo":
                            nrD += x;
                            break;
                        case "Viitorul":
                            nrV += x;
                            break;
                        case "Astra":
                            nrA += x;
                            break;
                        case "Otelul":
                            nrO += x;
                            break;
                        case "Rapid":
                            nrR += x;
                            break;
                    }
                }
            }



    }

        public void det_clasament_echipe () {


            Integer nrS = new Integer(0);
            Integer nrD = new Integer(0);
            Integer nrR = new Integer(0);
            Integer nrO = new Integer(0);
            Integer nrV = new Integer(0);
            Integer nrA = new Integer(0);

            det_punctaj(nrS, nrD, nrR, nrO, nrV, nrA);

            //System.out.println(nrS + " " + nrD + " " + nrR + " " + nrA + " " + nrO + " " + nrV);

            Meci[] meciuri = meciRepository.getMeciuri();


            SortedMap<Integer, String> sm = new TreeMap<Integer, String>();
            sm.put(nrS, "CSA Steaua");
            sm.put(nrD, "Dinamo");
            sm.put(nrR, "Rapid");
            sm.put(nrO, "Otelul");
            sm.put(nrV, "Viitorul");
            sm.put(nrA, "Astra");

            Set s = sm.entrySet();

            Iterator i = s.iterator();
            while (i.hasNext()) {
                Map.Entry m = (Map.Entry) i.next();
                int key = (Integer) m.getKey();
                String value = (String) m.getValue();
                System.out.println(" Echipa  " + value + " - " + key + " puncte");

            }

        }
        */

}
