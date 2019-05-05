package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.Antrenor;

public interface AntrenorRepository {

    Antrenor getAntrenorById(int AntrenorId);
    Antrenor getAntrenorByName(String AntrenorName);
    boolean existsByName(String AntrenorName);
    public String getNivelPerformanta(Antrenor a);
    public void detPerformantaAntrenor(String numeAntrenor);

    public Antrenor[] getAntrenori();
}
