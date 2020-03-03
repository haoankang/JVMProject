package ank.hao.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/test")
    public String test(){
        System.out.println("test");
        return "success";
    }
}
