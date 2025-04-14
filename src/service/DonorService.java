package service;

import interfaces.DonorRepositoryInterface;
import model.Donor;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Service responsible for managing donors, including in-memory storage
 * and synchronization with persistent storage.
 */
public final class DonorService {
    private final DonorRepositoryInterface donorRepo;
    private final Map<String, Donor> donors;

    /**
     * Constructs a DonorService with the given repository.
     * Loads donors from persistent storage into memory.
     *
     * @param donorRepo the donor repository
     */
    public DonorService(final DonorRepositoryInterface donorRepo) {
        this.donorRepo = donorRepo;
        this.donors = new HashMap<>();

        for (Donor d : donorRepo.loadAllDonors()) {
            donors.put(d.getName(), d);
        }
    }

    /**
     * Adds a new donor to the system if one does not already exist.
     *
     * @param name         the donor's name
     * @param monthlyLimit the donor's monthly donation limit
     */
    public void addDonor(final String name, final int monthlyLimit) {
        if (!donors.containsKey(name)) {
            Donor d = new Donor(name, monthlyLimit);
            donors.put(name, d);
        }
    }

    public Donor get(final String name) {
        return donors.get(name);
    }

    public Collection<Donor> getAllDonors() {
        return donors.values();
    }

    /**
     * Saves all donors from memory to persistent storage.
     */
    public void saveAllDonors() {
        donorRepo.saveAllDonors(new ArrayList<>(donors.values()));
    }

    /**
     * Deletes a donor from memory and persistent storage.
     *
     * @param donorName the name of the donor to delete
     */
    public void deleteDonor(final String donorName) {
        donors.remove(donorName);
        donorRepo.deleteDonor(donorName);
    }
}
