package interfaces;

import model.Donor;
import java.util.List;

public interface DonorRepositoryInterface {
    List<Donor> loadAllDonors();
    void saveAllDonors(List<Donor> donors);
    void deleteDonor(String donorName);
}
