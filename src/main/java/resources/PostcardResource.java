package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import entity.Postcard;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import static main.OverviewContriller.ITEMS_PER_PAGE;
import org.jboss.logging.Logger;

public class PostcardResource
{
    public static final Logger LOG = Logger.getLogger(PostcardResource.class);
    private static List<Postcard> postcardList = new ArrayList<>();
    private static List<Postcard> postcardListOther = new ArrayList<>();
    
    private static final Comparator BY_DATE = (Comparator<Postcard>) (Postcard p1, Postcard p2) -> 
    {
        Date date2 = p2.getDateReceived() == null ? p2.getDateSent() : p2.getDateReceived();
        Date date1 = p1.getDateReceived() == null ? p1.getDateSent() : p1.getDateReceived();
        return date2.compareTo(date1);
    };
    
    private static final Comparator BY_ID = (Comparator<Postcard>) (Postcard p1, Postcard p2) -> 
    {
        return p2.getId().compareTo(p1.getId());
    };
    
    public static List<Postcard> getList()
    {
        if (postcardList.isEmpty())
        {
            postcardList.addAll(readTsv("/Postcard collection - 1980x.tsv"));
            postcardList.addAll(readTsv("/Postcard collection - 1990x.tsv"));
            postcardList.addAll(readTsv("/Postcard collection - 2000x.tsv"));
            postcardList.addAll(readTsv("/Postcard collection - 2010x.tsv"));
            postcardList.addAll(readTsv("/Postcard collection - 2019.tsv"));
            postcardList.sort(BY_DATE);
        }
        return postcardList;
    }
    
    public static List<Postcard> getOtherList()
    {
        if (postcardListOther.isEmpty())
        {
            postcardListOther.addAll(readTsv("/Postcard collection - other.tsv"));
            postcardListOther.forEach(postcard -> postcard.setMine(false));
            postcardListOther.sort(BY_ID);
        }
        return postcardListOther;
    }
    
    public static List<Postcard> getList(int page, boolean isOther)
    {
        return getPage(page, isOther ? getOtherList() : getList());
    }
    
    public static List<Postcard> getList(int page)
    {
        return getPage(page, getList());
    }
    
    public static List<Postcard> getListWithTag(String tagName, int page)
    {
        return getPage(page, getListWithTag(tagName));
    }
    
    public static List<Postcard> getPage(int page, List<Postcard> list)
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
        for (Postcard postcard : getOtherList())
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
                    || (postcard.getCity() != null && postcard.getCity().equals(tagName))
                    || postcard.getSenders().contains(tagName)
                    || postcard.getTags().contains(tagName)
                    || postcard.getYear().equals(tagName))
            {
                result.add(postcard);
            }
        }
        
        result.sort(BY_DATE);
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
                
                if (data[0].isEmpty() || "ID".equals(data[0]))
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
                    postcard.setDateReceived(Date.from(
                                LocalDate.parse(data[3], formatter)
                                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                }
                else {
                    LOG.warn("data id " + postcard.getId());
                }
                if (!data[4].isEmpty() && !data[5].isEmpty())
                {
                    postcard.setHeight(Integer.parseInt(data[4]));
                    postcard.setWidth(Integer.parseInt(data[5]));
                }
                postcard.setCountry(data[6]);
                postcard.setCity(data[7]);
                readMultiValues(data[8], postcard.getSenders());
                if (data.length > 9)
                {
                    String descr = data[9];
                    descr = descr.replaceAll("\\[([a-zA-Z\\s]+)\\|([a-zA-Z\\s:/\\.]+)\\]", "<a href=\"$2\" target=\"_blank\">$1</a>");
                    postcard.setDescription(descr);
                }
                if (data.length > 10)
                {
                    readMultiValues(data[10], postcard.getTags());
                }
                if (data.length > 11 && !data[11].isEmpty())
                {
                    int postcardCount = Integer.parseInt(data[11]);
                    for (int i = 2; i <= postcardCount; i++) 
                    {
                        postcard.getImages().add(data[1].replaceAll("1.jpg", i + ".jpg"));
                    }
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
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    private static void readMultiValues(String field, List<String> variableList)
    {
        String[] tags = field.split(", ");
        Arrays.stream(tags)
                .filter(tag -> !tag.isEmpty())
                .forEach(tag -> variableList.add(tag));
    }
    
}
