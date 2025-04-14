package repository;

import model.Donation;
import interfaces.DonationRepositoryInterface;
import util.CsvUtil;

import java.util.*;
import java.io.*;

public class DonationRepository implements DonationRepositoryInterface {
    private final String file_path;
    private final CsvUtil csv_util;

    public DonationRepository(String file_path) throws IOException {
        this.file_path = file_path;
        csv_util = new CsvUtil();
        csv_util.ensureFileExists(file_path);
    }

    @Override
    public List<Donation> loadAllDonations() {
        List<Donation> donations = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(file_path))){
            while(sc.hasNextLine()){
                String[] parts = sc.nextLine().split(",");
                if(parts.length <2) continue;
                Donation d = new Donation(parts[0],parts[1],Integer.parseInt(parts[2]));
                donations.add(d);
            }

        }catch (IOException e) {
            System.err.println("No donations file found, creating new one...");
        }
        return donations;
    }

    @Override
    public void saveDonation(Donation donation) {

       try(FileWriter fw = new FileWriter(file_path,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)){
           pw.println(donation.getDonor_name() + ","+donation.getCampaign_name()+","+donation.getAmount_donated());

       }catch (IOException e) {
           throw new RuntimeException("Failed to save donation. Error: " + e.getMessage());
       }

    }
}
