package test.controller;

import controller.CommandController;
import model.Campaign;
import model.Donor;
import model.Donation;
import service.CampaignService;
import service.DonationService;
import service.DonorService;
import interfaces.CampaignRepositoryInterface;
import interfaces.DonorRepositoryInterface;
import interfaces.DonationRepositoryInterface;

import java.util.*;

import static test.TestRunner.assertEquals;

public final class CommandControllerTests {

    private CommandControllerTests() {
        //private builder
    }

    public static void runAll() {
        System.out.println("CommandControllerTests:");
        testAddDonorCommand();
        testAddCampaignCommand();
        testDonateCommand();
        testInvalidCommand();
    }

    public static void testAddDonorCommand() {
        var donorRepo = new InMemoryDonorRepo();
        var campaignRepo = new InMemoryCampaignRepo();
        var donationRepo = new InMemoryDonationRepo();

        var controller = new CommandController(
                new DonationService(donationRepo,
                        new DonorService(donorRepo),
                        new CampaignService(campaignRepo)),
                new CampaignService(campaignRepo),
                new DonorService(donorRepo)
        );

        controller.processCommands("Add Donor Greg $500");
        assertEquals(1, donorRepo.store.size(), "Donor 'Greg' added");
        assertEquals(500,
                donorRepo.store.get("Greg").getMonthlyLimit(),
                "Donor limit correctly set");
    }

    public static void testAddCampaignCommand() {
        var donorRepo = new InMemoryDonorRepo();
        var campaignRepo = new InMemoryCampaignRepo();
        var donationRepo = new InMemoryDonationRepo();

        var controller = new CommandController(
                new DonationService(donationRepo,
                        new DonorService(donorRepo),
                        new CampaignService(campaignRepo)),
                new CampaignService(campaignRepo),
                new DonorService(donorRepo)
        );

        controller.processCommands("Add Campaign SaveTrees");
        assertEquals(1,
                campaignRepo.store.size(),
                "Campaign 'SaveTrees' added");
    }

    public static void testDonateCommand() {
        var donorRepo = new InMemoryDonorRepo();
        var campaignRepo = new InMemoryCampaignRepo();
        var donationRepo = new InMemoryDonationRepo();

        donorRepo.store.put("Greg", new Donor("Greg", 500));
        campaignRepo.store.put("Dogs", new Campaign("Dogs"));

        var controller = new CommandController(
                new DonationService(donationRepo,
                        new DonorService(donorRepo),
                        new CampaignService(campaignRepo)),
                new CampaignService(campaignRepo),
                new DonorService(donorRepo)
        );

        controller.processCommands("Donate Greg Dogs $200");
        assertEquals(1, donationRepo.history.size(), "Donation recorded");
        assertEquals(200,
                donorRepo.store.get("Greg").getTotalDonated(),
                "Donor updated");
        assertEquals(200,
                campaignRepo.store.get("Dogs").getTotalRaised(),
                "Campaign updated");
    }

    public static void testInvalidCommand() {
        var donorRepo = new InMemoryDonorRepo();
        var campaignRepo = new InMemoryCampaignRepo();
        var donationRepo = new InMemoryDonationRepo();

        var controller = new CommandController(
                new DonationService(donationRepo,
                        new DonorService(donorRepo),
                        new CampaignService(campaignRepo)),
                new CampaignService(campaignRepo),
                new DonorService(donorRepo)
        );

        controller.processCommands("Donate Greg Cats $300");
        assertEquals(0,
                donationRepo.history.size(),
                "No donation should be recorded");
    }

    static class InMemoryDonorRepo implements DonorRepositoryInterface {
        Map<String, Donor> store = new HashMap<>();

        public List<Donor> loadAllDonors() {
            return new ArrayList<>(store.values());
        }

        public void saveAllDonors(final List<Donor> donors) {
            store.clear();
            for (Donor d : donors) {
                store.put(d.getName(), d);
            }
        }

        public void deleteAllDonors() {
            store.clear();
        }

        public void deleteDonor(final String name) {
            store.remove(name);
        }
    }

    static class InMemoryCampaignRepo implements CampaignRepositoryInterface {
        Map<String, Campaign> store = new HashMap<>();

        public List<Campaign> loadAllCampaigns() {
            return new ArrayList<>(store.values());
        }

        public void saveAllCampaigns(final List<Campaign> campaigns) {
            store.clear();
            for (Campaign c : campaigns) {
                store.put(c.getName(), c);
            }
        }

        public void deleteCampaign(final String name) {
            store.remove(name);
        }

        public void deleteAllCampaigns() {
            store.clear();
        }
    }

    static class InMemoryDonationRepo implements DonationRepositoryInterface {
        List<Donation> history = new ArrayList<>();

        public List<Donation> loadAllDonations() {
            return new ArrayList<>(history);
        }

        public void saveDonation(final Donation donation) {
            history.add(donation);
        }
    }
}
