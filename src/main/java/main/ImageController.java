package main;

import entity.Postcard;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import resources.PostcardResource;

@Controller
public class ImageController
{
    @Autowired
    ServletContext servletContext;
    
    @Autowired
    ResourceLoader resourceLoader;
    
    private static final String JPG = "jpg";
    private static final String SMALL = "sm";
    private static final String THUMB = "thumb";
    
    @RequestMapping(value = "/image/{size}/{name}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(
            @PathVariable("name") String name,
            @PathVariable("size") String size) 
            throws IOException 
    {
        return createImage(name, size);
    }
    
    @RequestMapping(value = "/lastPostcard", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getLast() 
            throws IOException 
    {
        Postcard postcard = PostcardResource.getList().get(0);
        return createImage(postcard.getImages().get(0), "sm");
    }

    private ResponseEntity<byte[]> createImage(String name, String size) throws IOException
    {
        HttpHeaders headers = new HttpHeaders();
        String folder = name.substring(0, 4) + "/";
        InputStream in = resourceLoader.getResource("classpath:" + folder + name).getInputStream();
        
        byte[] media;
        if (SMALL.equals(size)) 
        {
            media = resize(in, 200);
        }
        else if (THUMB.equals(size)) 
        {
            media = resize(in, 100);
        }
        else
        {
            media = IOUtils.toByteArray(in);
        }
       
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.setContentType(MediaType.IMAGE_JPEG);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }

    private byte[] resize(InputStream in, int maxSize) throws IOException
    {
        byte[] media;
        BufferedImage image = ImageIO.read(in);
        boolean isVertical = image.getHeight() > image.getWidth();
        int width, height;
        if (isVertical)
        {
            height = maxSize;
            width = image.getWidth() * maxSize / image.getHeight();
        }
        else
        {
            width = maxSize;
            height = image.getHeight() * maxSize / image.getWidth();
        }
        BufferedImage resized = resize(image, height, width);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resized, JPG, baos);
        media = baos.toByteArray();
        return media;
    }
    
    private static BufferedImage resize(BufferedImage img, int height, int width) 
    {
        BufferedImage scaledBI = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledBI.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(img, 0, 0, width, height, null); 
        g.dispose();
        return scaledBI;
    }
}
