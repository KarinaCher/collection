package main;

import entity.Postcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import resources.PostcardOtherResource;
import resources.PostcardResource;

import java.util.List;

import static util.InputValidator.isValidFilter;
import static util.ListUtil.*;
import static util.PostcardHelper.BY_DATE;

@Controller
public class OverviewController {
    public static final int ITEMS_PER_PAGE = 36;
    private static final int FIRST_PAGE = 1;

    @Autowired
    private PostcardResource resource;
    @Autowired
    private PostcardOtherResource otherResource;

    @GetMapping("/")
    public String overview(Model model) {
        addAttributes(model,
                FIRST_PAGE,
                getPage(FIRST_PAGE, resource.getList()),
                resource.getCount());
        return "overview";
    }

    @GetMapping("/other/page/{page}")
    public String otherPostcards(@PathVariable String page, Model model) {
        int pageNum = currentPage(page);

        addAttributes(model,
                pageNum,
                getPage(pageNum, otherResource.getList()),
                otherResource.getCount());

        model.addAttribute("parentPath", "/other");
        return "overview";
    }

    @GetMapping("/page/{page}")
    public String overviewPage(@PathVariable String page, Model model) {
        int pageNum = currentPage(page);

        addAttributes(model, pageNum,
                getPage(pageNum, resource.getList()),
                resource.getCount());

        return "overview";
    }

    @GetMapping("/tag/{tagName}/page/{page}")
    public String tag(@PathVariable String tagName,
                      @PathVariable String page,
                      Model model) {
        int pageNum = currentPage(page);

        List<Postcard> list = tagName == null
                ? resource.getList()
                : resource.getListWithTag(tagName, BY_DATE);

        addAttributes(model, pageNum,
                getPage(pageNum, list),
                list.size());

        model.addAttribute("itemId", tagName);
        model.addAttribute("navPath", "tag");

        return "overview";
    }

    @GetMapping("/country/{countryId}/page/{page}")
    public String getBycountry(@PathVariable String countryId,
                               @PathVariable String page,
                               Model model) {
        int pageNum = currentPage(page);

        List<Postcard> list = countryId == null
                ? resource.getList()
                : resource.getListWithTag(countryId, BY_DATE);

        addAttributes(model, pageNum,
                getPage(pageNum, list),
                list.size());
        model.addAttribute("itemId", countryId);
        model.addAttribute("navPath", "country");

        return "overview";
    }

    @GetMapping("/city/{city}/page/{page}")
    public String getByCity(@PathVariable String city,
                            @PathVariable String page,
                            Model model) {
        int pageNum = currentPage(page);

        List<Postcard> list = city == null
                ? resource.getList()
                : resource.getListWithTag(city, BY_DATE);

        addAttributes(model, pageNum,
                getPage(pageNum, list),
                list.size());
        model.addAttribute("itemId", city);
        model.addAttribute("navPath", "city");

        return "overview";
    }

    @GetMapping(value = {"/postcard/{id}/{filter}", "/postcard/{id}"})
    public String postcard(@PathVariable String id,
                           @PathVariable(required = false) String filter,
                           Model model) {
        Postcard item;
        Postcard prev;
        Postcard next;
        List<Postcard> list;
        boolean hasFilter = false;

        if (isValidFilter(filter)) {
            list = resource.getListWithTag(filter, BY_DATE);
            item = getById(id, list);
            prev = getPrev(item, list);
            next = getNext(item, list);
            hasFilter = true;

        } else {
            item = getById(id,
                    resource.getList(),
                    otherResource.getList());
            prev = getPrev(item,
                    resource.getList(),
                    otherResource.getList());
            next = getNext(item,
                    resource.getList(),
                    otherResource.getList());
        }

        model.addAttribute("itemId", filter);
        model.addAttribute("postcard", item);
        model.addAttribute("prevPostcard", prev);
        model.addAttribute("nextPostcard", next);
        return "postcard";
    }

    private int currentPage(String page) {
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
