package statistic;

import entity.Postcard;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SizeMap
{
    private final List<Postcard> postcardList;
    private Map<Integer, Map<Integer, Integer>> map = new HashMap();
    private Set<Integer> heights = new HashSet();
    private Set<Integer> widths = new HashSet();

    public SizeMap(List<Postcard> postcardList)
    {
        this.postcardList = postcardList;
    }
    
    private void build()
    {
        for (Postcard postcard : postcardList)
        {
            heights.add(postcard.getHeight());
            widths.add(postcard.getWidth());
        }
        
        for (Integer height : heights) 
        {
            map.put(height, new HashMap());
            for (Integer width : widths) 
            {
                map.get(height).put(width, 0);
            }
        }
        
        for (Postcard postcard : postcardList)
        {
            Map<Integer, Integer> heightMap = map.get(postcard.getHeight());
            int increaseValue = heightMap.get(postcard.getWidth()) + 1;
            heightMap.put(postcard.getWidth(), increaseValue);
        }
    }
    
    public Map<Integer, Map<Integer, Integer>> get() {
        if (map.isEmpty())
        {
            build();
        }
        return map;
    }
}
