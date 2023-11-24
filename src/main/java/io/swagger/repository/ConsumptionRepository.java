package io.swagger.repository;

import io.swagger.model.Consumption;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ConsumptionRepository {
    private List<Consumption> consumptions = new ArrayList<Consumption>();

    public List<Consumption> getAllConsumptions(){
        return consumptions;
    }

    public Consumption findById(String date){
        for (int i = 0; i < consumptions.size(); i++) {
            if (consumptions.get(i).getDate() == (date)) {
                return consumptions.get(i);
            }
        }
        return null;
    }

    public Consumption save(Consumption cons) {
        Consumption note = new Consumption();
        note.setDate(cons.getDate());
        note.setColdWater(cons.getColdWater());
        note.setHotWater(cons.getHotWater());
        note.setDayEnergy(cons.getDayEnergy());
        note.setNightEnergy(cons.getNightEnergy());
        consumptions.add(note);
        return note;
    }

    public String delete(String date) {
        consumptions.removeIf(x -> x.getDate().equals((date)));
        return null;
    }

    public Consumption update(Consumption consumption) {
        return null;
    }
}
