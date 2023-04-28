package presentation;

import entity.Postcard;
import filter.Filter;
import filter.ListFilter;
import filter.StringFilter;
import resources.Resource;

import java.util.List;

import static resources.TagResource.*;

public enum Filters {
    TAG_BY_COUNT(new ListFilter(Postcard::getTags, BY_COUNT.reversed())),
    TAG_BY_NAME(new ListFilter(Postcard::getTags, BY_NAME)),
    YEAR_BY_NAME(new StringFilter(Postcard::getYear, BY_NAME.reversed())),
    SENDERS_BY_COUNT(new ListFilter(Postcard::getSenders, BY_COUNT.reversed())),
    COUNTRY_CITY_BY_COUNT(new StringFilter(Postcard::getCountry, Postcard::getCity, BY_COUNT.reversed())),
    ;

    private final Filter filter;

    Filters(Filter filter) {
        this.filter = filter;
    }

    public List<TagInfo> getList(Resource list) {
        return this.filter.getList(list);
    }

}
