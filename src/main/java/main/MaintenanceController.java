package main;

import entity.Postcard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static util.ResourceBundleHelper.getMap;

@Controller
public class MaintenanceController {
    @GetMapping("/maintenance/{id}")
    public String maintenance(@PathVariable String id, Model model) {
        model.addAttribute("id", id);

        model.addAttribute("countries", getMap("countryMap"));
        model.addAttribute("senders", getMap("senderMap"));
        model.addAttribute("tags", getMap("tagMap"));

        return "maintenance";
    }

    @PostMapping("/update")
    public String update(@RequestBody Postcard postcard, Model model) {

        System.out.println("postcard: " + postcard.getCountry());
        return "maintenance";
    }

}
