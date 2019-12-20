package resources;

import java.util.Comparator;
import java.util.List;
import entity.Postcard;
import entity.TagInfo;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.YEAR;

public class TagResource
{
    private static List<TagInfo> tagsBySender = new ArrayList<>();
    private static List<TagInfo> tagsByCountry = new ArrayList<>();
    private static List<TagInfo> tagsByYear = new ArrayList<>();
    private static List<TagInfo> tags = new ArrayList<>();
    private static List<TagInfo> tagsByName = new ArrayList<>();
    
    private final static Comparator<TagInfo> BY_COUNT_DESC = 
            (TagInfo o1, TagInfo o2) -> o1.getCount().compareTo(o2.getCount()) * -1;
    
    private final static Comparator<TagInfo> BY_NAME = Comparator.comparing(TagInfo::getName);
    
    public List<TagInfo> getTagsBySender(List<Postcard> postcardList)
    {
        if (tagsBySender.isEmpty())
        {
            postcardList.forEach((postcard) ->
            {
                postcard.getSenders().forEach((sender) ->
                {
                    updateTagCount(sender, tagsBySender);
                });
            });
            tagsBySender.sort(BY_COUNT_DESC);
        }
        
        return tagsBySender;
    }
    
    public List<TagInfo> getTagsByCountry(List<Postcard> postcardList)
    {
        if (tagsByCountry.isEmpty())
        {
            postcardList.forEach((postcard) ->
            {
                TagInfo country = updateTagCount(postcard.getCountry(), tagsByCountry);
                updateTagCount(postcard.getCity(), country.getList());
            });
            
            tagsByCountry.forEach((country) ->
            {
                country.getList().sort(BY_COUNT_DESC);
            });
            tagsByCountry.sort(BY_COUNT_DESC);
        }
        
        return tagsByCountry;
    }
    
    public List<TagInfo> getTagsByName(List<Postcard> postcardList) {
        return sortTags(tagsByName, postcardList, BY_NAME);
    }
    
    public List<TagInfo> getTags(List<Postcard> postcardList)
    {
        return sortTags(tags, postcardList, BY_COUNT_DESC);
    }
    
    public List<TagInfo> sortTags(List<TagInfo> listToSort, List<Postcard> postcardList, Comparator sortParam)
    {
        if (listToSort.isEmpty())
        {
            postcardList.forEach((postcard) ->
            {
                postcard.getTags().forEach((tag) ->
                {
                    updateTagCount(tag, listToSort);
                });
            });
            listToSort.sort(sortParam);
        }
        
        return listToSort;
    }

    private TagInfo updateTagCount(String tagName, List<TagInfo> tags)
    {
        for (TagInfo tagInfo : tags)
        {
            if (tagInfo.getName().equals(tagName))
            {
                tagInfo.setCount(tagInfo.getCount() + 1);
                return tagInfo;
            }
        }
        
        TagInfo result = new TagInfo(tagName, 1);
        tags.add(result);
        return result;
    }

    public List<TagInfo> getTagsByYear(List<Postcard> postcardList)
    {
        if (tagsByYear.isEmpty())
        {
            postcardList.forEach((postcard) ->
            {
                updateTagCount(postcard.getYear(), tagsByYear);
            });

            tagsByYear.sort(BY_NAME);
            tagsByYear.remove("1900");
        }
        
        return tagsByYear;
    }
}
