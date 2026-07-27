package route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import request.PostcardReq;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.APPEND;
import static util.ResourceBundleHelper.SENDERS_INCLUDE;
import static util.ResourceBundleHelper.getMap;

@Controller
public class MaintenanceController {
    private static final Logger log = LoggerFactory.getLogger(MaintenanceController.class);
    public static final String EMPTY_VALUE = "";

    @Value("${sourceFile}")
    private String fileName;

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
        // return lists
        model.addAttribute("countries", getMap("countryMap"));
        model.addAttribute("senders", getMap("senderMap", SENDERS_INCLUDE));
        model.addAttribute("tags", getMap("tagMap"));

        // define formatters
        DateTimeFormatter formatterId = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // prepare data
        String id = formatterId.format(postcardReq.getDateReceived()) + postcardReq.getCountry();
        StringBuilder postcardRow = new StringBuilder();
        String dateSent = formatter.format(postcardReq.getDateSent()); // TODO empty
        String dateReceived = formatter.format(postcardReq.getDateReceived());
        String senders = postcardReq.getSenders().stream().collect(Collectors.joining(","));
        String description = postcardReq.getDescription().isBlank()
                ? EMPTY_VALUE
                : postcardReq.getDescription();
        String tags = postcardReq.getTags().isEmpty()
                ? EMPTY_VALUE
                : postcardReq.getTags().stream().collect(Collectors.joining(","));
        String originCountry = postcardReq.getOriginCountry() == null
                ? EMPTY_VALUE
                : postcardReq.getOriginCountry();

        // create a row
        postcardRow.append("\n") // start new row
                .append(id).append("\t")
                .append("image_name.jpg").append("\t")
                .append(dateSent).append("\t")
                .append(dateReceived).append("\t")
                .append(postcardReq.getHeight()).append("\t")
                .append(postcardReq.getWidth()).append("\t")
                .append(postcardReq.getCountry()).append("\t")
                .append(postcardReq.getCity()).append("\t")
                .append(senders).append("\t")
                .append(description).append("\t")
                .append(tags).append("\t")
                .append(EMPTY_VALUE).append("\t") //postcard count
                .append(originCountry).append("\t");

        try {
            OutputStream outputStream = Files.newOutputStream(Paths.get(fileName), APPEND);
            outputStream.write(postcardRow.toString().getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

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
