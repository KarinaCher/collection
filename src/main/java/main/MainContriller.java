package main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainContriller
{
    @RequestMapping("/")
    public String index() {
        return "main";
    }
    
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
