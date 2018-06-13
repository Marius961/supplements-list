package ua.supplementsList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.supplementsList.models.Supplement;
import ua.supplementsList.services.interfaces.MainService;

@Controller
public class MainController {

    private MainService mainService;

    @Autowired
    private void setService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping(value = {"/", "/{request}"}, method = RequestMethod.GET)
    public ModelAndView getHomePage(@PathVariable(required = false) String request) {
        ModelAndView modelAndView = new ModelAndView();
        if (request != null) {
            modelAndView.addObject("supplements", mainService.searchSupplements(request));
        } else {
            modelAndView.addObject("supplements", mainService.getSupplements());
        }
        modelAndView.addObject("supplement", new Supplement());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/remove-supplement/{id}", method = RequestMethod.GET)
    public String removeSupplement(@PathVariable int id) {
        mainService.removeSupplement(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/add-supplement", method = RequestMethod.POST)
    public String addSupplement(@ModelAttribute Supplement supplement) {
        if (supplement.getId() == 0) {
            mainService.addSupplement(supplement);
        } else {
            mainService.updateSupplement(supplement);
        }
        return "redirect:/";
    }
}
