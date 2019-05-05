package main.java.campionat.configuration;

import java.io.FileNotFoundException;
import main.java.campionat.domain.repository.*;

public class RepositoryConfig {

    private MeciRepository meciRepository = new MeciRepositoryImpl();
    private JucatorRepository jucatorRepository = new JucatorRepositoryImpl();
    private AntrenorRepository antrenorRepository=new AntrenorRepositoryImpl();
    private ArbitruRepository arbitruRepository;
    private PresedinteEchipaRepository presedinteEchipaRepository;
    private SponsorRepository sponsorRepository;
    private SuporterRepository suporterRepository;


    public MeciRepository getMeciRepository() {
        return meciRepository;
    }
    public JucatorRepository getJucatorRepository() {
        return jucatorRepository;
    }
    public AntrenorRepository getAntrenorRepository() {
        return antrenorRepository;
    }
    public PresedinteEchipaRepository getPresedinteEchipaRepository() {
        return presedinteEchipaRepository;
    }
    public SponsorRepository getSponsorRepository() {
        return sponsorRepository;
    }
    public SuporterRepository getSuporterRepository() {
        return suporterRepository;
    }
    public ArbitruRepository getArbitruRepository() { return arbitruRepository; }


    private static RepositoryConfig ourInstance = new RepositoryConfig();
    public static RepositoryConfig getInstance() { return ourInstance; }

    private RepositoryConfig()
    {
        try
        {
            arbitruRepository = new ArbitruRepositoryFileImpl("C:/Users/Dani/Desktop/licenta-info/an2/sem2/PAO/proiect/src/main/java/campionat/domain/repository/arbitru_file");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not create ArbitruRepositoryFileImpl: "+e.getMessage());
            System.out.println("The system will use the mock data from  ArbitruRepositoryImpl");
            arbitruRepository = new ArbitruRepositoryImpl(); //Use data from another source
        }

        try
        {
            presedinteEchipaRepository = new PresedinteEchipaRepositoryFileImpl("C:/Users/Dani/Desktop/licenta-info/an2/sem2/PAO/proiect/src/main/java/campionat/domain/repository/presedinte_file");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not create PresedinteEchipaRepositoryFileImpl: "+e.getMessage());
            System.out.println("The system will use the mock data from  PresedinteEchipaRepositoryImpl");
            presedinteEchipaRepository = new PresedinteEchipaRepositoryImpl(); //Use data from another source
        }

        try
        {
            sponsorRepository = new SponsorRepositoryFileImpl("C:/Users/Dani/Desktop/licenta-info/an2/sem2/PAO/proiect/src/main/java/campionat/domain/repository/sponsor_file");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not create SponsorRepositoryFileImpl: "+e.getMessage());
            System.out.println("The system will use the mock data from  SponsorRepositoryImpl");
            sponsorRepository = new SponsorRepositoryImpl(); //Use data from another source
        }

        try
        {
            suporterRepository = new SuporterRepositoryFileImpl("C:/Users/Dani/Desktop/licenta-info/an2/sem2/PAO/proiect/src/main/java/campionat/domain/repository/suporter_file");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not create SuporterRepositoryFileImpl: "+e.getMessage());
            System.out.println("The system will use the mock data from  SuporterRepositoryImpl");
            suporterRepository = new SuporterRepositoryImpl(); //Use data from another source
        }
    }

}
