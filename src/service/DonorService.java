package src.service;

import src.interfaces.DonorRepositoryInterface;
import src.model.Donor;

import java.util.*;

public class DonorService {
    private final DonorRepositoryInterface donorRepo;
    private final Map<String, Donor> donors;

    public DonorService(DonorRepositoryInterface donorRepo) {
        this.donorRepo = donorRepo;
        this.donors = new HashMap<>();
        for(Donor d : donorRepo.loadAllDonors()) {
            donors.put(d.getName(),d);
        }
    }

    public void addDonor(String name, int monthly_limit){
        if(!donors.containsKey(name)){
            Donor d = new Donor(name,monthly_limit);
            donors.put(name, d);
        }
    }

    public Donor get(String name){
        return donors.get(name);
    }


    public Collection<Donor> getAllDonors(){
        return donors.values();
    }

    public void saveAllDonors() {
        donorRepo.saveAllDonors(new ArrayList<>(donors.values()));
    }
}
