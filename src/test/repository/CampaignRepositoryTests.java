package test.repository;

import interfaces.CampaignRepositoryInterface;
import model.Campaign;

import java.util.*;

import static test.TestRunner.assertEquals;

public class CampaignRepositoryTests {

    public static void runAll() {
        System.out.println("CampaignRepositoryTests:");
        testSaveAndLoadAllCampaigns();
        testDeleteCampaign();
        testDeleteAllCampaigns();
    }

    public static void testSaveAndLoadAllCampaigns() {
        InMemoryCampaignRepo repo = new InMemoryCampaignRepo();
        repo.saveAllCampaigns(List.of(new Campaign("C1"), new Campaign("C2")));
        List<Campaign> loaded = repo.loadAllCampaigns();
        assertEquals(2, loaded.size(), "Saved and loaded 2 campaigns");
    }

    public static void testDeleteCampaign() {
        InMemoryCampaignRepo repo = new InMemoryCampaignRepo();
        repo.saveAllCampaigns(List.of(new Campaign("C1"), new Campaign("C2")));
        repo.deleteCampaign("C1");
        assertEquals(1, repo.loadAllCampaigns().size(), "Deleted one campaign");
    }

    public static void testDeleteAllCampaigns() {
        InMemoryCampaignRepo repo = new InMemoryCampaignRepo();
        repo.saveAllCampaigns(List.of(new Campaign("X"), new Campaign("Y")));
        repo.deleteAllCampaigns();
        assertEquals(0, repo.loadAllCampaigns().size(), "Deleted all campaigns");
    }

    static class InMemoryCampaignRepo implements CampaignRepositoryInterface {
        private final Map<String, Campaign> store = new HashMap<>();

        public List<Campaign> loadAllCampaigns() {
            return new ArrayList<>(store.values());
        }

        public void saveAllCampaigns(List<Campaign> campaigns) {
            store.clear();
            for (Campaign c : campaigns) store.put(c.getName(), c);
        }

        public void deleteCampaign(String name) {
            store.remove(name);
        }

        public void deleteAllCampaigns() {
            store.clear();
        }
    }
}
