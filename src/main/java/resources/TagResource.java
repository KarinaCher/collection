package resources;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import json.Postcard;

public class TagResource
{
    private static Map<String, Integer> tags;
    private static Map<String, Integer> tagsBySender;
    private static Map<String, Integer> tagsByCountry;
    private static List<Postcard> postcardList;
    
    private static Comparator comparator = new Comparator<String>() 
    {
        @Override
        public int compare(String o1, String o2) {
            return o1.toLowerCase().compareTo(o2.toLowerCase());
        }
    };
    
    public TagResource(List<Postcard> postcardList)
    {
        this.postcardList = postcardList;
        tags = new TreeMap<>(comparator);
        tagsBySender = new TreeMap<>(comparator);
        tagsByCountry = new TreeMap<>(comparator);
    }
    
    public Map<String, Integer> getTagsBySender()
    {
        if (tagsBySender.isEmpty())
        {
            for (Postcard postcard : postcardList)
            {
                tagsBySender.put(postcard.getSender(), getItemCount(tagsBySender, postcard.getSender()) + 1);
            }
        }
        
        return tagsBySender;
    }
    
    public Map<String, Integer> getTagsByCountry()
    {
        if (tagsByCountry.isEmpty())
        {
            for (Postcard postcard : postcardList)
            {
                tagsByCountry.put(postcard.getCountry(), getItemCount(tagsByCountry, postcard.getCountry()) + 1);
            }
        }
        
        return tagsByCountry;
    }
    
    public Map<String, Integer> getTags()
    {
        if (tags.isEmpty())
        {
            for (Postcard postcard : postcardList)
            {
                for (String tag : postcard.getTags())
                {
                    tags.put(tag, getItemCount(tags, tag) + 1);
                }
            }
        }
        
        return tags;
    }
    
    private Integer getItemCount(Map<String, Integer> tags, String tagName)
    {
        Integer itemCount = tags.get(tagName);
        if (itemCount == null)
        {
            itemCount = 0;
        }
        return itemCount;
    }
}
