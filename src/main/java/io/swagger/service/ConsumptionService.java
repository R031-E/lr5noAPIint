package io.swagger.service;

import io.swagger.model.Consumption;
import io.swagger.repository.ConsumptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;

    public Consumption saveConsumption(Consumption consumption){
       return consumptionRepository.save(consumption);
    }

    public List<Consumption> getConsumptions() {
        return consumptionRepository.getAllConsumptions();
    }

    public Consumption getConsumptionById(String date) {
        return consumptionRepository.findById(date);
    }

    public String deleteConsumption(String date) {
        consumptionRepository.delete(date);
        return "product removed !! " + date;
    }

    public Consumption updateProduct(Consumption consumption) {
        return consumptionRepository.update(consumption);
    }
}
