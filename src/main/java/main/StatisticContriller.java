package main;

import entity.Postcard;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import resources.PostcardResource;
import resources.TagResource;

@Controller
public class StatisticContriller
{
    @RequestMapping(value = "/statistics")
    public String statistics(Model model)
    {
        final List<Postcard> list = PostcardResource.getList();
        final TagResource tagResource = new TagResource();
        model.addAttribute("tagsBySender", tagResource.getTagsBySender(list));
        model.addAttribute("tagsByCountry", tagResource.getTagsByCountry(list));
        model.addAttribute("tags", tagResource.getTags(list));
        return "statistics";
    }
    
}
