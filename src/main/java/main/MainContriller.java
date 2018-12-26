package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.Postcard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainContriller
{
    @RequestMapping("/")
    public String index(Model model) {
        List<Postcard> list = read();
        model.addAttribute("list", list);
        return "main";
    }
    
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
    
    private List<Postcard> read()
    {
        List<Postcard> list = new ArrayList();
        
        //read json file data to String
        byte[] jsonData;
        try
        {
            jsonData = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("postcards.json").toURI()));
            

            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            //convert json string to object
            Postcard postcard = objectMapper.readValue(jsonData, Postcard.class);

            list.add(postcard);
        } catch (IOException ex)
        {
            Logger.getLogger(MainContriller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex)
        {
            Logger.getLogger(MainContriller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
