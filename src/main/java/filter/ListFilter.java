package filter;

import entity.Postcard;
import presentation.TagInfo;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListFilter extends Filter<Postcard, List<String>, List<String>>
{
    public ListFilter(Function<Postcard, List<String>> field, Comparator<TagInfo> comparator)
    {
        super(field, comparator);
    }
    
    @Override
    protected Set<String> getSet(List<Postcard> postcardList)
    {
        return postcardList.stream()
                .flatMap(p -> this.getField().apply(p).stream())
                .collect(Collectors.toSet());
    }

    @Override
    protected Set<String> getSubSet(List<Postcard> postcardList, String field)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
