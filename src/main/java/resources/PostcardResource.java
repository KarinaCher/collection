package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import entity.Postcard;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import static main.OverviewContriller.ITEMS_PER_PAGE;
import org.jboss.logging.Logger;

public class PostcardResource
{
    public static final Logger LOG = Logger.getLogger(PostcardResource.class);
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
            postcardList = readTsv("/postcards.tsv");
            postcardList.sort(byDate);
        }
        return postcardList;
    }
    
    public static List<Postcard> getList(int page)
    {
        return substringList(page, getList());
    }
    
    public static List<Postcard> getListWithTag(String tagName, int page)
    {
        return substringList(page, getListWithTag(tagName));
    }
    
    public static List<Postcard> substringList(int page, List<Postcard> list)
    {
        List<Postcard> result = new ArrayList();
        int begin = ITEMS_PER_PAGE * (page - 1);
        int end = ITEMS_PER_PAGE * page;
        end = end > list.size() ? list.size() : end;
        
        for (int i = begin; i < end; i++)
        {
            result.add(list.get(i));
        }
        
        return result;
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
    
    private static List<Postcard> readTsv(String file)
    {
        List<Postcard> list = new ArrayList<>();
        Set<String> ids = new HashSet();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        
        InputStream inputStream = PostcardResource.class.getResourceAsStream(file);
        
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) 
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
                if (ids.contains(postcard.getId()))
                {
                    LOG.warn("Dublicate id " + postcard.getId());
                }
                ids.add(postcard.getId());
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
