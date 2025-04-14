package test.service;

import model.Donor;
import service.DonorService;
import interfaces.DonorRepositoryInterface;

import java.util.*;

import static test.TestRunner.assertEquals;

public class DonorServiceTests {

    public static void runAll() {
        System.out.println("DonorServiceTests:");
        testAddAndGetDonor();
        testDeleteDonor();
    }

    static DonorService createTestService() {
        return new DonorService(new InMemoryDonorRepo());
    }

    public static void testAddAndGetDonor() {
        DonorService service = createTestService();
        service.addDonor("Greg", 500);
        Donor d = service.get("Greg");
        assertEquals("Greg", d.getName(), "Donor created and retrieved");
        assertEquals(500, d.getMonthlyLimit(), "Donor limit set");
    }

    public static void testDeleteDonor() {
        DonorService service = createTestService();
        service.addDonor("Ana", 1000);
        service.deleteDonor("Ana");
        assertEquals(null, service.get("Ana"), "Donor deleted");
    }


    // Fake repository (no file IO)
    static class InMemoryDonorRepo implements DonorRepositoryInterface {
        private final Map<String, Donor> store = new HashMap<>();

        public List<Donor> loadAllDonors() {
            return new ArrayList<>(store.values());
        }

        public void saveAllDonors(List<Donor> donors) {
            store.clear();
            for (Donor d : donors) store.put(d.getName(), d);
        }

        public void deleteAllDonors() {
            store.clear();
        }

        public void deleteDonor(String name) {
            store.remove(name);
        }
    }
}
