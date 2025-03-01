package entity;

import org.jboss.logging.Logger;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static util.DateUtil.format;

public class Postcard implements Item<String> {
    public static final Logger LOG = Logger.getLogger(Postcard.class);

    private String id;
    private List<String> images = new ArrayList<>();
    private LocalDate dateSent;
    private LocalDate dateReceived;
    private int height;
    private int width;
    private String country;
    private String city;
    private List<String> senders = new ArrayList<>();
    private String description;
    private List<String> tags = new ArrayList<>();
    private String originCountry;
    private boolean mine = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public LocalDate getDateSent() {
        return dateSent;
    }

    public void setDateSent(LocalDate dateSent) {
        this.dateSent = dateSent;
    }

    public LocalDate getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDate dateReceived) {
        this.dateReceived = dateReceived;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryOrOrigin() {
        return getOriginCountry() == null ? getCountry() : getOriginCountry();
    }

    public String getCityOrOrigin() {
        return getOriginCountry() == null ? getCity() : null;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getSenders() {
        return senders;
    }

    public void setSenders(List<String> senders) {
        this.senders = senders;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public LocalDate getDate() {
        if (getDateReceived() != null) {
            return getDateReceived();
        } else if (getDateSent() != null) {
            return getDateSent();
        } else {
            return null;
        }
    }

    public long getTravelDays() {
        if (getDateSent() == null || getDateReceived() == null) {
            return -1;
        }

        return Duration.ofDays(DAYS.between(getDateSent(), getDateReceived())).toDays();
    }

    public String getYear() {
        if (getDate() == null) {
            LOG.warn("Postcard without date. ID = " + getId());
            return null;
        }

        return String.valueOf(getDate().getYear());
    }

    public String getMonth() {
        if (getDate() == null) {
            LOG.warn("Postcard without date. ID = " + getId());
            return null;
        }

        int value = getDate().getMonth().getValue();
        return getYear() + ((value < 10) ? "0" + value : String.valueOf(value));
    }

    public int getSquare() {
        return getHeight() * getWidth();
    }

    public String getDateSentString() {
        return format(dateSent);
    }

    public String getDateReceivedString() {
        return format(dateReceived);
    }
}
