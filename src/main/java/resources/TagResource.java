package resources;

import java.util.Comparator;
import java.util.List;
import entity.Postcard;
import entity.TagInfo;
import java.util.ArrayList;

public class TagResource
{
    private static List<TagInfo> tagsBySender = new ArrayList<>();
    private static List<TagInfo> tagsByCountry = new ArrayList<>();
    private static List<TagInfo> tags = new ArrayList<>();
    private static List<Postcard> postcardList;
    
    private final static Comparator COUNT_DESC = 
            (Comparator<TagInfo>) (TagInfo o1, TagInfo o2) -> o1.getCount().compareTo(o2.getCount()) * -1;
    
    public TagResource(List<Postcard> postcardList)
    {
        this.postcardList = postcardList;
    }
    
    public List<TagInfo> getTagsBySender()
    {
        if (tagsBySender.isEmpty())
        {
            postcardList.forEach((postcard) ->
            {
                updateTagCount(postcard.getSender(), tagsBySender);
            });
            tagsBySender.sort(COUNT_DESC);
        }
        
        return tagsBySender;
    }
    
    public List<TagInfo> getTagsByCountry()
    {
        if (tagsByCountry.isEmpty())
        {
            postcardList.forEach((postcard) ->
            {
                updateTagCount(postcard.getCountry(), tagsByCountry);
            });
            tagsByCountry.sort(COUNT_DESC);
        }
        
        return tagsByCountry;
    }
    
    public List<TagInfo> getTags()
    {
        if (tags.isEmpty())
        {
            postcardList.forEach((postcard) ->
            {
                postcard.getTags().forEach((tag) ->
                {
                    updateTagCount(tag, tags);
                });
            });
            tags.sort(COUNT_DESC);
        }
        
        return tags;
    }

    private void updateTagCount(String tagName, List<TagInfo> tags)
    {
        for (TagInfo tagInfo : tags)
        {
            if (tagInfo.getName().equals(tagName))
            {
                tagInfo.setCount(tagInfo.getCount() + 1);
                return;
            }
        }
        
        tags.add(new TagInfo(tagName, 1));
    }
}
