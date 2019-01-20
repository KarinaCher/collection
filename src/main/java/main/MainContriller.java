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
public class MainContriller
{
    @RequestMapping("/")
    public String index(Model model) 
    {
        model.addAttribute("list", PostcardResource.getList());
        model.addAttribute("tags", (new TagResource(PostcardResource.getList())).getTags());
        return "main";
    }

    @RequestMapping(value = "/tag/{tagName}", method = RequestMethod.GET)
    public String tag(@PathVariable String tagName, Model model) 
    {
        List<Postcard> postcards = ALL.equals(tagName) 
                ? PostcardResource.getList()
                : PostcardResource.getListWithTag(tagName);
        
        // TODO validate input.
        model.addAttribute("list", postcards);
        model.addAttribute("tags", (new TagResource(PostcardResource.getList())).getTags());
        return "main";
    }
    
    @RequestMapping(value = "/postcard/{id}", method = RequestMethod.GET)
    public String postcard(@PathVariable String id, Model model)
    {
        Postcard item = PostcardResource.getById(id);
        model.addAttribute("postcard", item);
        return "postcard";
    }
    
}
