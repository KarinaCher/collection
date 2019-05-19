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
    
    @RequestMapping("/other/page/{page}")
    public String otherPostcards(@PathVariable String page, Model model) 
    {
        int pageNum = getPage(page);
        
        final List<Postcard> list = PostcardResource.getList(pageNum, true);
        addAttributes(model, pageNum, list, PostcardResource.getOtherList().size());
        model.addAttribute("parentPath", "/other");
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
                PostcardResource.getPage(pageNum, list), 
                list.size());
        model.addAttribute("itemId", tagName);
        model.addAttribute("navPath", "tag");
        
        return "overview";
    }
    
    @RequestMapping(value = "/country/{countryId}/page/{page}", method = RequestMethod.GET)
    public String getBycountry(@PathVariable String countryId, 
            @PathVariable String page,
            Model model) 
    {
        // TODO validate input.
        int pageNum = getPage(page);
        
        List<Postcard> list = countryId == null
                ? PostcardResource.getList()
                : PostcardResource.getListWithTag(countryId);
        
        addAttributes(model, pageNum, 
                PostcardResource.getPage(pageNum, list), 
                list.size());
        model.addAttribute("itemId", countryId);
        model.addAttribute("navPath", "country");
        
        return "overview";
    }
    
    @RequestMapping(value = "/city/{city}/page/{page}", method = RequestMethod.GET)
    public String getByCity(@PathVariable String city, 
            @PathVariable String page,
            Model model) 
    {
        // TODO validate input.
        int pageNum = getPage(page);
        
        List<Postcard> list = city == null
                ? PostcardResource.getList()
                : PostcardResource.getListWithTag(city);
        
        addAttributes(model, pageNum, 
                PostcardResource.getPage(pageNum, list), 
                list.size());
        model.addAttribute("itemId", city);
        model.addAttribute("navPath", "city");
        
        return "overview";
    }
    
    @RequestMapping(value = "/postcard/{id}", method = RequestMethod.GET)
    public String postcard(@PathVariable String id, Model model)
    {
        Postcard item = PostcardResource.getById(id);
        model.addAttribute("postcard", item);
        model.addAttribute("count", PostcardResource.getList().size());
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
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("list", list);
        model.addAttribute("pages", (int) (fullListSize / ITEMS_PER_PAGE) + 1);
        model.addAttribute("count", fullListSize);
    }
}
