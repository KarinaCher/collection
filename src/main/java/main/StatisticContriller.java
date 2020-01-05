package main;

import entity.Postcard;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import resources.PostcardResource;
import resources.TagResource;
import static presentation.Filters.*;
import presentation.SizeMap;

@Controller
public class StatisticContriller
{
    
    @RequestMapping(value = "/statistics/{param}")
    public String statistics(
            @PathVariable String param, 
            Model model)
    {
        final List<Postcard> list = PostcardResource.getList();
        final TagResource tagResource = new TagResource(list);
        switch (param)
        {
            case "sender":
                model.addAttribute("tagsBySender", tagResource.getBy(SENDERS_BY_COUNT));
                break;
                
            case "country":
                model.addAttribute("tagsByCountry", tagResource.getBy(COUNTRY_CITY_BY_COUNT));
                break;
                
            case "tag":
                model.addAttribute("tags", tagResource.getBy(TAG_BY_COUNT));
                model.addAttribute("tagsByName", tagResource.getBy(TAG_BY_NAME));
                break;
                
            case "size":
                model.addAttribute("sizeMap", (new SizeMap(list)).get());
                break;
                
            case "year":
                model.addAttribute("tagsByYear", tagResource.getBy(YEAR_BY_NAME));
                break;
        }
        model.addAttribute("p", param);

        return "statistics";
    }

}
