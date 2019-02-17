package resources;

import java.util.Comparator;
import java.util.List;
import entity.Postcard;
import entity.TagInfo;
import java.util.ArrayList;

public class TagResource
{
    private static List<TagInfo> tagsBySender;
    private static List<TagInfo> tagsByCountry;
    private static List<Postcard> postcardList;
    
    private static Comparator byCountDesc = new Comparator<TagInfo>() 
    {
        @Override
        public int compare(TagInfo o1, TagInfo o2) {
            return o1.getCount().compareTo(o2.getCount()) * -1;
        }
    };
    
    public TagResource(List<Postcard> postcardList)
    {
        this.postcardList = postcardList;
        tagsBySender = new ArrayList<>();
        tagsByCountry = new ArrayList<>();
    }
    
    public List<TagInfo> getTagsBySender()
    {
        if (tagsBySender.isEmpty())
        {
            for (Postcard postcard : postcardList)
            {
                updateTagCount(postcard.getSender(), tagsBySender);
            }
        }
        
        tagsBySender.sort(byCountDesc);
        return tagsBySender;
    }
    
    public List<TagInfo> getTagsByCountry()
    {
        if (tagsByCountry.isEmpty())
        {
            for (Postcard postcard : postcardList)
            {
                updateTagCount(postcard.getCountry(), tagsByCountry);
            }
        }
        
        tagsByCountry.sort(byCountDesc);
        return tagsByCountry;
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
