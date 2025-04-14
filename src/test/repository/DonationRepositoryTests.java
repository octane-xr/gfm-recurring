package test.repository;

import interfaces.DonationRepositoryInterface;
import model.Donation;

import java.util.*;

import static test.TestRunner.assertEquals;

public class DonationRepositoryTests {

    public static void runAll() {
        System.out.println("DonationRepositoryTests:");
        testSaveAndLoadDonations();
    }

    public static void testSaveAndLoadDonations() {
        InMemoryDonationRepo repo = new InMemoryDonationRepo();
        repo.saveDonation(new Donation("Greg", "Dogs", 150));
        repo.saveDonation(new Donation("Ana", "Cats", 200));
        List<Donation> all = repo.loadAllDonations();
        assertEquals(2, all.size(), "Saved and loaded 2 donations");
    }

    static class InMemoryDonationRepo implements DonationRepositoryInterface {
        private final List<Donation> history = new ArrayList<>();

        public List<Donation> loadAllDonations() {
            return new ArrayList<>(history);
        }

        public void saveDonation(Donation donation) {
            history.add(donation);
        }
    }
}
