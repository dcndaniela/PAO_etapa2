package main.java.campionat.domain.repository;

import main.java.campionat.domain.entity.PresedinteEchipa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Double;

public class PresedinteEchipaRepositoryFileImpl implements PresedinteEchipaRepository {
    private PresedinteEchipa[] presedinti =new PresedinteEchipa[100];

    public PresedinteEchipaRepositoryFileImpl(String filename) throws FileNotFoundException {
        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(fileInputStream);
        int counter = 0;
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            PresedinteEchipa newEntry = new PresedinteEchipa(new Integer(values[0]), values[1],values[2],new Integer(values[3]));
            presedinti[counter++] = newEntry;
        }
    }


@Override
    public PresedinteEchipa getPresedinteEchipaById(int PresedinteEchId){
    PresedinteEchipa selectedPresedinteEchipa = null;
    for(int i = 0; i< presedinti.length; i++){
        if(presedinti[i].getId() == PresedinteEchId){
            selectedPresedinteEchipa = presedinti[i];
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
