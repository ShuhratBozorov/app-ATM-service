package uz.shuhratbozorov.appatmservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shuhratbozorov.appatmservice.entity.ReplenishAtmHistory;
import uz.shuhratbozorov.appatmservice.entity.Role;
import uz.shuhratbozorov.appatmservice.entity.enums.RoleName;
import uz.shuhratbozorov.appatmservice.payload.ApiResponse;
import uz.shuhratbozorov.appatmservice.repository.ReplenishAtmHistoryRepository;
import uz.shuhratbozorov.appatmservice.utils.CommonUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static uz.shuhratbozorov.appatmservice.utils.CommonUtils.checkAuthority;

@Service
public class ReplenishAtmHistoryService {
    @Autowired
    ReplenishAtmHistoryRepository replenishAtmHistoryRepository;

    public ApiResponse getReplenishingBankomatHistories(Integer bId) {
        if (checkAuthority(RoleName.ROLE_DIRECTOR)) {
            return new ApiResponse("You don't have the authority", false);
        }

        List<ReplenishAtmHistory> replenishAtmHistoryList = replenishAtmHistoryRepository.findAllByBankomatId(bId);
        return new ApiResponse("Replenishing ATM histories", true, replenishAtmHistoryList);
    }


}
