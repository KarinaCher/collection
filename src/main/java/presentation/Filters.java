package presentation;

import entity.Postcard;
import filter.Filter;
import filter.ListFilter;
import filter.StringFilter;
import java.util.List;
import static resources.TagResource.BY_COUNT_DESC;
import static resources.TagResource.BY_NAME;

public enum Filters
{
    TAG_BY_COUNT(new ListFilter(Postcard::getTags, BY_COUNT_DESC)),
    TAG_BY_NAME(new ListFilter(Postcard::getTags, BY_NAME)),
    YEAR_BY_NAME(new StringFilter(Postcard::getYear, BY_NAME.reversed())),
    SENDERS_BY_COUNT(new ListFilter(Postcard::getSenders, BY_COUNT_DESC)),
    COUNTRY_CITY_BY_COUNT(new StringFilter(Postcard::getCountry, Postcard::getCity, BY_COUNT_DESC)),
    ;
    
    private final Filter filter;

    private Filters(Filter filter)
    {
        this.filter = filter;
    }
    
    public List<TagInfo> getList(List<Postcard> list)
    {
        return this.filter.getList(list);
    }
    
}
