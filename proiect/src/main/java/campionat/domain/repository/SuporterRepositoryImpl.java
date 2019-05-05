package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.Suporter;

public class SuporterRepositoryImpl implements SuporterRepository {
    private Suporter[] suporteri=new Suporter[]{
            new Suporter(1,"Suporter1","Gica Hagi"),
            new Suporter(2,"Suporter2","Marius Pop"),
            new Suporter(3,"Suporter3","Vlad Chiriches"),
            new Suporter(4,"Suporter4","Marius Lacatus"),
            new Suporter(5,"Suporter5","Ciprian Tatarusanu")
    };


@Override
    public Suporter getSuporterById(int SuporterId) {
        Suporter selectedSuporter = null;
        for(int i = 0; i< suporteri.length; i++){
            if(suporteri[i].getId() == SuporterId){
                selectedSuporter = (Suporter) suporteri[i];
            }
        }
        return selectedSuporter;
    }

    @Override
    public boolean existsByName(String SuporterName) {

        for(int i=0;i<suporteri.length;i++)
            if(suporteri[i].getNume_persoana().equals(SuporterName))
                return true;

        return false; // nu exista suporterul cu numele SuporterName
    }

    @Override
    public Suporter[] getSuporteri(){return suporteri;}


}
