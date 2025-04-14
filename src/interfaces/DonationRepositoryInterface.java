package interfaces;

import model.Donation;
import java.util.List;

public interface DonationRepositoryInterface {
    List<Donation> loadAllDonations();
    void saveDonation(Donation donation);
}
