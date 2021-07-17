package resources;

import presentation.Filters;
import presentation.TagInfo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagResource {
    private Resource resource;
    private static Map<String, List<TagInfo>> cache = new HashMap<>();

    public final static Comparator<TagInfo> BY_COUNT = Comparator.comparing(TagInfo::getCount);
    public final static Comparator<TagInfo> BY_NAME = Comparator.comparing(TagInfo::getName);

    public TagResource(Resource resource) {
        this.resource = resource;
    }

    public List<TagInfo> getBy(Filters filter) {
        if (cache.get(filter.name()) == null) {
            cache.put(filter.name(), filter.getList(resource));
        }

        return cache.get(filter.name());
    }
}
