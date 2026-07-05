package filter;

import entity.Postcard;
import presentation.TagInfo;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringListFilter extends Filter<Postcard, String, List<String>>
{
    public StringListFilter(Function<Postcard, String> field,
                            Function<Postcard, List<String>> subField,
                            Comparator<TagInfo> comparator,
                            Comparator<TagInfo> subComparator)
    {
        super(field, subField, comparator, subComparator);
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
                .flatMap(postcard -> subfield.apply(postcard).stream())
                .collect(Collectors.toSet());
    }
}