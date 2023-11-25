package com.example.collectibles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String displayHome(Model model) {
        return "index";
        // View resolver  will resolve index to index.html. The ThymeleafViewResolver class does that.
        // resolve views = resolve content + data
        // Spring is flexible in terms of view tech
        // works with 2 interfaces View (preps the data before sending to specific render)
        // ViewResolver responsible for mapping views with the name. For example Index --> Index.html
        // InternalViewResolver to resolve JSP
        // FreeMakerViewResolver to resolve free marker templating engine
        // BeanNameViewResolver to customize view resolving
        // ThymeleafViewResolver -- we use it in this project. Thanks to dependency injection, we don't have to explicitly define them.

    }

    @GetMapping("/getCharacter/{charname}")
    public String getCharacter(@PathVariable("charname") String charName) {
        return "/characters/" + charName;
    }
}
