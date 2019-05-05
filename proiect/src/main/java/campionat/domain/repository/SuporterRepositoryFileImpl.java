package main.java.campionat.domain.repository;

import main.java.campionat.domain.CampionatException;
import main.java.campionat.domain.ErrorCode;
import main.java.campionat.domain.entity.Suporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Double;

import static main.java.campionat.domain.ErrorCode.JUCATOR_NOT_FOUND;

public class SuporterRepositoryFileImpl implements SuporterRepository{
    private Suporter[] suporteri=new Suporter[100];

    public SuporterRepositoryFileImpl(String filename) throws FileNotFoundException {
        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(fileInputStream);
        int counter = 0;
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
            Suporter newEntry = new Suporter(new Integer(values[0]), values[1],values[2]);
            suporteri[counter++] = newEntry;
        }
    }


    /*
@Override
    public Suporter getSuporterById(int SuporterId) {
        try
        {
            //int id = new Integer(SuporterId);
            Suporter selectedSuporter = null;
            for(int i = 0; i< suporteri.length; i++){
                if(suporteri[i].getId() == SuporterId)
                {
                    selectedSuporter =suporteri[i];
                    break;
                }
            }
            return selectedSuporter;
        }
        catch (NumberFormatException e)
        {
            throw new CampionatException(ErrorCode.INCORRECT_USER_ID, "Ati introdus ID-ul in format gresit !");
        }
    }
    */
    @Override
    public Suporter getSuporterById(int SuporterId) {
          //int id = new Integer(SuporterId);
            Suporter selectedSuporter = null;
            int i;
            for(i = 0; i< suporteri.length; i++)
                if(suporteri[i].getId() == SuporterId)
                {
                selectedSuporter=suporteri[i];
                break;
                }
            if(selectedSuporter==null)
                throw new CampionatException(JUCATOR_NOT_FOUND," Nu s-a putut gasi suporterul cu id-ul "+SuporterId);

            return suporteri[i];
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
