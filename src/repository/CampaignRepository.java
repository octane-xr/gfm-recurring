package repository;

import model.Campaign;
import interfaces.CampaignRepositoryInterface;
import util.CsvUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Repository for managing Campaign data using a CSV file.
 */
public final class CampaignRepository implements CampaignRepositoryInterface {
    private final String filePath;
    private final CsvUtil csvUtil;

    /**
     * Constructs a new CampaignRepository with the given file path.
     * Ensures that the file exists.
     *
     * @param filePath the path to the CSV file
     * @throws IOException if an I/O error occurs
     */
    public CampaignRepository(final String filePath) throws IOException {
        this.filePath = filePath;
        csvUtil = new CsvUtil();
        csvUtil.ensureFileExists(filePath);
    }

    /**
     * Loads all campaigns from the CSV file.
     *
     * @return a list of campaigns loaded from the file
     */
    @Override
    public List<Campaign> loadAllCampaigns() {
        List<Campaign> campaigns = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length < 2) {
                    continue;
                }
                Campaign c = new Campaign(parts[0]);
                c.addDonation(Integer.parseInt(parts[1]));
                campaigns.add(c);
            }
        } catch (IOException e) {
            System.err.println("Failed to read campaign file: "
                    + e.getMessage());
        }
        return campaigns;
    }

    /**
     * Saves all given campaigns to the CSV file, overwriting existing data.
     *
     * @param campaigns the list of campaigns to be saved
     */
    @Override
    public void saveAllCampaigns(final List<Campaign> campaigns) {
        try (PrintWriter pw = new PrintWriter(new File(filePath))) {
            for (Campaign c : campaigns) {
                pw.println(c.getName()
                        + ","
                        + c.getTotalRaised());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save campaigns. Error: "
                    + e.getMessage());
        }
    }

    /**
     * Deletes a campaign with the given name from the file.
     *
     * @param campaignName the name of the campaign to delete
     */
    @Override
    public void deleteCampaign(final String campaignName) {
        List<Campaign> campaigns = loadAllCampaigns();
        campaigns.removeIf(c -> c.getName().equalsIgnoreCase(campaignName));
        saveAllCampaigns(campaigns);
    }
}
