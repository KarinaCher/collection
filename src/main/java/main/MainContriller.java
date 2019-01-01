package main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
        
        try
        {
            ObjectMapper mapper = new ObjectMapper(); 
            File from = Paths.get(ClassLoader.getSystemResource("postcards.json").toURI()).toFile(); 
            TypeReference<List<Postcard>> typeRef = new TypeReference<List<Postcard>>() {};

            list = mapper.readValue(from, typeRef); 
        } catch (IOException | URISyntaxException ex)
        {
            Logger.getLogger(MainContriller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
