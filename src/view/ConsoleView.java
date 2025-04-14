package view;

import controller.CommandController;
import model.Donation;
import model.Donor;
import model.Campaign;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


/**
 * Provides a console-based interface for interacting with the donation system.
 * Displays summaries, lists of donations and sends commands
 * to the command controller to get processed there.
 */
public final class ConsoleView {
    private final CommandController commandController;

    /**
     * Constructs a ConsoleView with the given CommandController.
     *
     * @param commandController the controller to handle commands and access data
     */
    public ConsoleView(final CommandController commandController) {
        this.commandController = commandController;
    }

    /**
     * Prints a summary of all donors and campaigns to the console.
     * Includes total and average donation per donor,
     * and total raised per campaign.
     */
    public void printSummary() {
        List<Donor> donors = new ArrayList<>(
                commandController.getDonorService().getAllDonors());
        List<Campaign> campaigns = new ArrayList<>(
                commandController.getCampaignService().getAllCampaigns());

        donors.sort(Comparator.comparing(Donor::getName));
        campaigns.sort(Comparator.comparing(Campaign::getName));

        System.out.println("Donors: ");
        for (Donor d : donors) {
            System.out.println(d.getName() + ": $"
                    + d.getTotalDonated()
                    + " Average: $"
                    + d.getAverageDonation());
        }
        System.out.println("\nCampaigns: ");
        for (Campaign c : campaigns) {
            System.out.println(c.getName()
                    + ": Total: $"
                    + c.getTotalRaised());
        }

    }

    /**
     * Prints a sorted list of all donations to the console.
     * Each donation includes donor name, amount, and target campaign.
     */
    public void printDonations() {
        List<Donation> donations = new ArrayList<>(
                commandController.getDonationService().getDonations());
        donations.sort(Comparator.comparing(Donation::getDonorName));
        System.out.println("Donations:");
        for (Donation d : donations) {
            System.out.println(d.getDonorName()
                    + " donated $" + d.getAmountDonated()
                    + " to " + d.getCampaignName());
        }
    }

    /**
     * Runs the main command loop for the console view.
     * If a file is provided as an argument,
     * it processes commands from the file first.
     * Then enters an interactive input loop.
     *
     * @param args an optional argument with the path to a command file
     */
    public void run(final String[] args) {
        Scanner sc = null;

        if (args.length == 1) {
            try {
                sc = new Scanner(new File(args[0]));
                while (sc.hasNextLine()) {
                    String line = sc.nextLine().trim();
                    if (line.isEmpty()) {
                        continue;
                    }
                    System.out.println(line);
                    commandController.processCommands(line);
                }

            } catch (FileNotFoundException e) {
                System.err.println("File: " + args[0] + " not found.");
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                if (sc != null) {
                    sc.close();
                }
                printSummary();
            }
        }
        System.out.println();
        sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine().trim();
            commandController.processCommands(input);
        }
    }
}
