package interfaces;

import model.Donor;
import java.util.List;

/**
 * Interface for accessing and manipulating donor data in the data source.
 */
public interface DonorRepositoryInterface {
    List<Donor> loadAllDonors();
    void saveAllDonors(List<Donor> donors);
    void deleteDonor(String donorName);
}
