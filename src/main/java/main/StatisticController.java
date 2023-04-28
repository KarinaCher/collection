package main;

import entity.Postcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import presentation.SizeMap;
import presentation.TopItem;
import resources.PostcardResource;
import resources.TagResource;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
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

            case "size":
                SizeMap sizeMap = new SizeMap(list);
                model.addAttribute("sizeMap", sizeMap.get());
                model.addAttribute("maxCount", sizeMap.getMaxCount());
                model.addAttribute("heights", sizeMap.getHeights());
                model.addAttribute("widths", sizeMap.getWidths());
                break;

            case "year":
                model.addAttribute("tagsByYear", tagResource.getBy(YEAR_BY_NAME));
                break;

            case "most":
                model.addAttribute("most", getMostPostcards(list));
                break;

            case "sender":
            default:
                model.addAttribute("tagsBySender", tagResource.getBy(SENDERS_BY_COUNT));
                break;
        }
        model.addAttribute("p", param);

        return "statistics";
    }

    private List<TopItem> getMostPostcards(List<Postcard> postcards) {
        List<TopItem> result = new ArrayList();

        Postcard oldest = postcards.stream()
                .min(comparing(Postcard::getDate)).get();
        Postcard latest = postcards.stream()
                .max(comparing(Postcard::getDate)).get();

        result.add(new TopItem("oldest", oldest.getDate(), oldest));
        result.add(new TopItem("latest", latest.getDate(), latest));

        Postcard highest = postcards.stream()
                .max(comparing(Postcard::getHeight)).get();
        Postcard shortest = postcards.stream()
                .filter(p -> p.getHeight() > 0)
                .min(comparing(Postcard::getHeight)).get();

        result.add(new TopItem("highest", highest.getHeight(), highest));
        result.add(new TopItem("shortest", shortest.getHeight(), shortest));

        Postcard widest = postcards.stream()
                .max(comparing(Postcard::getWidth)).get();
        Postcard narrowest = postcards.stream()
                .filter(p -> p.getWidth() > 0)
                .min(comparing(Postcard::getWidth)).get();

        result.add(new TopItem("widest", widest.getWidth(), widest));
        result.add(new TopItem("narrowest", narrowest.getWidth(), narrowest));

        Postcard biggest = postcards.stream()
                .max(comparing(Postcard::getSquare)).get();
        Postcard smallest = postcards.stream()
                .filter(p -> p.getSquare() > 0)
                .min(comparing(Postcard::getSquare)).get();

        result.add(new TopItem("biggest", biggest.getSquare(), biggest));
        result.add(new TopItem("smallest", smallest.getSquare(), smallest));

        return result;
    }

}
