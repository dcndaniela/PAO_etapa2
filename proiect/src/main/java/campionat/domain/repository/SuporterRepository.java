package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.Suporter;

public interface SuporterRepository {

    Suporter getSuporterById(int SuporterId);

    boolean existsByName(String SuporterName);

   public Suporter[] getSuporteri();
}
