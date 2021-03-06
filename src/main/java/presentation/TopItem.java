package presentation;

import entity.Postcard;
import java.time.LocalDate;
import static util.DateUtil.format;

public class TopItem
{
    private String nomination;
    private String value;
    private String itemId;
    private String image;

    public TopItem(String nomination, String value, Postcard postcard)
    {
        this.nomination = nomination;
        this.value = value;
        this.itemId = postcard.getId();
        this.image = postcard.getImages().get(0);
    }

    public TopItem(String nomination, LocalDate value, Postcard postcard)
    {
        this.nomination = nomination;
        this.value = format(value);
        this.itemId = postcard.getId();
        this.image = postcard.getImages().get(0);
    }

    public TopItem(String nomination, int value, Postcard postcard)
    {
        this.nomination = nomination;
        this.value = String.valueOf(value);
        this.itemId = postcard.getId();
        this.image = postcard.getImages().get(0);
    }

    
    public String getNomination()
    {
        return nomination;
    }

    public String getValue()
    {
        return value;
    }

    public String getItemId()
    {
        return itemId;
    }

    public String getImage()
    {
        return image;
    }
}
