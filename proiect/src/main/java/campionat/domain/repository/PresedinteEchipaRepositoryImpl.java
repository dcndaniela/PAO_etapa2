package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.PresedinteEchipa;

public class PresedinteEchipaRepositoryImpl implements PresedinteEchipaRepository {
    private PresedinteEchipa[] presedinti=new PresedinteEchipa[]{
            new PresedinteEchipa(1,"PresedinteSteaua","CSA Steaua",128),
            new PresedinteEchipa(2,"PresedinteDinamo","Dinamo",284),
            new PresedinteEchipa(3,"PresedinteAstra","Astra",321),
            new PresedinteEchipa(4,"PresedinteViitorul","Viitorul",123),
            new PresedinteEchipa(5,"PresedinteOtelul","Otelul",222),
            new PresedinteEchipa(6,"PresedinteRapid","Rapid",311)
    };


    @Override
    public PresedinteEchipa getPresedinteEchipaById(int PresedinteEchId){
        PresedinteEchipa selectedPresedinteEchipa = null;
        for(int i = 0; i< presedinti.length; i++){
            if(presedinti[i] instanceof PresedinteEchipa &&
                    presedinti[i].getId() == PresedinteEchId){
                selectedPresedinteEchipa = (PresedinteEchipa) presedinti[i];
            }
        }
        return selectedPresedinteEchipa;

    }

    @Override
    public boolean existsByEchipa(String EchipaCondusa){

        //prin conventie, 1 echipa trebuie sa aiba 1 singur presedinte
        for(int i=0;i<presedinti.length;i++)
            if(presedinti[i].getEchipa_condusa().equals(EchipaCondusa))
                return true;
        return false; // EchipaCondusa nu are presedinte
    }

    @Override
    public PresedinteEchipa[] getPresedinti(){return presedinti;}
}
