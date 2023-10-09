package edu.greenriver.sdev372.adamwinter.spring_project_372.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticRoutes {

    /**
     * Redirects a request to home.html to the default home page of React app
     *
     * @return redirect in the form of a String
     */
    @GetMapping(path = "home.html")
    public String redirectHome(){
        return "redirect:/index.html";
    }
}
