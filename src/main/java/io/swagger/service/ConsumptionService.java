package io.swagger.service;

import io.swagger.model.Consumption;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class ConsumptionService {

    HashMap<String, Consumption> dataStorage = new HashMap<>();

    public Consumption findById(String id){
        return (dataStorage.containsKey(id)) ? dataStorage.get(id) : null;
    }

    public List<Consumption> findAll(){
        List<Consumption> dataList = new ArrayList<>(dataStorage.values());
        return dataList;
    }

    public void addConsumption(String date, float coldWater, float hotWater, float dayEnergy, float nightEnergy){
        if (dataStorage.containsKey(date)) return;
        Consumption obj = new Consumption();
        obj.setDate(date);
        obj.setColdWater(coldWater);
        obj.setHotWater(hotWater);
        obj.setDayEnergy(dayEnergy);
        obj.setNightEnergy(nightEnergy);
        dataStorage.put(date, obj);
    }

    public boolean deleteConsumption(String id){
        if (dataStorage.containsKey(id)){
            dataStorage.remove(id);
            return true;
        }
        else return false;
    }

    public boolean updateConsumption(String oldDate, String newDate, float coldWater, float hotWater, float dayEnergy, float nightEnergy){
        if (dataStorage.containsKey(oldDate)) {
            Consumption obj = new Consumption();
            obj.setDate(newDate);
            obj.setColdWater(coldWater);
            obj.setHotWater(hotWater);
            obj.setDayEnergy(dayEnergy);
            obj.setNightEnergy(nightEnergy);
            dataStorage.remove(oldDate);
            dataStorage.put(newDate, obj);
            return true;
        }
        else return false;
    }

}
