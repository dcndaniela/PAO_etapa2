package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.Arbitru;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Double;

public class ArbitruRepositoryFileImpl implements ArbitruRepository{
    private Arbitru[] arbitri=new Arbitru[100];

    public ArbitruRepositoryFileImpl(String filename) throws FileNotFoundException  {
        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(fileInputStream);
        int counter = 0;
        while (scanner.hasNext()){ //cat timp mai pot citi din fisier
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Arbitru newEntry = new Arbitru(new Integer(values[0]), values[1],values[2],new Double(values[4]));
            arbitri[counter++] = newEntry;
        }
    }


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
            if(arbitri[i].getNume_persoana().equals(ArbitruName))
                nr++;
        if(nr==1)
            return true;
        return false; // sunt mai multi arbitri cu acelasi nume

    }


    @Override
    public Arbitru[] getArbitri(){return arbitri;}

}
