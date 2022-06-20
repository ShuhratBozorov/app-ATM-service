package uz.shuhratbozorov.appatmservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.shuhratbozorov.appatmservice.entity.enums.AccountTypeName;
import uz.shuhratbozorov.appatmservice.payload.ApiResponse;
import uz.shuhratbozorov.appatmservice.service.AccountHistoryService;
import uz.shuhratbozorov.appatmservice.service.BankomatSetService;
import uz.shuhratbozorov.appatmservice.service.ReplenishAtmHistoryService;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    AccountHistoryService accountHistoryService;

    @Autowired
    ReplenishAtmHistoryService replenishAtmHistoryService;

    @Autowired
    BankomatSetService bankomatSetService;

    @GetMapping("/all")
    public HttpEntity<?> getAllAccountHistory(@RequestParam Integer bankomatId) {
        ApiResponse response = accountHistoryService.getAllAccountHistory(bankomatId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping("/dailyIncomes")
    public HttpEntity<?> getDailyIncomes(@RequestParam Integer bankomatId, @RequestParam String date) {
        ApiResponse response = accountHistoryService.getDailyIncomesOrExpenses(bankomatId, date, AccountTypeName.INCOME);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping("/dailyExpenses")
    public HttpEntity<?> getDailyExpenses(@RequestParam Integer bankomatId, @RequestParam String date) {
        ApiResponse response = accountHistoryService.getDailyIncomesOrExpenses(bankomatId, date, AccountTypeName.EXPENDITURE);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
    @GetMapping("/replenishingAtmHistories")
    public HttpEntity<?> getReplenishingBankomatHistories(@RequestParam Integer bankomatId) {
        ApiResponse response = replenishAtmHistoryService.getReplenishingBankomatHistories(bankomatId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping("/banknotesInfoByBanknoteId")
    public HttpEntity<?> getAllBanknoteInfoByBankomatId(@RequestParam Integer bankomatId) {
        ApiResponse response = bankomatSetService.getAllBanknoteInfoByBankomatId(bankomatId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}
