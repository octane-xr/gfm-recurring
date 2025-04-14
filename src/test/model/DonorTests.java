package test.model;

import model.Donor;

import static test.TestRunner.assertEquals;

public final class DonorTests {
    private DonorTests() {
        //private constructor
    }

    public static void runAll() {
        System.out.println("DonorTests:");
        testCreateDonor();
        testAddDonation();
        testCanDonate();
        testAverageDonation();
    }

    public static void testCreateDonor() {
        Donor d = new Donor("Greg", 1000);
        assertEquals("Greg", d.getName(), "Donor name should be 'Greg'");
        assertEquals(1000, d.getMonthlyLimit(), "Donor limit should be 1000");
    }

    public static void testAddDonation() {
        Donor d = new Donor("Ana", 1000);
        d.addDonation(200);
        assertEquals(200, d.getTotalDonated(), "Total donated should be 200");
    }

    public static void testCanDonate() {
        Donor d = new Donor("Leo", 500);
        d.addDonation(400);
        assertEquals(true,
                d.canDonate(100),
                "Should allow 100 donation");
        assertEquals(false,
                d.canDonate(101),
                "Should reject donation above limit");
    }

    public static void testAverageDonation() {
        Donor d = new Donor("Mia", 1000);
        d.addDonation(100);
        d.addDonation(300);
        assertEquals(200,
                d.getAverageDonation(),
                "Average donation should be 200");
    }
}
