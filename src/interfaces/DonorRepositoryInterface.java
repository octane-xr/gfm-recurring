package src.interfaces;

import src.model.Donor;
import java.util.List;

public interface DonorRepositoryInterface {
    List<Donor> loadAllDonors();
    void saveAllDonors(List<Donor> donors);
}
