package src;
import java.util.ArrayList;
import java.util.List;


public class Donor {
    String name;
    int monthly_limit;
    List<Integer> donations = new ArrayList<>();

    public Donor(String name, int monthly_limit) {
        this.name = name;
        this.monthly_limit = monthly_limit;
    }

    public int totalDonated(){
        return donations.stream().mapToInt(i -> i).sum();
    }

    public boolean canDonate(int amount){
        return totalDonated() + amount <= monthly_limit;
    }

    public boolean makeDonation(int amount){
        if(canDonate(amount)){
            donations.add(amount);
            return true;
        }

        else{
            System.out.println(amount + " surpasses the monthly limit (" + monthly_limit + ")");
            return false;
        }

    }

    public int averageDonation(){
        if(donations.isEmpty()){
            return 0;
        }
        return totalDonated()/donations.size();
    }

    public String getName() {
        return name;
    }
}