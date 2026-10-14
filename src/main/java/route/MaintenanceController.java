package route;

import entity.Postcard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import request.PostcardReq;
import resources.PostcardResource;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.APPEND;
import static util.ListUtil.getById;
import static util.PostcardConverter.convert;
import static util.ResourceBundleHelper.SENDERS_INCLUDE;
import static util.ResourceBundleHelper.getMap;

@Controller
public class MaintenanceController {
    private static final Logger log = LoggerFactory.getLogger(MaintenanceController.class);

    @Autowired
    private PostcardResource resource;

    @Value("${sourceFile}")
    private String fileName;

    @GetMapping("/maintenance/{id}")
    public String maintenance(@PathVariable String id, Model model) {
        model.addAttribute("id", id);

        if (id.isEmpty()) {
            model.addAttribute("postcard", new PostcardReq());
        } else {
            Postcard postcard = getById(id, resource.getList());
            model.addAttribute("postcard", convert(postcard));
        }

        model.addAttribute("countries", getMap("countryMap"));
        model.addAttribute("senders", getMap("senderMap", SENDERS_INCLUDE));
        model.addAttribute("tags", getMap("tagMap"));

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
        LocalDate foDateSent = postcardReq.getDateSent();
        String dateSent = foDateSent == null ?
                null
                : formatter.format(foDateSent);
        String dateReceived = formatter.format(postcardReq.getDateReceived());
        String senders = postcardReq.getSenders().stream().collect(Collectors.joining(","));
        String description = postcardReq.getDescription().isBlank()
                ? null
                : postcardReq.getDescription();
        String tags = postcardReq.getTags().isEmpty()
                ? null
                : postcardReq.getTags().stream().collect(Collectors.joining(","));
        String originCountry = postcardReq.getOriginCountry() == null
                ? null
                : postcardReq.getOriginCountry();

        // create a row
        postcardRow.append("\n") // start new row
                .append(id).append("\t")
                .append("image_name.jpg").append("\t");
        appendIfNotNull(postcardRow, dateSent);

        postcardRow.append(dateReceived).append("\t")
                .append(postcardReq.getHeight()).append("\t")
                .append(postcardReq.getWidth()).append("\t")
                .append(postcardReq.getCountry()).append("\t")
                .append(postcardReq.getCity()).append("\t")
                .append(senders).append("\t");

        appendIfNotNull(postcardRow, description);
        appendIfNotNull(postcardRow, tags);
        appendIfNotNull(postcardRow, null); //postcard count
        postcardRow.append(originCountry).append("\t");

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

    private void appendIfNotNull(StringBuilder sb, String value) {
        if (value != null) {
            sb.append(value).append("\t");
        } else {
            sb.append("\t");
        }
    }
}
