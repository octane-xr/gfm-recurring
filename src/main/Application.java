package main;

import controller.CommandController;
import repository.CampaignRepository;
import repository.DonationRepository;
import repository.DonorRepository;
import interfaces.DonorRepositoryInterface;
import service.CampaignService;
import service.DonationService;
import service.DonorService;
import view.ConsoleView;

import java.io.IOException;

/**
 * Main entry point for the donation management application.
 */
public final class Application {

    /**
     * Private constructor to prevent instantiation.
     */
    private Application() {
        // This class should not be instantiated
    }

    /**
     * Starts the application by initializing repositories, services,
     * controllers, and launching the console view.
     *
     * @param args optional command-line arguments
     * @throws IOException if there is an I/O error during initialization
     */
    public static void main(final String[] args) throws IOException {

        final String donorCSV = "data/donors.csv";
        final String campaignCSV = "data/campaigns.csv";
        final String donationCSV = "data/donations.csv";

        DonorRepositoryInterface donorRepo = new DonorRepository(donorCSV);
        CampaignRepository campaignRepo = new CampaignRepository(campaignCSV);
        DonationRepository donationRepo = new DonationRepository(donationCSV);

        DonorService donorServive = new DonorService(donorRepo);
        CampaignService campaignServive = new CampaignService(campaignRepo);
        DonationService donationService = new DonationService(
                donationRepo, donorServive, campaignServive);

        CommandController controller = new CommandController(
                donationService, campaignServive, donorServive);

        ConsoleView view = new ConsoleView(controller);
        view.run(args);

    }
}
