package entity;

public class TagInfo
{

    public TagInfo(String name, Integer count)
    {
        this.name = name;
        this.count = count;
    }
    
    private String name;
    private Integer count;

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
    
    
}
