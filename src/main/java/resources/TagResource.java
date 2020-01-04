package resources;

import java.util.Comparator;
import entity.TagInfo;

public class TagResource
{
    public final static Comparator<TagInfo> BY_COUNT_DESC = 
            (TagInfo o1, TagInfo o2) -> o1.getCount().compareTo(o2.getCount()) * -1;
    
    public final static Comparator<TagInfo> BY_NAME = Comparator.comparing(TagInfo::getName);

}
