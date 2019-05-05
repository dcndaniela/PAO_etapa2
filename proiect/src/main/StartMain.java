package main;

import main.java.campionat.configuration.RepositoryConfig;
import main.java.campionat.domain.CampionatException;
import main.java.campionat.domain.repository.AntrenorRepositoryImpl;
import main.java.campionat.domain.repository.JucatorRepositoryImpl;
import main.java.campionat.domain.repository.MeciRepositoryImpl;
import main.java.campionat.services.CampionatService;
import main.java.campionat.services.JucatorService;
import main.java.campionat.services.PresedinteService;
import main.java.campionat.services.SuporterService;
import main.java.campionat.tool.Utilizare_Colectii;
import java.util.Scanner;

import static main.java.campionat.domain.ErrorCode.*;

public class StartMain {

    public static void main(String[] args) {

        System.out.println("Bine ati venit la CAMPIONATUL DE FOTBAL !");
        System.out.println();

        CampionatService campionatService=new CampionatService();
        JucatorService jucatorService=new JucatorService();
        PresedinteService presedinteService=new PresedinteService();
        SuporterService suporterService=new SuporterService();
        AntrenorRepositoryImpl antrenorRepository=new AntrenorRepositoryImpl();
        Utilizare_Colectii utilizare_colectii=new Utilizare_Colectii();
        JucatorRepositoryImpl jucatorRepository=new JucatorRepositoryImpl();
        MeciRepositoryImpl meciRepository=new MeciRepositoryImpl();


        /*try {
            SponsorRepositoryFileImpl sponsorRepositoryFile = new SponsorRepositoryFileImpl();
        }
        catch(FileNotFoundException e)
        {
            throw new CampionatException(EROARE_INTRARE,e.getMessage() );
        }
        */


        System.out.println("Echipele calificate in Campionatul National 2019 sunt: CSA Steaua, Rapid, Dinamo, Viitorul, Astra, Otelul");
        System.out.println();

        System.out.println("Cate cautari doriti sa efectuati? ");
        Scanner in = new Scanner(System.in);
        Scanner inn = new Scanner(System.in);
        int nr_cautari = inn.nextInt();
        int i;


        for(i=0;i<nr_cautari;i++)
        {
            System.out.println();
            System.out.println("Optiunea 1: Numarul de jucatori dintr-o anumita echipa");
            System.out.println("Optiunea 2: Echipa sustinuta de un anumit suporter");
            System.out.println("Optiunea 3: Performanta unui anumit antrenor");
            System.out.println("Optiunea 4: Cat timp mai poate activa un jucator");
            System.out.println("Optiunea 5: Echipa castigatoare ");
            System.out.println("Optiunea 6: Sponsorul unui anumit meci");
            System.out.println("Optiunea 7: Cel mai bine platit arbitru");
            System.out.println("Optiunea 8: Jucatorii dintr-un anumit meci");
            System.out.println("Optiunea 9: Atacantii dintr-o anumita echipa");
            System.out.println("Optiunea 10: Presedintele de echipa care detine cele mai multe/putine actiuni");
            System.out.println("Optiunea 11: Detalii despre trecutul jucatorilor si antrenorilor");
            System.out.println("Optiunea 12: Clasamentul general final");

            System.out.println();

            System.out.println("Tastati optiunea: ");
            int op=inn.nextInt();

            //  1 -> 10 : afisare in "rezultate.txt"
            // 11-> 12 : afisare in consola (pe ecran)

            switch(op){
                case 1:
                    try {
                        System.out.println("Tastati echipa: ");
                        String e = in.nextLine();

                        if(!e.equals("CSA Steaua") && !e.equals("Dinamo")&& !e.equals("Astra")
                                && !e.equals("Viitorul")&& !e.equals("Otelul")&& !e.equals("Rapid"))
                            throw new CampionatException(INEXISTENT,"Echipa inexistenta");
                        jucatorService.detNrJucatorDinEchipa(e);
                        break;
                    }
                    catch (CampionatException e) {
                                System.out.println(e.getMessage() );
                                break;
                    }
                case 2:
                    try {
                        Scanner sn=new Scanner(System.in);
                        System.out.println("Tastati id suporter (numar natural de la 1 la 5) : ");
                        int sup_id = Integer.parseInt(sn.next());
                        if(sup_id<1||sup_id>5)
                             throw new CampionatException(INCORRECT_USER_ID,"Ati introdus un ID invalid!");
                        suporterService.detEchipaSustinuta(sup_id);
                        break;
                    }
                    catch (CampionatException e) {
                                System.out.println(e.getMessage());
                                break;
                        }

                    catch (NumberFormatException e){
                     System.out.println("Ati introdus un ID invalid!");
                     break;
                    }
                case 3:
                    try {
                        System.out.println("Tastati nume antrenor: ");
                        String el = in.nextLine();
                        if(antrenorRepository.getAntrenorByName(el)==null)
                            //afis mesajul din AntrenorRepositoryImpl-> getAntrenorByName
                            throw new CampionatException(ANTRENOR_NOT_FOUND,"" );
                        antrenorRepository.detPerformantaAntrenor(el);
                        break;
                    }
                    catch(CampionatException el){
                        System.out.println(el.getMessage());
                        break;
                    }
                case 4:
                    try{
                    System.out.println("Tastati numele unuia dintre jucatori: ");
                    String er=in.nextLine();
                    if( jucatorRepository.getJucatorByName(er)==null )
                        //afis mesajul din JucatorRepositoryImpl-> getJucatorByName
                        throw new CampionatException(JUCATOR_NOT_FOUND,"" );
                    jucatorService.detPerioadaJucator(er);
                    break;
                    }
                    catch(CampionatException er)
                    {System.out.println(er.getCode()+" "+er.getMessage()); }

                case 5:
                    campionatService.detEchipaCastigatoare();
                    break;
                case 6:
                    try {
                        System.out.println("Tastati meciul:  ");
                        String e = in.nextLine();
                        if (meciRepository.getMeciByName(e) == null)
                            throw new CampionatException(MECI_NOT_FOUND, "");

                        campionatService.detSponsorMeci(e);
                        break;
                    }
                    catch(CampionatException e)
                    {System.out.println(e.getCode()+" "+e.getMessage());
                    break;
                    }
                case 7:
                    campionatService.detCelMaiBinePlatitArbitru();
                    break;
                case 8:
                    try {
                        System.out.println("Tastati meciul:  ");
                        String e1 = in.nextLine();
                        if (meciRepository.getMeciByName(e1) == null)
                            throw new CampionatException(MECI_NOT_FOUND, "");
                        campionatService.detJucatoriiDinMeci(e1);
                        break;
                    }
                    catch(CampionatException e1)
                    {System.out.println(e1.getCode()+" "+e1.getMessage());
                        break;
                    }
                case 9:
                    try{
                    System.out.println("Tastati echipa:  ");
                    String  en=in.nextLine();
                        if(!en.equals("CSA Steaua") && !en.equals("Dinamo")&& !en.equals("Astra")
                                && !en.equals("Viitorul")&& !en.equals("Otelul")&& !en.equals("Rapid"))
                            throw new CampionatException(INEXISTENT,"Echipa inexistenta");

                    jucatorRepository.detAtacantiiDinEchipa(en);
                    break;
                    }
                    catch (CampionatException en)
                    {
                        System.out.println(en.getMessage());
                        break;
                    }
                case 10:
                    presedinteService.detPresedintiEchipe();
                    break;
                // actiune implementata cu ajutorul colectiilor:
                case 11:
                    utilizare_colectii.istoric_angajati();
                    break;
                case 12:
                    // actiune implementata cu ajutorul colectiilor:
                    utilizare_colectii.det_clasament_echipe();
                    break;
                default:
                    System.out.println("Ati introdus un numar invalid");
                    break;
            }
        }




    }



}
