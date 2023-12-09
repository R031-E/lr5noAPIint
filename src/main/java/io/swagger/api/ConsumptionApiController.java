package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Consumption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.service.ConsumptionService;
import javax.validation.Valid;
import java.security.Principal;
import io.swagger.service.KafkaProducer;
import io.swagger.service.KafkaConsumer;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-17T23:14:48.983476182Z[GMT]")
@Controller
public class ConsumptionApiController{

    private static final Logger log = LoggerFactory.getLogger(ConsumptionApiController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConsumptionService consumptionService;
    private final KafkaProducer kafkaProducer;
    @Autowired(required = false)
    public ConsumptionApiController(ConsumptionService consumptionService, KafkaProducer kafkaProducer){
        this.consumptionService = consumptionService;
        this.kafkaProducer = kafkaProducer;
    }
    //@Autowired
    //ConsumptionService consumptionService;


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
            if (kafkaProducer != null) {
                String message = convertConsumptionToMessage(consumptionobj);
                kafkaProducer.sendMessage("myTopic", String.valueOf(consumptionobj.getDate()), message);
            }
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/consumption/delete/{date}", method = RequestMethod.DELETE)
    public String deleteConsumption(Principal user, @PathVariable("date") String date){
        if (user == null) throw new ForbiddenException();
        consumptionService.deleteConsumption(date);
        return "redirect:/";
    }

    private String convertConsumptionToMessage(Consumption consumption){
        try{
            return objectMapper.writeValueAsString(consumption);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}

