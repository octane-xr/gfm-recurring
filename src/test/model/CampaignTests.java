package test.model;

import model.Campaign;
import static test.TestRunner.assertEquals;

public class CampaignTests {
    public static void runAll() {
        System.out.println("CampaignTests:");
        testCreateCampaign();
        testAddFunds();
    }

    public static void testCreateCampaign() {
        Campaign c = new Campaign("Trees");
        assertEquals("Trees", c.getName(), "Campaign name should be 'Trees'");
        assertEquals(0, c.getTotalRaised(), "Initial funds should be 0");
    }

    public static void testAddFunds() {
        Campaign c = new Campaign("FoodDrive");
        c.addDonation(300);
        assertEquals(300, c.getTotalRaised(), "Total raised should be 300");
    }
}
