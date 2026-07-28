package util;

import entity.Postcard;
import request.PostcardReq;

public class PostcardConverter {
    public static PostcardReq convert(Postcard postcard) {
        PostcardReq postcardReq = new PostcardReq();
        // id ?
        postcardReq.setDateSent(postcard.getDateSent());
        postcardReq.setDateSent(postcard.getDateSent());
        postcardReq.setOriginCountry(postcard.getOriginCountry());
        postcardReq.setCountry(postcard.getCountry());
        postcardReq.setCity(postcard.getCity());
        postcardReq.setWidth(postcard.getWidth());
        postcardReq.setHeight(postcard.getHeight());
        postcardReq.setSenders(postcard.getSenders());
        postcardReq.setTags(postcard.getTags());
        postcardReq.setDescription(postcard.getDescription());
        // images ?
        return postcardReq;
    }
}
