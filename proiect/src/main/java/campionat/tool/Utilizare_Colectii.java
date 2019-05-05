package main.java.campionat.tool;

import main.java.campionat.configuration.RepositoryConfig;
import main.java.campionat.domain.entity.Antrenor;
import main.java.campionat.domain.entity.Jucator;
import main.java.campionat.domain.entity.Meci;
import main.java.campionat.domain.repository.AntrenorRepository;
import main.java.campionat.domain.repository.JucatorRepository;
import main.java.campionat.domain.repository.MeciRepository;
import java.util.*;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Utilizare_Colectii {

    private MeciRepository meciRepository = RepositoryConfig.getInstance().getMeciRepository();
    private JucatorRepository jucatorRepository = RepositoryConfig.getInstance().getJucatorRepository();
    private AntrenorRepository antrenorRepository = RepositoryConfig.getInstance().getAntrenorRepository();



// 11) Istoricul angajatilor
// Angajatii( Jucator si Antrenor) sunt grupati in functie de echipa la care au fost in trecut

    public void istoric_angajati() {

        Jucator[] jucatori = jucatorRepository.getJucatori();
        Antrenor[] antrenori = antrenorRepository.getAntrenori();
        int i;

        // hmap_juc.
        HashMap<String, ArrayList<String>> hmap_juc = new HashMap<String, ArrayList<String>>();

        List<String> lista_echipe_juc = Arrays.asList("CSM Ramnicu Valcea", "Gaz Metan Medias", "FC Voluntari",
                "Astra Giurgiu", "FCM Dunarea Galati");

        int dim=0;
        dim=lista_echipe_juc.size();
        ArrayList<String>[] arr_juc = new ArrayList[dim];
        //ArrayList []arr2=new ArrayList[5];

        for (i = 0; i < lista_echipe_juc.size(); i++)
            arr_juc[i] = new ArrayList<String>();


        for (i = 0; i < jucatori.length; i++) {
            if (jucatori[i].getEchipaLaCareAFostAngajatInTrecut().equals(lista_echipe_juc.get(0)))
                arr_juc[0].add(jucatori[i].getNume());
            if (jucatori[i].getEchipaLaCareAFostAngajatInTrecut().equals(lista_echipe_juc.get(1)))
                arr_juc[1].add(jucatori[i].getNume());
            if (jucatori[i].getEchipaLaCareAFostAngajatInTrecut().equals(lista_echipe_juc.get(2)))
                arr_juc[2].add(jucatori[i].getNume());
            if (jucatori[i].getEchipaLaCareAFostAngajatInTrecut().equals(lista_echipe_juc.get(3)))
                arr_juc[3].add(jucatori[i].getNume());
            if (jucatori[i].getEchipaLaCareAFostAngajatInTrecut().equals(lista_echipe_juc.get(4)))
                arr_juc[4].add(jucatori[i].getNume());
        }
        // hmap_juc.
        for(i=0;i<lista_echipe_juc.size();i++)
            hmap_juc.put(lista_echipe_juc.get(i), arr_juc[i]);

        for (i = 0; i < lista_echipe_juc.size(); i++) // echipele in care ar fi putut fi angajati (sunt in class Jucator)
            System.out.println(" Jucatorii care in trecut au fost angajatii echipei " + lista_echipe_juc.get(i) +
                    " sunt: " + hmap_juc.get(lista_echipe_juc.get(i)));


//istoricul antrenorilor

        List<String> lista_echipe_antr = Arrays.asList("ASC Otelul Galati", "FC Vaslui", "CS Universitatea Craiova", "CFR Cluj");

        HashMap<String, ArrayList<String>> hmap_antr = new HashMap<String, ArrayList<String>>();

        dim=lista_echipe_antr.size();
        ArrayList<String>[] arr_antr = new ArrayList[dim];//sunt 4 echipe
        //ArrayList []arr2=new ArrayList[5];

        for (i = 0; i < lista_echipe_antr.size(); i++)
            arr_antr[i] = new ArrayList<String>();

        for (i = 0; i < antrenori.length; i++)
        {
            if (antrenori[i].getEchipaLaCareAFostAngajatInTrecut().equals(lista_echipe_antr.get(0)))
                arr_antr[0].add(antrenori[i].getNume());
            if (antrenori[i].getEchipaLaCareAFostAngajatInTrecut().equals(lista_echipe_antr.get(1)))
                arr_antr[1].add(antrenori[i].getNume());
            if (antrenori[i].getEchipaLaCareAFostAngajatInTrecut().equals(lista_echipe_antr.get(2)))
                arr_antr[2].add(antrenori[i].getNume());
            if (antrenori[i].getEchipaLaCareAFostAngajatInTrecut().equals(lista_echipe_antr.get(3)))
                arr_antr[3].add(antrenori[i].getNume());
        }
        for(i=0;i<lista_echipe_antr.size();i++)
            hmap_antr.put(lista_echipe_antr.get(i), arr_antr[i]);

        for (i = 0; i < lista_echipe_antr.size(); i++) //sunt 4 echipe in care ar fi putut fi angajati
            System.out.println(" Antrenorii care in trecut au fost angajatii echipei " + lista_echipe_antr.get(i) +
                    " sunt: " + hmap_antr.get(lista_echipe_antr.get(i)));


    }



// 12) Afisarea clasamentului general

    private void det_punctaj(Foo nrS, Foo nrD, Foo nrR, Foo nrO, Foo nrV, Foo nrA) {
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
                        nrS.adauga(x);
                        break;
                    case "Dinamo":
                        nrD.adauga(x);
                        break;
                    case "Viitorul":
                        nrV.adauga(x);
                        break;
                    case "Astra":
                        nrA.adauga(x);
                        break;
                    case "Otelul":
                        nrO.adauga(x);
                        break;
                    case "Rapid":
                        nrR.adauga(x);
                        break;
                }
            }
        }



    }

    public void det_clasament_echipe() {

        Foo nrS = new Foo(0);
        Foo nrD = new Foo(0);
        Foo nrR = new Foo(0);
        Foo nrO = new Foo(0);
        Foo nrV = new Foo(0);
        Foo nrA = new Foo(0);

        det_punctaj(nrS, nrD, nrR, nrO, nrV, nrA);

/*
//le sorteaza crescator

        SortedMap<Integer, String> sm = new TreeMap<Integer, String>();
        sm.put(nrS.getNum(), "CSA Steaua");
        sm.put(nrD.getNum(), "Dinamo");
        sm.put(nrR.getNum(), "Rapid");
        sm.put(nrO.getNum(), "Otelul");
        sm.put(nrV.getNum(), "Viitorul");
        sm.put(nrA.getNum(), "Astra");

        Set s = sm.entrySet();

        Iterator i = s.iterator();
        while (i.hasNext()) {
            Map.Entry m = (Map.Entry) i.next();
            int key = (Integer) m.getKey();
            String value = (String) m.getValue();
            System.out.println(" Echipa  " + value + " - " + key + " puncte");

        }
*/
//le sorteaza descrescator

        Map<Integer, String> sm = new HashMap<>();
        sm.put(nrS.getNum(), "CSA Steaua");
        sm.put(nrD.getNum(), "Dinamo");
        sm.put(nrR.getNum(), "Rapid");
        sm.put(nrO.getNum(), "Otelul");
        sm.put(nrV.getNum(), "Viitorul");
        sm.put(nrA.getNum(), "Astra");

        //sortez descrescator dupa cheie( punctajul obtinut de fiecare echipa)
        TreeMap<Integer, String> sortedm = new TreeMap<>(sm); //elimina duplicatele
        System.out.println();
        System.out.println("Clasamentul general este: ");
        ((TreeMap<Integer, String>) sortedm).descendingMap().forEach((key, value) -> {
            System.out.println("Echipa " + value + " " + key + " puncte");
        });

        System.out.println();
        System.out.println("FELICITARI TUTUROR ECHIPELOR PARTICIPANTE ! ");

    }

}
