package main;

import entity.Postcard;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                   
            case "most":
                model.addAttribute("most", getMostPostcards(list));
                break;
        }
        model.addAttribute("p", param);

        return "statistics";
    }
    
    private Map<String, Postcard> getMostPostcards(List<Postcard> postcards)
    {
        Map<String, Postcard> result = new HashMap();
        
        result.put("oldest", postcards.stream()
                .min(Comparator.comparing(Postcard::getDate)).get());
        result.put("latest", postcards.stream()
                .max(Comparator.comparing(Postcard::getDate)).get());
        
        
        result.put("highest", postcards.stream()
                .max(Comparator.comparing(Postcard::getHeight)).get());
        result.put("widest", postcards.stream()
                .max(Comparator.comparing(Postcard::getWidth)).get());
        result.put("biggest", postcards.stream()
                .max(Comparator.comparing(Postcard::getSquare)).get());
        
        result.put("shortest", postcards.stream()
                .filter(p -> p.getHeight() > 0)
                .min(Comparator.comparing(Postcard::getHeight)).get());
        result.put("narrowest", postcards.stream()
                .filter(p -> p.getWidth() > 0)
                .min(Comparator.comparing(Postcard::getWidth)).get());
        result.put("smallest", postcards.stream()
                .filter(p -> p.getSquare() > 0)
                .min(Comparator.comparing(Postcard::getSquare)).get());
        
        
        return result;
    }

}
