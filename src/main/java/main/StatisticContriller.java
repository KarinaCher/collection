package main;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StatisticContriller
{
    
    
    @RequestMapping(value = "/statistics")
    public String statistics(Model model)
    {
        Map<String, Integer> senders = new HashMap();
        return "statistics";
    }
    
}
