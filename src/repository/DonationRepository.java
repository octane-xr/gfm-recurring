package repository;

import model.Donation;
import interfaces.DonationRepositoryInterface;
import util.CsvUtil;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Repository for managing
 * Donation data using a CSV file.
 */
public final class DonationRepository implements DonationRepositoryInterface {
    private final String filePath;
    private final CsvUtil csvUtil;

    /**
     * Constructs a new DonationRepository
     * with the given file path.
     * Ensures that the file exists.
     *
     * @param filePath the path to the CSV file
     * @throws IOException if an I/O error occurs
     */
    public DonationRepository(final String filePath) throws IOException {
        this.filePath = filePath;
        csvUtil = new CsvUtil();
        csvUtil.ensureFileExists(filePath);
    }

    /**
     * Loads all donations from the CSV file.
     *
     * @return a list of donations loaded from the file
     */
    @Override
    public List<Donation> loadAllDonations() {
        List<Donation> donations = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length < 2) {
                    continue;
                }
                Donation d = new Donation(
                        parts[0], parts[1], Integer.parseInt(parts[2]));
                donations.add(d);
            }
        } catch (IOException e) {
            System.err.println("No donations file found, creating new one...");
        }
        return donations;
    }

    /**
     * Saves a single donation entry by appending it to the CSV file.
     *
     * @param donation the donation to save
     */
    @Override
    public void saveDonation(final Donation donation) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println(donation.getDonorName()
                    + ","
                    + donation.getCampaignName()
                    + ","
                    + donation.getAmountDonated());

        } catch (IOException e) {
            throw new RuntimeException("Failed to save donation. Error: "
                    + e.getMessage());
        }

    }
}
