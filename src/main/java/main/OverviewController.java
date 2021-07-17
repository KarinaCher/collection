package main;

import entity.Postcard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import resources.PostcardOtherResource;
import resources.PostcardResource;
import resources.ResourceContainer;
import util.ListUtil;

import java.util.List;

import static util.PostcardHelper.BY_DATE;

@Controller
public class OverviewController {
    public static final int ITEMS_PER_PAGE = 36;
    private static final int FIRST_PAGE = 1;

    private static ResourceContainer container = new ResourceContainer();
    private static final ListUtil<Postcard, String> util = new ListUtil<>();

    @RequestMapping("/")
    public String overview(Model model) {
        PostcardResource postcardResource = container.getResource();

        addAttributes(model,
                FIRST_PAGE,
                util.getPage(FIRST_PAGE, postcardResource.getList()),
                postcardResource.getCount());
        return "overview";
    }

    @RequestMapping("/other/page/{page}")
    public String otherPostcards(@PathVariable String page, Model model) {
        int pageNum = getPage(page);
        PostcardOtherResource resource = container.getOtherResource();

        addAttributes(model,
                pageNum,
                util.getPage(pageNum, resource.getList()),
                resource.getCount());

        model.addAttribute("parentPath", "/other");
        return "overview";
    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public String overviewPage(@PathVariable String page, Model model) {
        int pageNum = getPage(page);

        PostcardResource resource = container.getResource();

        addAttributes(model, pageNum,
                util.getPage(pageNum, resource.getList()),
                resource.getCount());

        return "overview";
    }

    @RequestMapping(value = "/tag/{tagName}/page/{page}", method = RequestMethod.GET)
    public String tag(@PathVariable String tagName,
                      @PathVariable String page,
                      Model model) {
        int pageNum = getPage(page);

        PostcardResource resource = container.getResource();

        List<Postcard> list = tagName == null
                ? resource.getList()
                : resource.getListWithTag(tagName, BY_DATE);

        addAttributes(model, pageNum,
                util.getPage(pageNum, list),
                list.size());

        model.addAttribute("itemId", tagName);
        model.addAttribute("navPath", "tag");

        return "overview";
    }

    @RequestMapping(value = "/country/{countryId}/page/{page}", method = RequestMethod.GET)
    public String getBycountry(@PathVariable String countryId,
                               @PathVariable String page,
                               Model model) {
        int pageNum = getPage(page);

        PostcardResource resource = container.getResource();

        List<Postcard> list = countryId == null
                ? resource.getList()
                : resource.getListWithTag(countryId, BY_DATE);

        addAttributes(model, pageNum,
                util.getPage(pageNum, list),
                list.size());
        model.addAttribute("itemId", countryId);
        model.addAttribute("navPath", "country");

        return "overview";
    }

    @RequestMapping(value = "/city/{city}/page/{page}", method = RequestMethod.GET)
    public String getByCity(@PathVariable String city,
                            @PathVariable String page,
                            Model model) {
        int pageNum = getPage(page);

        PostcardResource resource = container.getResource();

        List<Postcard> list = city == null
                ? resource.getList()
                : resource.getListWithTag(city, BY_DATE);

        addAttributes(model, pageNum,
                util.getPage(pageNum, list),
                list.size());
        model.addAttribute("itemId", city);
        model.addAttribute("navPath", "city");

        return "overview";
    }

    @RequestMapping(value = "/postcard/{id}", method = RequestMethod.GET)
    public String postcard(@PathVariable String id, Model model) {
        Postcard item = (new ListUtil<Postcard, String>())
                .getById(id,
                        container.getResource().getList(),
                        container.getOtherResource().getList());

        model.addAttribute("postcard", item);
        model.addAttribute("count", container.getResource().getList().size()); // TODO is it needed
        return "postcard";
    }

    private int getPage(String page) {
        try {
            return Integer.parseInt(page);
        } catch (NumberFormatException ex) {
            return FIRST_PAGE;
        }
    }

    private void addAttributes(Model model, int pageNum, List<Postcard> list, int fullListSize) {
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("list", list);
        model.addAttribute("pages", (fullListSize / ITEMS_PER_PAGE) + 1);
        model.addAttribute("count", fullListSize);
    }
}
