package io.swagger.api;

import io.swagger.model.Consumption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.threeten.bp.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.service.ConsumptionService;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-17T23:14:48.983476182Z[GMT]")
@Controller
public class ConsumptionApiController{

    private static final Logger log = LoggerFactory.getLogger(ConsumptionApiController.class);

    // Добавьте хранилище данных в памяти
    @Autowired
    ConsumptionService consumptionService;

    @ModelAttribute("note")
    public Consumption createNewConsumption() {
        return new Consumption();
    }

    @GetMapping(value = "/consumptionlist")
    public ModelAndView consumptionGet(Principal user) {
        if (user == null) throw new ForbiddenException();
        ModelAndView mav = new ModelAndView();
        mav.addObject("consumptions", consumptionService.findAll());
        mav.addObject("note", createNewConsumption());
        mav.setViewName("consumption");
        return mav;
    }

    @PostMapping(value = "/consumption/new")
    public ResponseEntity<?> consumptionPost(Principal user, @ModelAttribute Consumption consumptionobj, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return new ResponseEntity<>("redirect:/consumptionlist/",HttpStatus.EXPECTATION_FAILED);
        }
        if (user == null) throw new ForbiddenException();
        System.out.println(consumptionobj.getDate());
        System.out.println(consumptionobj.getColdWater());
        if (consumptionobj.getColdWater() < 0 || consumptionobj.getHotWater() < 0 || consumptionobj.getDayEnergy() < 0 || consumptionobj.getNightEnergy() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (consumptionService.findById(consumptionobj.getDate()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            consumptionService.addConsumption(consumptionobj.getDate(), consumptionobj.getColdWater(), consumptionobj.getHotWater(), consumptionobj.getDayEnergy(), consumptionobj.getNightEnergy());
            return new ResponseEntity<>("redirect:/consumptionlist/", HttpStatus.CREATED);
        }
    }
}
