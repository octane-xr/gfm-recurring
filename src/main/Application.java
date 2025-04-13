package src.main;

import src.controller.CommandController;
import src.repository.*;
import src.interfaces.*;
import src.service.*;
import src.view.ConsoleView;


public class Application {
    public static void main(String[] args) {


        String donorCSV = "data/donors.csv";
        String campaignCSV = "data/campaigns.csv";
        String donationCSV = "data/donations.csv";

        DonorRepositoryInterface donorRepo = new DonorRepository(donorCSV);
        CampaignRepository campaignRepo = new CampaignRepository(campaignCSV);
        DonationRepository donationRepo = new DonationRepository(donationCSV);

        DonorService donorServive = new DonorService(donorRepo);
        CampaignService campaignServive = new CampaignService(campaignRepo);
        DonationService donationService = new DonationService(donationRepo, donorServive, campaignServive);

        CommandController controller = new CommandController(donationService, campaignServive, donorServive);

        ConsoleView view = new ConsoleView(controller);
        view.run(args);

    }
}
