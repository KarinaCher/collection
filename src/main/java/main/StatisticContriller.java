package main;

import entity.Postcard;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import resources.PostcardResource;
import static statistic.Filters.*;
import statistic.SizeMap;

@Controller
public class StatisticContriller
{
    
    @RequestMapping(value = "/statistics/{param}")
    public String statistics(
            @PathVariable String param, 
            Model model)
    {
        final List<Postcard> list = PostcardResource.getList();
        switch (param)
        {
            case "sender":
                model.addAttribute("tagsBySender", SENDERS_BY_COUNT.getList(list));
                break;
                
            case "country":
                model.addAttribute("tagsByCountry", COUNTRY_CITY_BY_COUNT.getList(list));
                break;
                
            case "tag":
                model.addAttribute("tags", TAG_BY_COUNT.getList(list));
                model.addAttribute("tagsByName", TAG_BY_NAME.getList(list));
                break;
                
            case "size":
                model.addAttribute("sizeMap", (new SizeMap(list)).get());
                break;
                
            case "year":
                model.addAttribute("tagsByYear", YEAR_BY_NAME.getList(list));
                break;
        }
        model.addAttribute("p", param);

        return "statistics";
    }

}
