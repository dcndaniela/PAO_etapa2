package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.Arbitru;

public interface ArbitruRepository {

    public Arbitru getArbitruByName(String ArbitruName);
    Arbitru getArbitruById(int ArbitruId);
    boolean existsByName(String ArbitruName);

    public Arbitru[] getArbitri();
}
