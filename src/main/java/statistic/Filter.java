package statistic;

import entity.Postcard;
import entity.TagInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import resources.PostcardResource;

public abstract class Filter<T, K>
{
    Function<Postcard, T> field;
    Function<Postcard, K> subfield;
    
    Comparator<TagInfo> comparator;

    public Filter(Function<Postcard, T> field, Comparator<TagInfo> comparator)
    {
        this.field = field;
        this.comparator = comparator;
    }
    
    public Filter(Function<Postcard, T> field, Function<Postcard, K> subfield, Comparator<TagInfo> comparator)
    {
        this.field = field;
        this.subfield = subfield;
        this.comparator = comparator;
    }

    public Function<Postcard, T> getField()
    {
        return field;
    }

    public Function<Postcard, K> getSubfield()
    {
        return subfield;
    }

    public Comparator<TagInfo> getComparator()
    {
        return comparator;
    }
    
//    public List<TagInfo> getList(List<Postcard> postcardList)
//    {
//        List<TagInfo> result = new ArrayList();
//        Set<String> set = getSet(postcardList);
//
//        set.stream()
//                .forEach(value -> result.add(createTagInfo(value)));
//
//        result.sort(this.getComparator());
//        return result;
//    }
    
    protected abstract Set<String> getSet(List<Postcard> postcardList);
    
    protected abstract Set<String> getSubSet(List<Postcard> postcardList, String field);
    
    public List<TagInfo> getList(List<Postcard> postcardList)
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
