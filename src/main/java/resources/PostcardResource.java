package resources;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Postcard;
import entity.TagInfo;
import java.util.Comparator;
import main.OverviewContriller;

public class PostcardResource
{
    private static List<Postcard> postcardList = Collections.EMPTY_LIST;
    
    private static Comparator byDate = new Comparator<Postcard>() 
    {
        @Override
        public int compare(Postcard p1, Postcard p2) {
            Date date1 = p1.getDateSent() != null ? p1.getDateSent() : p1.getDateReceived();
            Date date2 = p2.getDateSent() != null ? p2.getDateSent() : p2.getDateReceived();
            if (date1 == null)
            {
                return -1;
            }
            else if (date2 == null)
            {
                return -1;
            }
            else
            {
                return date2.compareTo(date1);
            }
        }
    };
    
    public static List<Postcard> getList()
    {
        if (postcardList.isEmpty())
        {
            postcardList = readTsv("postcards.tsv");
        }
        postcardList.sort(byDate);
        return postcardList;
    }
    
    public static Postcard getById(String id)
    {
        if (id == null)
        {
            return null;
        }
        for (Postcard postcard : getList())
        {
            if (id.equals(postcard.getId()))
            {
                return postcard;
            }
        }
        return null;
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
                    || postcard.getSender().equals(tagName)
                    || postcard.getTags().contains(tagName))
            {
                result.add(postcard);
            }
        }
        
        result.sort(byDate);
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
            Logger.getLogger(OverviewContriller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }

    private static List<Postcard> readTsv(String file)
    {
        List<Postcard> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        
        File from = null;
        try
        {
            from = Paths.get(ClassLoader.getSystemResource(file).toURI()).toFile();
        } catch (URISyntaxException ex)
        {
            Logger.getLogger(PostcardResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader(from))) 
        {
            for(String line; (line = br.readLine()) != null; ) 
            {
                String[] data = line.split("\t");
                
                if (data[0].isEmpty())
                {
                    continue;
                }

                Postcard postcard = new Postcard();
                postcard.setId(data[0]);
                postcard.getImages().add(data[1]);
                if (!data[2].isEmpty())
                {
                    postcard.setDateSent(Date.from(
                            LocalDate.parse(data[2], formatter)
                            .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                }
                if (!data[3].isEmpty())
                {
                    postcard.setDateSent(Date.from(
                            LocalDate.parse(data[3], formatter)
                            .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                }
                if (!data[4].isEmpty() && !data[5].isEmpty())
                {
                    postcard.setHeight(Integer.parseInt(data[4]));
                    postcard.setWidth(Integer.parseInt(data[5]));
                }
                postcard.setCountry(data[6]);
                postcard.setCity(data[7]);
                postcard.setSender(data[8]);
                if (data.length > 9)
                {
                    postcard.setDescription(data[9]);
                }
                if (data.length > 10)
                {
                    postcard.getTags().add(data[10]);
                }
                list.add(postcard);
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
        return list;
    }
    
}
