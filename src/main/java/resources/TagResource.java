package resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import json.Postcard;

public class TagResource
{
    public static final String ALL = "All";
    
    private static Map<String, Integer> tags = new HashMap();
    private static Map<String, Integer> tagsBySender = new HashMap();
    private static Map<String, Integer> tagsByCountry = new HashMap();
    private static List<Postcard> postcardList;
    
    public TagResource(List<Postcard> postcardList)
    {
        this.postcardList = postcardList;
    }
    
    public Map<String, Integer> getTagsBySender()
    {
        if (tagsBySender.isEmpty())
        {
            tagsBySender.put(ALL, 0);
            for (Postcard postcard : postcardList)
            {
                tagsBySender.put(postcard.getSender(), getItemCount(tagsBySender, postcard.getSender()) + 1);
                tagsBySender.put(ALL, tagsBySender.get(ALL) + 1);
            }
        }
        
        return tagsBySender;
    }
    
    public Map<String, Integer> getTagsByCountry()
    {
        if (tagsByCountry.isEmpty())
        {
            tagsByCountry.put(ALL, 0);
            for (Postcard postcard : postcardList)
            {
                tagsByCountry.put(postcard.getCountry(), getItemCount(tagsByCountry, postcard.getCountry()) + 1);
                tagsByCountry.put(ALL, tagsByCountry.get(ALL) + 1);
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
