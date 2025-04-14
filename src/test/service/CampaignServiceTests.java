package test.service;

import interfaces.CampaignRepositoryInterface;
import model.Campaign;
import service.CampaignService;

import java.util.*;

import static test.TestRunner.assertEquals;

public final class CampaignServiceTests {
    private CampaignServiceTests() {
        //private constructor
    }

    public static void runAll() {
        System.out.println("CampaignServiceTests:");
        testAddCampaign();
        testDeleteCampaign();
    }

    static CampaignService createTestService() {
        return new CampaignService(new InMemoryCampaignRepo());
    }

    public static void testAddCampaign() {
        CampaignService service = createTestService();
        service.addCampaign("SaveCats");
        Campaign c = service.get("SaveCats");
        assertEquals("SaveCats", c.getName(), "Campaign created");
    }

    public static void testDeleteCampaign() {
        CampaignService service = createTestService();
        service.addCampaign("X");
        service.deleteCampaign("X");
        assertEquals(null, service.get("X"), "Campaign deleted");
    }


    static class InMemoryCampaignRepo implements CampaignRepositoryInterface {
        private final Map<String, Campaign> store = new HashMap<>();

        @Override
        public List<Campaign> loadAllCampaigns() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void saveAllCampaigns(final List<Campaign> campaigns) {
            store.clear();
            for (Campaign c : campaigns) {
                store.put(c.getName(), c);
            }
        }

        @Override
        public void deleteCampaign(final String campaignName) {
            store.remove(campaignName);
        }

    }
}
