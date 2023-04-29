package main;

import entity.Postcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import resources.PostcardResource;
import resources.TagResource;

import java.util.List;

import static presentation.Filters.*;

@Controller
public class StatisticController {
    @Autowired
    private PostcardResource resource;

    @RequestMapping(value = "/statistics/{param}")
    public String statistics(
            @PathVariable String param,
            Model model) {
        final List<Postcard> list = resource.getList();
        final TagResource tagResource = new TagResource(resource);
        switch (param) {
            case "country":
                model.addAttribute("tagsByCountry", tagResource.getBy(COUNTRY_CITY_BY_COUNT));
                break;

            case "tag":
                model.addAttribute("tags", tagResource.getBy(TAG_BY_COUNT));
                model.addAttribute("tagsByName", tagResource.getBy(TAG_BY_NAME));
                break;

            case "year":
                model.addAttribute("tagsByYear", tagResource.getBy(YEAR_BY_NAME));
                break;

            case "sender":
            default:
                model.addAttribute("tagsBySender", tagResource.getBy(SENDERS_BY_COUNT));
                break;
        }
        model.addAttribute("p", param);

        return "statistics";
    }
}
