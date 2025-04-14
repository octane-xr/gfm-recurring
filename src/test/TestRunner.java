package test;

import test.model.DonorTests;
import test.model.CampaignTests;
import test.model.DonationTests;

import test.service.DonorServiceTests;
import test.service.CampaignServiceTests;
import test.service.DonationServiceTests;

import test.repository.DonorRepositoryTests;
import test.repository.CampaignRepositoryTests;
import test.repository.DonationRepositoryTests;

import test.controller.CommandControllerTests;

public final class TestRunner {
    private TestRunner() {
        //private builder
    }

    public static void main(final String[] args) {
        System.out.println("Running tests...\n");

        // Model
        DonorTests.runAll();
        System.out.println();
        CampaignTests.runAll();
        System.out.println();
        DonationTests.runAll();

        // Service
        System.out.println();
        DonorServiceTests.runAll();
        System.out.println();
        CampaignServiceTests.runAll();
        System.out.println();
        DonationServiceTests.runAll();

        // Repository
        System.out.println();
        DonorRepositoryTests.runAll();
        System.out.println();
        CampaignRepositoryTests.runAll();
        System.out.println();
        DonationRepositoryTests.runAll();

        // Controller
        System.out.println();
        CommandControllerTests.runAll();
        System.out.println();

        System.out.println("\nAll tests finished.");
    }

    public static void assertEquals(final Object expected,
                                    final Object actual,
                                    final String message) {
        if ((expected == null && actual != null)
                || (expected != null && !expected.equals(actual))) {
            System.err.println("FAIL: " + message);
            System.err.println("   Expected: " + expected);
            System.err.println("   Actual:   " + actual);
        } else {
            System.out.println("PASS: " + message);
        }
    }
}
