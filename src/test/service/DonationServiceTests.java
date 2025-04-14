package test.service;

import interfaces.DonationRepositoryInterface;
import model.Donation;
import service.DonationService;

import java.util.*;

import static test.TestRunner.assertEquals;

public final class DonationServiceTests {
    private DonationServiceTests() {
        //private constructor
    }

    public static void runAll() {
        System.out.println("DonationServiceTests:");
        testDonationSuccess();
        testDonationOverLimit();
    }

    public static void testDonationSuccess() {
        var repo = new InMemoryDonationRepo();
        var donors = DonorServiceTests.createTestService();
        var campaigns = CampaignServiceTests.createTestService();

        donors.addDonor("Greg", 300);
        campaigns.addCampaign("Dogs");

        DonationService service = new DonationService(repo, donors, campaigns);
        boolean result = service.makeDonation("Greg",
                "Dogs",
                200);

        assertEquals(true,
                result,
                "Donation accepted");
        assertEquals(200,
                donors.get("Greg").getTotalDonated(),
                "Donation amount updated");
        assertEquals(200,
                campaigns.get("Dogs").getTotalRaised(),
                "Campaign updated");
        assertEquals(1,
                repo.getHistory().size(),
                "Donation recorded");
    }

    public static void testDonationOverLimit() {
        var repo = new InMemoryDonationRepo();
        var donors = DonorServiceTests.createTestService();
        var campaigns = CampaignServiceTests.createTestService();

        donors.addDonor("Greg", 300);
        campaigns.addCampaign("Dogs");

        DonationService service = new DonationService(repo, donors, campaigns);
        boolean result = service.makeDonation("Greg",
                "Dogs",
                400);

        assertEquals(false,
                result,
                "Donation rejected for exceeding limit");
        assertEquals(0,
                repo.getHistory().size(),
                "No donation recorded");
    }

    static class InMemoryDonationRepo implements DonationRepositoryInterface {
        private final List<Donation> history = new ArrayList<>();

        @Override
        public List<Donation> loadAllDonations() {
            return new ArrayList<>(history);
        }

        @Override
        public void saveDonation(final Donation donation) {
            history.add(donation);
        }

        // Getter for testing
        public List<Donation> getHistory() {
            return history;
        }
    }
}
