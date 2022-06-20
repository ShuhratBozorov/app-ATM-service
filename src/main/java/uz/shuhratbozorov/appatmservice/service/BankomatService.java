package uz.shuhratbozorov.appatmservice.service;

import uz.shuhratbozorov.appatmservice.payload.ApiResponse;
import uz.shuhratbozorov.appatmservice.payload.BankomatDto;
import uz.shuhratbozorov.appatmservice.payload.ClientMoneyDto;
import uz.shuhratbozorov.appatmservice.payload.MoneyDto;

public interface BankomatService {

    ApiResponse add(BankomatDto bankomatDto);

    ApiResponse fill(MoneyDto moneyDto);

    ApiResponse withdraw(ClientMoneyDto clientMoneyDto);

    ApiResponse replenishCard(MoneyDto moneyDto);
}
