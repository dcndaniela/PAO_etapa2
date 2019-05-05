package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.Arbitru;
import main.java.campionat.domain.entity.Meci;


public interface MeciRepository {

    //public int getNrMeciuri();
    public boolean existsByName(String EchipaName);

    public Meci getMeciByName(String MeciName);

    public Meci[] getMeciuri();

}
