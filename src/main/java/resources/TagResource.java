package resources;

import java.util.Comparator;
import java.util.List;
import entity.Postcard;
import entity.TagInfo;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class TagResource
{
    private static List<TagInfo> tagsBySender = new ArrayList<>();
    private static List<TagInfo> tagsByCountry = new ArrayList<>();
    private static List<TagInfo> tagsByYear = new ArrayList<>();
    private static List<TagInfo> tags = new ArrayList<>();
    private static List<TagInfo> tagsByName = new ArrayList<>();
    
    private final static Comparator<TagInfo> BY_COUNT_DESC = 
            (TagInfo o1, TagInfo o2) -> o1.getCount().compareTo(o2.getCount()) * -1;
    
    private final static Comparator<TagInfo> BY_NAME = Comparator.comparing(TagInfo::getName);
    
    public List<TagInfo> getTagsBySender(List<Postcard> postcardList)
    {
        if (tagsBySender.isEmpty())
        {
            Set<String> senderSet = postcardList.stream()
                    .flatMap(p -> p.getSenders().stream())
                    .collect(Collectors.toSet());
            
            senderSet.stream()
                    .forEach(sender -> tagsBySender.add(new TagInfo(sender, 
                            PostcardResource.getListWithTag(sender).size()))
                    );
            
            tagsBySender.sort(BY_COUNT_DESC);
        }
        
        return tagsBySender;
    }
    
    public List<TagInfo> getTagsByCountry(List<Postcard> postcardList)
    {
        if (tagsByCountry.isEmpty())
        {
            Set<String> countrySet = postcardList.stream()
                    .map(Postcard::getCountry)
                    .collect(Collectors.toSet());
            
            countrySet.stream()
                    .forEach(country -> 
                    {
                        TagInfo tagInfo = new TagInfo(country, PostcardResource.getListWithTag(country).size());
                        Set<String> cities =
                                postcardList.stream()
                                        .filter(postcard -> country.equals(postcard.getCountry()))
                                        .filter(postcard -> postcard.getCity() != null)
                                        .map(Postcard::getCity)
                                        .collect(Collectors.toSet());
                        cities.stream()
                                .forEach(city -> tagInfo.getList()
                                        .add(new TagInfo(city, PostcardResource.getListWithTag(city).size())));
                        
                        tagsByCountry.add(tagInfo);
                    });
            
            tagsByCountry.forEach(country -> country.getList().sort(BY_COUNT_DESC));
            tagsByCountry.sort(BY_COUNT_DESC);
        }
        
        return tagsByCountry;
    }
    
    public List<TagInfo> getTagsByName(List<Postcard> postcardList) {
        return getTagsSorted(tagsByName, postcardList, BY_NAME);
    }
    
    public List<TagInfo> getTags(List<Postcard> postcardList)
    {
        return getTagsSorted(tags, postcardList, BY_COUNT_DESC);
    }
    
    public List<TagInfo> getTagsSorted(List<TagInfo> listToSort, List<Postcard> postcardList, Comparator<TagInfo> sortParam)
    {
        if (listToSort.isEmpty())
        {
            Set<String> tagSet = postcardList.stream()
                    .flatMap(p -> p.getTags().stream())
                    .collect(Collectors.toSet());
            
            tagSet.stream()
                    .forEach(tag -> listToSort.add(new TagInfo(tag, 
                            PostcardResource.getListWithTag(tag).size()))
                    );
            
            listToSort.sort(sortParam);
        }
        
        return listToSort;
    }

    public List<TagInfo> getTagsByYear(List<Postcard> postcardList)
    {
        if (tagsByYear.isEmpty())
        {
            Set<String> yearSet = postcardList.stream()
                    .map(Postcard::getYear)
                    .collect(Collectors.toSet());
            
            yearSet.stream()
                    .forEach(year -> tagsByYear.add(new TagInfo(year, 
                            PostcardResource.getListWithTag(year).size()))
                    );

            tagsByYear.sort(BY_NAME.reversed());
        }
        
        return tagsByYear;
    }
}
