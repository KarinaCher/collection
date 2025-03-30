package main;

import entity.Postcard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static util.ResourceBundleHelper.SENDERS_INCLUDE;
import static util.ResourceBundleHelper.getMap;

@Controller
public class MaintenanceController {
    @GetMapping("/maintenance/{id}")
    public String maintenance(@PathVariable String id, Model model) {
        model.addAttribute("id", id);

        model.addAttribute("countries", getMap("countryMap"));
        model.addAttribute("senders", getMap("senderMap", SENDERS_INCLUDE));
        model.addAttribute("tags", getMap("tagMap"));

        return "maintenance";
    }

    @PostMapping("/update")
    public String update(@RequestBody Postcard postcard,
                         @RequestParam("image") MultipartFile image,
                         Model model) {

        System.out.println("postcard: " + postcard.getCountry());
        return "maintenance";
    }

}
