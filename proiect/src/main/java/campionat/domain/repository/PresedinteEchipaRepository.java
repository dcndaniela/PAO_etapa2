package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.PresedinteEchipa;

public interface PresedinteEchipaRepository {

    PresedinteEchipa getPresedinteEchipaById(int PresedinteEchipaId);
    boolean existsByEchipa(String EchipaCondusa);

    public PresedinteEchipa[] getPresedinti();
}
