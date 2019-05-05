package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.Jucator;

public interface JucatorRepository {
    Jucator getJucatorById(int JucatorId);

    boolean existsByName(String JucatorName);
    public Jucator getJucatorByName(String JucatorName);
    boolean existsByEchipaName(String EchipaName);

    public Jucator[] getJucatori();
}
