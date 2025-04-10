package src.interfaces;

import src.model.Donation;
import java.util.List;

public interface DonationRepositoryInterface {
    List<Donation> loadAllDonations();
    void saveDonation(Donation donation);
}
