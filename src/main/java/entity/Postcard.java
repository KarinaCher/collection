package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Postcard
{
    private String id;
    private List<String> images = new ArrayList<>();
    private Date dateSent;
    private Date dateReceived;
    private int height;
    private int width;
    private String country;
    private String city;
    private List<String> senders = new ArrayList<>();
    private String description;
    private List<String> tags = new ArrayList<>();
    private boolean mine = true;
    
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public List<String> getImages()
    {
        return images;
    }

    public void setImages(List<String> images)
    {
        this.images = images;
    }

    public Date getDateSent()
    {
        return dateSent;
    }

    public void setDateSent(Date dateSent)
    {
        this.dateSent = dateSent;
    }

    public Date getDateReceived()
    {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived)
    {
        this.dateReceived = dateReceived;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public String getCountry()
    {
        return  country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public List<String> getSenders()
    {
        return senders;
    }

    public void setSenders(List<String> senders)
    {
        this.senders = senders;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<String> getTags()
    {
        return tags;
    }

    public void setTags(List<String> tags)
    {
        this.tags = tags;
    }

    public boolean isMine()
    {
        return mine;
    }

    public void setMine(boolean mine)
    {
        this.mine = mine;
    }
}
