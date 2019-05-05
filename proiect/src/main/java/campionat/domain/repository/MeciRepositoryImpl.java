package main.java.campionat.domain.repository;

import main.java.campionat.configuration.RepositoryConfig;
import main.java.campionat.domain.CampionatException;
import main.java.campionat.domain.entity.Arbitru;
import main.java.campionat.domain.entity.Meci;

import static main.java.campionat.domain.ErrorCode.MECI_NOT_FOUND;


public class MeciRepositoryImpl implements MeciRepository {

//    private ArbitruRepository arbitruRepository = RepositoryConfig.getInstance().getArbitruRepository();
    //private ArbitruRepositoryFileImpl arb;

    //ArbitruRepositoryFileImpl a=
   //Arbitru[] arbitri = arbitruRepository.getArbitri();

    private Meci[] meciuri = new Meci[]{
            new Meci("Arbitru1","CSA Steaua", "Astra", "2-0","22 aprilie 2019"),
            new Meci("Arbitru2", "CSA Steaua", "Dinamo", "4-1", "14 aprilie 2019"),
            new Meci("Arbitru3", "CSA Steaua", "Viitorul", "3-1","19 aprilie 2019"),
            new Meci("Arbitru4", "CSA Steaua", "Otelul", "2-0","19 aprilie 2019"),
            new Meci("Arbitru5", "Rapid", "CSA Steaua", "1-5","19 aprilie 2019"),
            new Meci("Arbitru6", "Astra", "Dinamo", "1-3","15 aprilie 2019"),
            new Meci("Arbitru7", "Astra", "Viitorul", "1-4","1 mai 2019"),
            new Meci("Arbitru8", "Rapid", "Astra", "3-5","14 aprilie 2019"),
            new Meci("Arbitru9", "Astra", "Otelul", "2-5","22 aprilie 2019"),
            new Meci("Arbitru10", "Viitorul", "Dinamo", "0-0","24 aprilie 2019"),
            new Meci("Arbitru11", "Viitorul", "Otelul", "4-1","2 aprilie 2019"),
            new Meci("Arbitru12", "Rapid", "Viitorul", "4-3","28 aprilie 2019"),
            new Meci("Arbitru13", "Otelul", "Dinamo", "4-4","29 aprilie 2019"),
            new Meci("Arbitru14", "Rapid", "Dinamo", "2-0","2 aprilie 2019"),
            new Meci("Arbitru15", "Rapid", "Otelul", "1-0","11 aprilie 2019")

    };

/*
@Override
    public void addMeci(Arbitru arbitru,String ech1,String ech2, String scor, String data, Sponsor sponsor){
    Meci newMeci=new Meci(arbitru,ech1,ech2,scor,data,sponsor);
    meciuri[currentIndex]=newMeci;
    currentIndex++;

}

@Override
    public int getNrMeciuri(){
    return meciuri.length;

}
*/
@Override
public boolean existsByName(String EchipaName) {
    for(int i=0;i<meciuri.length;i++)
        if(meciuri[i].getEchipa1().equals(EchipaName) || meciuri[i].getEchipa2().equals(EchipaName))
            return true;
    return false; // nu exista echipa EchipaNume
}


    @Override
    public Meci getMeciByName(String MeciName) {
        int i;
        String[] sir = MeciName.split("-");
        Meci selectedMeci=null;
        for (i = 0; i < meciuri.length; i++)
            if (meciuri[i].getEchipa1().equals(sir[0]) && meciuri[i].getEchipa2().equals(sir[1]) )
                selectedMeci=meciuri[i];

        if(selectedMeci==null)
            throw new CampionatException(MECI_NOT_FOUND," Nu s-a putut gasi Meciul "+MeciName);
        return selectedMeci;
    }

@Override
    public Meci[] getMeciuri(){return meciuri; }

}

