package seb.iban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StringValidationController {
    @RequestMapping( value = { "/", "/home" }, method = RequestMethod.GET )
    public ModelAndView validate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("validation");
        return modelAndView;
    }
}
