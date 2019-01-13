package resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import json.Postcard;

public class TagResource
{
    public static final String ALL = "All";
    
    private static Map<String, Integer> tags = new HashMap();
    private static List<Postcard> postcardList;
    
    public TagResource(List<Postcard> postcardList)
    {
        this.postcardList = postcardList;
    }
    
    public Map<String, Integer> getTags()
    {
        if (tags.isEmpty())
        {
            tags.put(ALL, 0);
            for (Postcard postcard : postcardList)
            {
                tags.put(postcard.getCountry(), getItemCount(tags, postcard.getCountry()) + 1);
                tags.put(postcard.getSender(), getItemCount(tags, postcard.getSender()) + 1);
                tags.put(ALL, tags.get(ALL) + 2);
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
