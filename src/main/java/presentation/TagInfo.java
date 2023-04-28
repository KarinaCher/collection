package presentation;

import java.util.ArrayList;
import java.util.List;

public class TagInfo
{
    public TagInfo(String name, Integer count)
    {
        this.name = name;
        this.count = count;
    }

    private String name;
    private Integer count;
    private List<TagInfo> list = new ArrayList();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public List<TagInfo> getList()
    {
        return list;
    }
}
