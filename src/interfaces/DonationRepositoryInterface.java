package interfaces;

import model.Donation;
import java.util.List;

/**
 * Interface for accessing and manipulating donation data in the data source.
 */
public interface DonationRepositoryInterface {
    List<Donation> loadAllDonations();
    void saveDonation(Donation donation);
}
