package src.model;

public class Campaign {
    private String name;
    private int total_raised;

    public Campaign(String name) {
        this.name = name;
        this.total_raised = 0;
    }

    public void add_donation(int amount){
        total_raised += amount;
    }


    public String getName() {
        return name;
    }

    public int getTotal_raised() {
        return total_raised;
    }
}