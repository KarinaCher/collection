package filter;

import presentation.TagInfo;
import resources.Resource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static util.PostcardHelper.BY_DATE;

public abstract class Filter<E, T, K> {
    Function<E, T> field;
    Function<E, K> subfield;

    Comparator<TagInfo> comparator;
    Comparator<TagInfo> subComparator;

    public Filter(Function<E, T> field, Comparator<TagInfo> comparator) {
        this.field = field;
        this.comparator = comparator;
    }

    public Filter(Function<E, T> field, Function<E, K> subfield,
                  Comparator<TagInfo> comparator, Comparator<TagInfo> subComparator) {
        this.field = field;
        this.subfield = subfield;
        this.comparator = comparator;
        this.subComparator = subComparator;
    }

    public Function<E, T> getField() {
        return field;
    }

    public Function<E, K> getSubfield() {
        return subfield;
    }

    private Comparator<TagInfo> getComparator() {
        return comparator;
    }

    private Comparator<TagInfo> getSubComparator() {
        return subComparator;
    }

    protected abstract Set<String> getSet(List<E> postcardList);

    protected abstract Set<String> getSubSet(List<E> postcardList, String field);

    public List<TagInfo> getList(Resource resource) {
        List<TagInfo> result = new ArrayList();
        Set<String> fieldValues = getSet(resource.getList());

        fieldValues.stream()
                .forEach(field ->
                {
                    TagInfo tagInfo = createTagInfo(field, getCountOfTagged(resource, field));

                    if (getSubfield() != null) {
                        Set<String> subSet = getSubSet(resource.getList(), field);
                        subSet.stream()
                                .forEach(subField -> {
                                    tagInfo.getList().add(
                                            createTagInfo(subField, getCountOfTagged(resource, subField)));
                                });
                    }

                    result.add(tagInfo);
                });

        result.forEach(value -> value.getList().sort(this.getSubComparator()));
        result.sort(this.getComparator());

        return result;
    }

    private int getCountOfTagged(Resource resource, String field) {
        return resource.getListWithTag(field, BY_DATE).size();
    }

    private TagInfo createTagInfo(String field, int listSize) {
        return new TagInfo(field, listSize);
    }
}
