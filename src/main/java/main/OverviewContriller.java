package main;

import java.util.List;
import entity.Postcard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import resources.PostcardResource;

@Controller
public class OverviewContriller
{
    public static final int ITEMS_PER_PAGE = 24;
    
    @RequestMapping("/")
    public String overview(Model model) 
    {
        final List<Postcard> list = PostcardResource.getList(1);
        addAttributes(model, 1, list, PostcardResource.getList().size());
        return "overview";
    }
    
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public String overviewPage(@PathVariable String page, Model model) 
    {
        int pageNum = getPage(page);
        
        final List<Postcard> list = PostcardResource.getList(pageNum);
        
        addAttributes(model, pageNum, 
                list, 
                PostcardResource.getList().size());
        return "overview";
    }
    
    @RequestMapping(value = "/tag/{tagName}/page/{page}", method = RequestMethod.GET)
    public String tag(@PathVariable String tagName, 
            @PathVariable String page,
            Model model) 
    {
        // TODO validate input.
        int pageNum = getPage(page);
        
        List<Postcard> list = tagName == null
                ? PostcardResource.getList()
                : PostcardResource.getListWithTag(tagName);
        
        addAttributes(model, pageNum, 
                PostcardResource.substringList(pageNum, list), 
                list.size());
        model.addAttribute("tag", tagName);
        
        return "overview";
    }
    
    @RequestMapping(value = "/postcard/{id}", method = RequestMethod.GET)
    public String postcard(@PathVariable String id, Model model)
    {
        Postcard item = PostcardResource.getById(id);
        model.addAttribute("postcard", item);
        return "postcard";
    }

    private int getPage(String page)
    {
        int pageNum = 1;
        try
        {
            pageNum = Integer.parseInt(page);
        }
        catch (NumberFormatException ex) { }
        return pageNum;
    }

    private void addAttributes(Model model, int pageNum, List<Postcard> list, int fullListSize)
    {
        model.addAttribute("currenctPage", pageNum);
        model.addAttribute("list", list);
        model.addAttribute("pages", (int) (fullListSize / ITEMS_PER_PAGE));
    }
}
