package test.repository;

import interfaces.DonorRepositoryInterface;
import model.Donor;

import java.util.*;

import static test.TestRunner.assertEquals;

public final class DonorRepositoryTests {
    private DonorRepositoryTests() {
        //private constructor
    }

    public static void runAll() {
        System.out.println("DonorRepositoryTests:");
        testSaveAndLoadAllDonors();
        testDeleteDonor();
        testDeleteAllDonors();
    }

    public static void testSaveAndLoadAllDonors() {
        InMemoryDonorRepo repo = new InMemoryDonorRepo();
        repo.saveAllDonors(List.of(new Donor("Greg",
                        1000),
                new Donor("Ana",
                        500)));
        List<Donor> loaded = repo.loadAllDonors();
        assertEquals(2,
                loaded.size(),
                "Saved and loaded 2 donors");
    }

    public static void testDeleteDonor() {
        InMemoryDonorRepo repo = new InMemoryDonorRepo();
        repo.saveAllDonors(List.of(new Donor("Greg",
                        1000),
                new Donor("Ana",
                        500)));
        repo.deleteDonor("Greg");
        assertEquals(1,
                repo.loadAllDonors().size(),
                "Deleted one donor");
    }

    public static void testDeleteAllDonors() {
        InMemoryDonorRepo repo = new InMemoryDonorRepo();
        repo.saveAllDonors(List.of(new Donor("A", 100), new Donor("B", 200)));
        repo.deleteAllDonors();
        assertEquals(0, repo.loadAllDonors().size(), "Deleted all donors");
    }

    static class InMemoryDonorRepo implements DonorRepositoryInterface {
        private final Map<String, Donor> store = new HashMap<>();

        public List<Donor> loadAllDonors() {
            return new ArrayList<>(store.values());
        }

        public void saveAllDonors(final List<Donor> donors) {
            store.clear();
            for (Donor d : donors) {
                store.put(d.getName(), d);
            }
        }

        public void deleteAllDonors() {
            store.clear();
        }

        public void deleteDonor(final String name) {
            store.remove(name);
        }
    }
}
