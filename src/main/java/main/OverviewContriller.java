package main;

import java.util.List;
import json.Postcard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import resources.PostcardResource;
import resources.TagResource;
import static resources.TagResource.ALL;

@Controller
public class OverviewContriller
{
    @RequestMapping("/")
    public String overview(Model model) 
    {
        model.addAttribute("list", PostcardResource.getList());
        model.addAttribute("tagsBySender", (new TagResource(PostcardResource.getList())).getTagsBySender());
        model.addAttribute("tagsByCountry", (new TagResource(PostcardResource.getList())).getTagsByCountry());
        model.addAttribute("tags", (new TagResource(PostcardResource.getList())).getTags());
        return "overview";
    }

    @RequestMapping(value = "/tag/{tagName}", method = RequestMethod.GET)
    public String tag(@PathVariable String tagName, Model model) 
    {
        List<Postcard> postcards = ALL.equals(tagName) 
                ? PostcardResource.getList()
                : PostcardResource.getListWithTag(tagName);
        
        // TODO validate input.
        model.addAttribute("list", postcards);
        
        final TagResource tagResource = new TagResource(PostcardResource.getList());
        model.addAttribute("tagsBySender", tagResource.getTagsBySender());
        model.addAttribute("tagsByCountry", tagResource.getTagsByCountry());
        model.addAttribute("tags", (new TagResource(PostcardResource.getList())).getTags());
        return "overview";
    }
    
    @RequestMapping(value = "/postcard/{id}", method = RequestMethod.GET)
    public String postcard(@PathVariable String id, Model model)
    {
        Postcard item = PostcardResource.getById(id);
        model.addAttribute("postcard", item);
        return "postcard";
    }
}
