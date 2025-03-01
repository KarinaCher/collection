package filter;

import entity.Postcard;
import presentation.TagInfo;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringFilter extends Filter<Postcard, String, String>
{
    public StringFilter(Function<Postcard, String> field, Comparator<TagInfo> comparator)
    {
        super(field, comparator);
    }
    
    public StringFilter(Function<Postcard, String> field, 
                        Function<Postcard, String> subfield, 
                        Comparator<TagInfo> comparator,
                        Comparator<TagInfo> subComparator)
    {
        super(field, subfield, comparator, subComparator);
    }
    
    @Override
    protected Set<String> getSet(List<Postcard> postcardList)
    {
        return postcardList.stream()
                .map(this.getField())
                .collect(Collectors.toSet());
    }

    @Override
    protected Set<String> getSubSet(List<Postcard> postcardList, String field)
    {
        return postcardList.stream()
                .filter(postcard -> field.equals(getField().apply(postcard)))
                .filter(postcard -> getSubfield().apply(postcard) != null)
                .map(getSubfield())
                .collect(Collectors.toSet());
    }
}