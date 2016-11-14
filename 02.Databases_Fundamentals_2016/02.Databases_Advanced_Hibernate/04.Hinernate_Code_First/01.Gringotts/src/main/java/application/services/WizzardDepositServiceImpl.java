package application.services;

import application.entities.WizzardDeposit;
import application.repositories.WizzardDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WizzardDepositServiceImpl implements WizzardDepositService {

    private final WizzardDepositRepository wizzardDepositRepository;

    @Autowired
    public WizzardDepositServiceImpl(WizzardDepositRepository wizzardDepositRepository) {
        this.wizzardDepositRepository = wizzardDepositRepository;
    }

    @Override
    public void persist(WizzardDeposit wizzardDeposit) {
        this.wizzardDepositRepository.saveAndFlush(wizzardDeposit);
    }
}
