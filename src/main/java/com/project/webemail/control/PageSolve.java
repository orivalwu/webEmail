package com.project.webemail.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:wzh
 * @Description:
 * @Date:Createed in 2020/6/26 10:03
 **/
@Controller
public class PageSolve {
    @RequestMapping("/login")
    public String getPage(){return "Login";}

    @RequestMapping("/main")
    public String getMain(){return "main";}

    @RequestMapping("/write")
    public String getTemp(){return "write";}

    @RequestMapping("/reading")
    public String getReading(){return "read";}

    @RequestMapping("/register")
    public String getRegister(){return "register";}
}
