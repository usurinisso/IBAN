package com.validator.controller;

import com.validator.service.FileValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileValidationController {

    @Autowired
    private FileValidate fileValidationService;


    @RequestMapping( value = { "/upload" }, method = RequestMethod.GET )
    public ModelAndView validate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload");
        return modelAndView;
    }

    @RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
    public ModelAndView validateFile(@RequestParam(name = "path") String path) {
        ModelAndView modelAndView = new ModelAndView();
        if(fileValidationService.validateFile(path) && path != "")
        {
            modelAndView.addObject("msg","Given path is valid, your output file should be ready in the same path");
        } else if (path != "") modelAndView.addObject("msg","Ooops, something went wrong! Check path and file inputs" +
                ", or the file might already exist");
        modelAndView.setViewName("upload");
        return modelAndView;
    }
}
