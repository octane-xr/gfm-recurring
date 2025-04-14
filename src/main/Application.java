package main;

import controller.CommandController;
import repository.*;
import interfaces.*;
import service.*;
import view.ConsoleView;

import java.io.IOException;


public class Application {
    public static void main(String[] args) throws IOException {


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
