package uz.shuhratbozorov.appatmservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shuhratbozorov.appatmservice.entity.BankomatSet;
import uz.shuhratbozorov.appatmservice.entity.enums.RoleName;
import uz.shuhratbozorov.appatmservice.payload.ApiResponse;
import uz.shuhratbozorov.appatmservice.repository.BankomatSetRepository;

import java.util.List;

import static uz.shuhratbozorov.appatmservice.utils.CommonUtils.checkAuthority;

@Service
public class BankomatSetService {

    @Autowired
    BankomatSetRepository bankomatSetRepository;

    public ApiResponse getAllBanknoteInfoByBankomatId(Integer bId) {
        if (checkAuthority(RoleName.ROLE_DIRECTOR)) {
            return new ApiResponse("You don't have the authority", false);
        }
        List<BankomatSet> bankomatSetList = bankomatSetRepository.findAllByBankomatId(bId);
        return new ApiResponse("Banknotes info",true,bankomatSetList);

    }

}
