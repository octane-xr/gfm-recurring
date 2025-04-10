package src.interfaces;

import src.model.Campaign;
import java.util.List;

public interface CampaignRepositoryInterface {
    List<Campaign> loadAllCampaigns();
    void saveAllCampaigns(List<Campaign> campaigns);
}
