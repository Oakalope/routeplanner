package com.example.routeplanner;
import models.PlanModel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/journeyplanner")
public class PlannerController {
    @GetMapping("/")
    public String showPlannerForm(Model model) {
        model.addAttribute("planModel", new PlanModel());
        return "journeyPlan";
    }
    @PostMapping("/processPlan")
    public String processPlan(@Valid PlanModel planModel,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("planModel", planModel);
            return "JourneyPlan";
        }
        else{
            model.addAttribute("planModel", planModel);
            planModel.calculateFastestRoute();
            return "JourneyResult";
        }
    }
}
