package main;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainContriller
{
    @RequestMapping("/")
    public String index(Model model) {
        List<String> list = new ArrayList();
        list.add("elem1");
        model.addAttribute("list", list);
        return "main";
    }
    
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
