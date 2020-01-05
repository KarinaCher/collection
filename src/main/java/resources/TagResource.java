package resources;

import entity.Postcard;
import java.util.Comparator;
import java.util.List;
import presentation.TagInfo;
import java.util.HashMap;
import java.util.Map;
import filter.Filters;

public class TagResource
{
    private List<Postcard> postcardList;
    private static Map<String, List<TagInfo>> cache = new HashMap<>();
    
    public final static Comparator<TagInfo> BY_COUNT_DESC = 
            (TagInfo o1, TagInfo o2) -> o1.getCount().compareTo(o2.getCount()) * -1;
    
    public final static Comparator<TagInfo> BY_NAME = Comparator.comparing(TagInfo::getName);

    public TagResource(List<Postcard> postcardList)
    {
        this.postcardList = postcardList;
    }

    public List<TagInfo> getBy(Filters filter)
    {
        if (cache.get(filter.name()) == null)
        {
            cache.put(filter.name(), filter.getList(postcardList));
        }
        
        return cache.get(filter.name());
    }
}
