package main;

import entity.Postcard;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import resources.PostcardResource;
import resources.TagResource;
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
        final TagResource tagResource = new TagResource();
        switch (param)
        {
            case "sender":
                model.addAttribute("tagsBySender", tagResource.getTagsBySender(list));
                break;
                
            case "country":
                model.addAttribute("tagsByCountry", tagResource.getTagsByCountry(list));
                break;
                
            case "tag":
                model.addAttribute("tags", tagResource.getTags(list));
                model.addAttribute("tagsByName", tagResource.getTagsByName(list));
                break;
                
            case "size":
                model.addAttribute("sizeMap", (new SizeMap(list)).get());
                break;
                
            case "year":
                model.addAttribute("tagsByYear", tagResource.getTagsByYear(list));
                break;
        }
        model.addAttribute("p", param);

        return "statistics";
    }

}
