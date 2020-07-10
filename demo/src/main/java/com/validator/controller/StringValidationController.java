package com.validator.controller;

import com.validator.service.IbanIsValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StringValidationController {

    @Autowired private IbanIsValid validationService;

    @RequestMapping( value = { "/" }, method = RequestMethod.GET )
    public ModelAndView validate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("validation");
        return modelAndView;
    }

    @Controller
    public class stringValidate {
        @RequestMapping(value = { "/" }, method = RequestMethod.POST)
        public ModelAndView validate(@RequestParam(name = "iban") String iban) {
            ModelAndView modelAndView = new ModelAndView();
            if(validationService.isValid(iban))
            {
                modelAndView.addObject("msg","IBAN is valid");
            } else if (iban != "") {
                modelAndView.addObject("msg", "IBAN is invalid");
            }
            modelAndView.setViewName("validation");
            return modelAndView;
        }
    }
}
