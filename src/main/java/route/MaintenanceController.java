package route;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
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

    @PostMapping(
            path = "/update",
            consumes = {MULTIPART_FORM_DATA_VALUE})
    public String update(@RequestPart(name = "dateSent", required = false) String dateSent,
                         @RequestPart(name = "dateReceived", required = false) String dateReceived,
                         @RequestPart(name = "country", required = false) String country,
                         @RequestPart(name = "city", required = false) String city,
                         @RequestPart(name = "sender", required = false) String sender,
                         @RequestPart(name = "width", required = false) String width,
                         @RequestPart(name = "height", required = false) String height,
                         @RequestPart(name = "tag", required = false) String tag,
                         @RequestPart(name = "descr", required = false) String descr,
                         @RequestPart(name = "image1", required = false) MultipartFile image,
                         Model model) {

        System.out.println("dateSent: " + dateSent);
        System.out.println("dateReceived: " + dateReceived);
        System.out.println("country: " + country);
        System.out.println("city: " + city);
        System.out.println("sender: " + sender);
        System.out.println("width: " + width);
        System.out.println("height: " + height);
        System.out.println("tag: " + tag);
        System.out.println("descr: " + descr);

        File file = new File(image.getOriginalFilename());
        try {
            image.transferTo(file);
            System.out.println("path" + file.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace(); // TODO
        }
        return "maintenance";
    }

}
