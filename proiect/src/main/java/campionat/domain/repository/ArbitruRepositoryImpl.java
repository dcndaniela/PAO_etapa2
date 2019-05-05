package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.Arbitru;

public class ArbitruRepositoryImpl implements ArbitruRepository{
    private Arbitru[] arbitri=new Arbitru[]{
            new Arbitru(1, "Arbitru1", "Facultatea de Sport, Cluj ",2789),
            new Arbitru(2, "Arbitru2", "Facultatea de Educatie fizica si sport, Iasi ",5590),
            new Arbitru(3, "Arbitru3", "Colegiul National de Educatie fizica, Sibiu ",5876.5),
            new Arbitru(4, "Arbitru4", "Facultatea de Sport, Sibiu  ",8234),
            new Arbitru(5, "Arbitru5", "Colegiul National de Educatie fizica, Bucuresti ",7777.7),
            new Arbitru(6, "Arbitru6", "Facultatea de Sport, Craiova  ",5643),
            new Arbitru(7, "Arbitru7", "Colegiul National de Educatie fizica, Craiova ",4567),
            new Arbitru(8, "Arbitru8", "Facultatea de Sport, Calarasi ",5239.8),
            new Arbitru(9, "Arbitru9", "Facultatea de Sport, Constanta",4890.2),
            new Arbitru(10, "Arbitru10", "Facultatea de Sport, Bacau ",5621),
            new Arbitru(11, "Arbitru11", "Facultatea de Sport, Giurgiu  ",6900),
            new Arbitru(12, "Arbitru12", "Colegiul National de Educatie fizica, Bacau ",6431.6),
            new Arbitru(13, "Arbitru13", "Colegiul National de Educatie fizica, Bucuresti ",8844),
            new Arbitru(14, "Arbitru14", "Facultatea de Sport, Bucuresti  ",8364.6),
            new Arbitru(15, "Arbitru15", "Colegiul National de Educatie fizica, Craiova ",7314.8)

    };


    @Override
    public Arbitru getArbitruByName(String ArbitruName) {
        int i;
        for (i = 0; i < arbitri.length; i++)
            if (arbitri[i].getNume_persoana().equals(ArbitruName))
                break;
        return arbitri[i];
    }

    @Override
    public Arbitru getArbitruById(int ArbitruId) {
        Arbitru selectedArbitru = null;
        for(int i = 0; i< arbitri.length; i++){
            if(arbitri[i] instanceof Arbitru &&
                    arbitri[i].getId() == ArbitruId){
                selectedArbitru = (Arbitru) arbitri[i];
            }
        }
        return selectedArbitru;
    }


    @Override
    public boolean existsByName(String ArbitruName) {
        int nr=0;
        for(int i=0;i<arbitri.length;i++)
            if(arbitri[i] instanceof Arbitru && arbitri[i].getNume_persoana().equals(ArbitruName))
                nr++;
        if(nr==1)
            return true;
        return false; // sunt mai multi arbitri cu acelasi nume

    }
    @Override
    public Arbitru[] getArbitri(){return arbitri;}
}
