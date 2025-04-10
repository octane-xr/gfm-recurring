package src.repository;

import src.interfaces.DonorRepositoryInterface;
import src.model.Donor;

import java.io.*;
import java.util.*;

public class DonorRepository implements DonorRepositoryInterface {
    private final String file_path;

    public DonorRepository(String file_path) {
        this.file_path = file_path;
    }

    @Override
    public List<Donor> loadAllDonors() {
        List<Donor> donors = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(file_path))){
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if(parts.length<4) continue;
                Donor donor = new Donor(parts[0], Integer.parseInt(parts[1]));
                donor.addDonation(Integer.parseInt(parts[2]));
                for(int i =1; i<Integer.parseInt(parts[3]); i++){
                    donor.addDonation(0);
                }
                donors.add(donor);
            }

        } catch (IOException e) {
            System.err.println("No donor file found, creating new one...");
        }
        return donors;
    }

    @Override
    public void saveAllDonors(List<Donor> donors) {
        try(PrintWriter pw = new PrintWriter(new File(file_path))){
            for(Donor d: donors){
                pw.println(d.getName() + "," + d.getMonthly_limit() + "," + d.getTotal_donated() + "," + d.getDonation_count());
            }
        }catch (IOException e) {
            throw new RuntimeException("Failed to save donors. Error: " + e.getMessage());
        }

    }
}
