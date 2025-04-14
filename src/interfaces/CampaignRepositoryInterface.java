package interfaces;

import model.Campaign;
import java.util.List;

public interface CampaignRepositoryInterface {
    List<Campaign> loadAllCampaigns();
    void saveAllCampaigns(List<Campaign> campaigns);
    void deleteCampaign(String campaignName);
}
