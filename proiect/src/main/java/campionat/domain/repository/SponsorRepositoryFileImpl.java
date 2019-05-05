package main.java.campionat.domain.repository;

import main.java.campionat.configuration.RepositoryConfig;
import main.java.campionat.domain.CampionatException;
import main.java.campionat.domain.entity.Sponsor;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import java.lang.Double;

import static main.java.campionat.domain.ErrorCode.PRINT_ALL_JUCATORI_ERROR;


public class SponsorRepositoryFileImpl implements SponsorRepository{

//    private MeciRepository meciRepository = RepositoryConfig.getInstance().getMeciRepository();
    private Sponsor[] sponsori=new Sponsor[100];

    public SponsorRepositoryFileImpl(String filename) throws FileNotFoundException {
        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(fileInputStream);
        int counter = 0;
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Sponsor newEntry = new Sponsor(new Integer(values[0]), values[1],new Double(values[2]),new Double(values[3]));
            sponsori[counter++] = newEntry;
        }
    }

    @Override
    public Sponsor getSponsorById(int SponsorId) {
        Sponsor selectedSponsor = null;
        for(int i = 0; i< sponsori.length; i++){
            if(sponsori[i] instanceof Sponsor &&
                    sponsori[i].getId() == SponsorId){
                selectedSponsor = (Sponsor) sponsori[i];
            }
        }
        return selectedSponsor;
    }

    @Override
    public boolean existsByName(String SponsorName) {

        for(int i=0;i<sponsori.length;i++)
            if(sponsori[i].getNume_persoana().equals(SponsorName))
                return true;

        return false; // nu exista sponsorul SponsorName
    }


    @Override
    public Sponsor[] getSponsori(){return sponsori;}
}
