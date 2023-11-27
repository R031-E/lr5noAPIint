package io.swagger.repository;

import io.swagger.model.Consumption;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface ConsumptionRepository extends CrudRepository<Consumption, String> {
    List<Consumption> findAll();
    Consumption findByDate(String date);
    Consumption save(Consumption obj);
    @Transactional
    void deleteByDate(String date);


}
