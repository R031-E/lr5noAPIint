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


    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView generateFormData() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("consumptions", consumptionService.getConsumptions());
        mav.addObject("consumption", new Consumption());
        mav.setViewName("consumption");
        return mav;
    }

    @RequestMapping(value = "/consumptionlist", method = RequestMethod.GET)
    public ModelAndView consumptionGet(Principal user) {
        if (user == null) throw new ForbiddenException();
        ModelAndView mav = new ModelAndView();
        mav.addObject("consumptions", consumptionService.getConsumptions());
        mav.addObject("consumption", new Consumption());
        mav.setViewName("consumption");
        return mav;
    }

    @RequestMapping(value = "/consumption/new", method = RequestMethod.POST)
    public String consumptionPost(Principal user,@Valid @ModelAttribute("consumption") Consumption consumptionobj, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "redirect:/";
        }
        if (user == null) throw new ForbiddenException();
        System.out.println(consumptionobj.getDate());
        System.out.println(consumptionobj.getColdWater());
        if (consumptionobj.getColdWater() < 0 || consumptionobj.getHotWater() < 0 || consumptionobj.getDayEnergy() < 0 || consumptionobj.getNightEnergy() < 0) {
            return "redirect:/";
        }
        if (consumptionService.getConsumptionById(consumptionobj.getDate()) != null) {
            return "redirect:/";
        } else {
            consumptionService.saveConsumption(consumptionobj);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/consumption/delete/{date}", method = RequestMethod.DELETE)
    public String deleteConsumption(Principal user, @PathVariable("date") String date){
        if (user == null) throw new ForbiddenException();
        consumptionService.deleteConsumption(date);
        return "redirect:/";
    }
}
