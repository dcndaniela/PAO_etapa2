package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.Sponsor;
import main.java.campionat.domain.entity.Suporter;

public interface SponsorRepository {
    Sponsor getSponsorById(int SponsorId);

    boolean existsByName(String SponsorName);

    public Sponsor[] getSponsori();
}
