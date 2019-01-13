package resources;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.Postcard;
import main.MainContriller;

public class PostcardResource
{
    private static List<Postcard> postcardList = Collections.EMPTY_LIST;
    
    public static List<Postcard> getList()
    {
        if (postcardList.isEmpty())
        {
            postcardList = read("postcards2018.json");
        }
        return postcardList;
    }
    
    public static List<Postcard> getListWithTag(String tagName)
    {
        List<Postcard> result = new ArrayList();
        if (tagName.isEmpty())
        {
            return getList();
        }
        
        for (Postcard postcard : getList())
        {
            if (postcard.getCountry().equals(tagName)
                    || postcard.getSender().equals(tagName))
            {
                result.add(postcard);
            }
        }
        
        return result;
    }
    
    private static List<Postcard> read(String sourceName)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            File from = Paths.get(ClassLoader.getSystemResource(sourceName).toURI()).toFile();
            TypeReference<List<Postcard>> typeRef = new TypeReference<List<Postcard>>()
            {
            };
            return mapper.readValue(from, typeRef);
        } catch (IOException | URISyntaxException ex)
        {
            Logger.getLogger(MainContriller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }
    
}
