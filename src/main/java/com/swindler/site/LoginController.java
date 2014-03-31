package com.swindler.site;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Artyom_Borkowsky on 3/24/14.
 */
@Controller
public class LoginController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginGet(ModelMap model,
                           @RequestParam(value = "error",required = false) boolean error) {
        if(error){
            model.addAttribute("error", "true");
        }
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(ModelMap model) {
        model.addAttribute("message","site");
        return "hello";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(ModelMap model){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(ModelMap model){
        return "login";
    }
}
