package test.model;

import model.Donation;
import static test.TestRunner.assertEquals;

public class DonationTests {
    public static void runAll() {
        System.out.println("DonationTests:");
        testCreateDonation();
    }

    public static void testCreateDonation() {
        Donation d = new Donation("Greg", "Trees", 150);
        assertEquals("Greg", d.getDonorName(), "Donor name should be 'Greg'");
        assertEquals("Trees", d.getCampaignName(), "Campaign name should be 'Trees'");
        assertEquals(150, d.getAmountDonated(), "Donation amount should be 150");
    }
}
