package repository;

import interfaces.DonorRepositoryInterface;
import model.Donor;
import util.CsvUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Repository for managing Donor data using a CSV file.
 */
public final class DonorRepository implements DonorRepositoryInterface {
    private final String filePath;
    private final CsvUtil csvUtil;

    /**
     * Constructs a new DonorRepository with the given file path.
     * Ensures the file exists.
     *
     * @param filePath the path to the CSV file
     * @throws IOException if an I/O error occurs
     */
    public DonorRepository(final String filePath) throws IOException {
        this.filePath = filePath;
        csvUtil = new CsvUtil();
        csvUtil.ensureFileExists(filePath);
    }

    /**
     * Loads all donors from the CSV file.
     *
     * @return a list of donors
     */
    @Override
    public List<Donor> loadAllDonors() {
        List<Donor> donors = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length < 4) {
                    continue;
                }
                Donor donor = new Donor(parts[0], Integer.parseInt(parts[1]));
                donor.addDonation(Integer.parseInt(parts[2]));
                for (int i = 1; i < Integer.parseInt(parts[3]); i++) {
                    donor.addDonation(0);
                }
                donors.add(donor);
            }

        } catch (IOException e) {
            System.err.println("No donor file found, creating new one...");
        }
        return donors;
    }

    /**
     * Saves all donors to the CSV file, overwriting existing data.
     *
     * @param donors the list of donors to save
     */
    @Override
    public void saveAllDonors(final List<Donor> donors) {
        try (PrintWriter pw = new PrintWriter(new File(filePath))) {
            for (Donor d : donors) {
                pw.println(d.getName()
                        + ","
                        + d.getMonthlyLimit()
                        + ","
                        + d.getTotalDonated()
                        + ","
                        + d.getDonationCount());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save donors. Error: "
                    + e.getMessage());
        }

    }

    /**
     * Deletes a donor with the specified name from the data file.
     *
     * @param donorName the name of the donor to delete
     */
    @Override
    public void deleteDonor(final String donorName) {
        List<Donor> donors = loadAllDonors();
        donors.removeIf(d -> d.getName().equalsIgnoreCase(donorName));
        saveAllDonors(donors);
    }
}
