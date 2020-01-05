package filter;

import presentation.TagInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import resources.PostcardResource;

public abstract class Filter<E, T, K>
{
    Function<E, T> field;
    Function<E, K> subfield;
    
    Comparator<TagInfo> comparator;

    public Filter(Function<E, T> field, Comparator<TagInfo> comparator)
    {
        this.field = field;
        this.comparator = comparator;
    }
    
    public Filter(Function<E, T> field, Function<E, K> subfield, Comparator<TagInfo> comparator)
    {
        this.field = field;
        this.subfield = subfield;
        this.comparator = comparator;
    }

    public Function<E, T> getField()
    {
        return field;
    }

    public Function<E, K> getSubfield()
    {
        return subfield;
    }

    public Comparator<TagInfo> getComparator()
    {
        return comparator;
    }
    
    protected abstract Set<String> getSet(List<E> postcardList);
    
    protected abstract Set<String> getSubSet(List<E> postcardList, String field);
    
    public List<TagInfo> getList(List<E> postcardList)
    {
        List<TagInfo> result = new ArrayList();
        Set<String> fieldValues = getSet(postcardList);
            
        fieldValues.stream()
                .forEach(field -> 
                {
                    TagInfo tagInfo = createTagInfo(field);
                    
                    if (getSubfield() != null) 
                    {
                        getSubSet(postcardList, field).stream()
                                .forEach(subField -> tagInfo.getList().add(createTagInfo(subField)));
                    }

                    result.add(tagInfo);
                });

        result.forEach(value -> value.getList().sort(getComparator()));
        result.sort(this.getComparator());
        
        return result;
    }
    
    private TagInfo createTagInfo(String field)
    {
        return new TagInfo(field, PostcardResource.getListWithTag(field).size());
    }
}
