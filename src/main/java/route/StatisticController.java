package route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import presentation.TagInfo;
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
                model.addAttribute("tagsByYear", tagResource.getBy(YEAR_MONTH_BY_NAME));
                break;

            case "sender":
                model.addAttribute("tagsBySender", tagResource.getBy(SENDERS_BY_COUNT));
                break;

            case "yearSender":
            default:
                List<TagInfo> list = tagResource.getBy(YEAR_SENDERS_BY_COUNT);
                int maxCount = list.stream().mapToInt(TagInfo::getCount).max().orElse(0);
                model.addAttribute("tagsBySender", list);
                model.addAttribute("maxCount", maxCount);
                break;
        }
        model.addAttribute("p", param);

        return "statistics";
    }
}
