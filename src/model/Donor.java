package src.model;
import java.util.ArrayList;
import java.util.List;


public class Donor {
    private String name;
    private int monthly_limit;
    private int total_donated;
    private int donation_count;


    public Donor(String name, int monthly_limit) {
        this.name = name;
        this.monthly_limit = monthly_limit;
        this.total_donated = 0;
        this.donation_count = 0;
    }

    public boolean canDonate(int amount){
        return total_donated + amount <= monthly_limit;
    }

    public void addDonation(int amount){
        total_donated += amount;
        donation_count++;
    }

    public int getAverageDonation(){
        return donation_count == 0 ? 0 : total_donated /donation_count;
    }

    public String getName() {
        return name;
    }

    public int getMonthly_limit() {
        return monthly_limit;
    }

    public int getTotal_donated() {
        return total_donated;
    }

    public int getDonation_count() {
        return donation_count;
    }
}