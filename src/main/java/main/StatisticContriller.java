package main;

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
        final TagResource tagResource = new TagResource(PostcardResource.getList());
        model.addAttribute("tagsBySender", tagResource.getTagsBySender());
        model.addAttribute("tagsByCountry", tagResource.getTagsByCountry());
        model.addAttribute("tags", tagResource.getTags());
        return "statistics";
    }
    
}
