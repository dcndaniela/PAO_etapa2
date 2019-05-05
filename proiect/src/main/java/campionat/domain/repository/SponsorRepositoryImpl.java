package main.java.campionat.domain.repository;

import main.java.campionat.domain.CampionatException;
import main.java.campionat.domain.entity.Sponsor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import static main.java.campionat.domain.ErrorCode.PRINT_ALL_JUCATORI_ERROR;

public class SponsorRepositoryImpl implements SponsorRepository {
    private Sponsor[] sponsori = new Sponsor[]{
            new Sponsor(1, "Sponsor1", 2744.88, 6.5),
            new Sponsor(2, "Sponsor2", 54000, 4),
            new Sponsor(3, "Sponsor3", 39000.99, 0.8),
            new Sponsor(4, "Sponsor4", 4400.44, 12),
            new Sponsor(5, "Sponsor5", 100000, 2),
            new Sponsor(6, "Sponsor6", 53000.2, 0.3)
    };


    @Override
    public Sponsor getSponsorById(int SponsorId) {
        Sponsor selectedSponsor = null;
        for (int i = 0; i < sponsori.length; i++) {
            if (sponsori[i] instanceof Sponsor &&
                    sponsori[i].getId() == SponsorId) {
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
        public Sponsor[] getSponsori () {
            return sponsori;
        }


}