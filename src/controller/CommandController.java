package controller;

import service.CampaignService;
import service.DonationService;
import service.DonorService;

import view.ConsoleView;

public class CommandController {
    private final DonationService donationService;

    private final CampaignService campaignService;

    private final DonorService donorService;

    private final ConsoleView consoleView;

    /**
     * Expected number of arguments for the different commands.
     */
    private static final int ARG_COUNT_ADD_DONOR = 4;
    private static final int ARG_COUNT_ADD_CAMPAIGN = 3;
    private static final int ARG_COUNT_DONATION = 4;
    private static final int ARG_COUNT_DELETE = 3;


    /**
     * Creates a CommandController to manage application commands and services.
     *
     * @param donationService the donation service
     * @param campaignService the campaign service
     * @param donorService    the donor service
     */
    public CommandController(final DonationService donationService,
                             final CampaignService campaignService,
                             final DonorService donorService) {
        this.donationService = donationService;
        this.campaignService = campaignService;
        this.donorService = donorService;
        this.consoleView = new ConsoleView(this);
    }

    /**
     * Processes a single command line from the user or file input.
     *
     * @param line the command line to be processed
     */
    public void processCommands(final String line) {
        String[] parts = line.trim().split(" ");
        try {
            switch (parts[0]) {
                case "Add":
                    if ("Donor".equals(parts[1])) {
                        processAddDonor(parts);

                    } else if ("Campaign".equals(parts[1])) {
                        processAddCampaign(parts);

                    } else {
                        System.out.println("Unknown command: " + parts[1]);
                    }
                    break;

                case "Donate":
                    processDonations(parts);
                    break;

                case "Delete":
                    if (parts.length != ARG_COUNT_DELETE) {
                        System.out.println("Missing arguments");
                        break;
                    }

                    if ("Donor".equals(parts[1])) {
                        processDeleteDonor(parts);
                        break;

                    } else if ("Campaign".equals(parts[1])) {
                        processDeleteCampaign(parts);
                        break;
                    }

                case "Print_summary":
                    consoleView.printSummary();
                    break;

                case "Print_donations":
                    consoleView.printDonations();
                    break;

                case "Exit":
                    persist();
                    System.exit(0);

                default:
                    System.err.println(parts[0] + ": command not found");
            }
        } catch (Exception e) {
            System.err.println("Error processing line: " + line);
            System.err.println("    -->"
                    + e.getClass().getSimpleName()
                    + ": "
                    + e.getMessage());
        }
    }

    private void processAddDonor(final String[] parts) {
        if (parts.length != ARG_COUNT_ADD_DONOR) {
            System.out.println("Missing arguments");
            return;
        }
        String donorName = parts[2];

        if (donorService.get(donorName) != null) {
            System.out.println("Donor " + donorName + " already exists");
            return;
        }

        if (!parts[3].startsWith("$")) {
            System.out.println("Invalid format. Use: Add Donor [name] $[amount]");
            return;
        }

        int monthlyLimit = Integer.parseInt(parts[3].replace("$", ""));

        if (monthlyLimit < 1) {
            System.out.println("Limit must be greater than 0");
            return;
        }
        donorService.addDonor(donorName, monthlyLimit);
        persist();

    }

    private void processAddCampaign(final String[] parts) {
        if (parts.length != ARG_COUNT_ADD_CAMPAIGN) {
            System.out.println("Missing arguments");
            return;
        }
        String campaignName = parts[2];

        if (campaignService.get(campaignName) != null) {
            System.out.println("Campaign " + campaignName + " already exists");
            return;
        }

        campaignService.addCampaign(campaignName);
        persist();

    }

    private void processDonations(final String[] parts) {
        if (parts.length != ARG_COUNT_DONATION) {
            System.out.println("Missing arguments");
            return;
        }
        String donorName = parts[1];
        if (donorService.get(donorName) == null) {
            System.out.println("Donor " + donorName + " does not exist");
            return;
        }

        String campaignName = parts[2];

        if (campaignService.get(campaignName) == null) {
            System.out.println("Campaign "
                    + campaignName
                    + " doest not exist");
            return;
        }

        if (!parts[3].startsWith("$")) {
            System.out.println("Invalid formate. Use: Donate [donorName] [campaignName] $[amount]");
            return;
        }

        int amount = Integer.parseInt(parts[3].replace("$", ""));
        if (amount < 1) {
            System.out.println("Amount donated must be greater than 0");
            return;
        }

        donationService.makeDonation(donorName, campaignName, amount);
        persist();

    }

    private void processDeleteDonor(final String[] parts) {
        String donorName = parts[2];

        if (donorService.get(donorName) == null) {
            System.out.println("Donor " + donorName + " doest not exist");
            return;
        }
        donorService.deleteDonor(donorName);
        persist();

    }

    private void processDeleteCampaign(final String[] parts) {
        String campaignName = parts[2];

        if (campaignService.get(campaignName) == null) {
            System.out.println("Campaign " + campaignName + " doest not exist");
            return;
        }

        campaignService.deleteCampaign(campaignName);
        persist();
    }

    /**
     * Persists all donors and campaigns to their respective storage.
     */
    public void persist() {
        donorService.saveAllDonors();
        campaignService.saveAllCampaigns();
    }

    public CampaignService getCampaignService() {
        return campaignService;
    }

    public DonorService getDonorService() {
        return donorService;
    }

    public DonationService getDonationService() {
        return donationService;
    }
}
