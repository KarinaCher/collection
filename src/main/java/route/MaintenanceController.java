package route;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import request.PostcardReq;

import java.time.format.DateTimeFormatter;

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
        model.addAttribute("postcard", new PostcardReq());

        return "maintenance";
    }

    @PostMapping(
            path = "/maintenance")
    public String update(@ModelAttribute("postcard") PostcardReq postcardReq,
                         Model model) {

        model.addAttribute("countries", getMap("countryMap"));
        model.addAttribute("senders", getMap("senderMap", SENDERS_INCLUDE));
        model.addAttribute("tags", getMap("tagMap"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        System.out.println("generate ID: " + formatter.format(postcardReq.getDateReceived()) + postcardReq.getCountry());
        System.out.println("dateSent: " + postcardReq.getDateSent());
        System.out.println("dateReceived: " + postcardReq.getDateReceived());
        System.out.println("country: " + postcardReq.getCountry());
        System.out.println("city: " + postcardReq.getCity());
        System.out.println("sender: " + postcardReq.getSenders());
        System.out.println("width: " + postcardReq.getWidth());
        System.out.println("height: " + postcardReq.getHeight());
        System.out.println("tags: " + postcardReq.getTags());
        System.out.println("descr: " + postcardReq.getDescription());

//        File file = new File(image.getOriginalFilename());
//        try {
//            image.transferTo(file);
//            System.out.println("path" + file.getAbsolutePath());
//        } catch (IOException ex) {
//            ex.printStackTrace(); // TODO
//        }
        return "maintenance";
    }

}
