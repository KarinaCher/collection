package json;

import java.util.Date;
import java.util.List;

/**
 * "postcard": {
       "id": "",
	   "images": [
		   ".jpg",
		   "_.jpg"
	   ],
	   "dateSent": "",
	   "dateReceived": "",
	   "height": "",
	   "width": "",
	   "country": "",
	   "city": "",
	   "sender": "",
	   "description": ""
	   }
 * @author karinacherkashina
 */
public class Postcard
{
    private String id;
    private List<String> images;
    private Date dateSent;
    private Date dateReceived;
    private int height;
    private int width;
    private String country;
    private String city;
    private String sender;
    private String description;
    private List<String> tags;

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

    public String getSender()
    {
        return sender;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
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
    
    
    
}
