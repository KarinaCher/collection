package main;

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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImageController
{
    @Autowired
    ServletContext servletContext;
    
    @Autowired
    ResourceLoader resourceLoader;
    
    private static final int MAX = 200;
    private static final String JPG = "jpg";
    private static final String SMALL = "sm";
    
    @RequestMapping(value = "/image/{size}/{name}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(
            @PathVariable("name") String name,
            @PathVariable("size") String size) 
            throws IOException 
    {
        HttpHeaders headers = new HttpHeaders();
        String folder = name.substring(0, 4) + "/";
        InputStream in = resourceLoader.getResource("classpath:" + folder + name).getInputStream();
        
        byte[] media;
        if (SMALL.equals(size)) 
        {
            BufferedImage image = ImageIO.read(in);
            
            boolean isVertical = image.getHeight() > image.getWidth();
            
            int width, height;
            if (isVertical)
            {
                height = MAX;
                width = image.getWidth() * MAX / image.getHeight();
            }
            else
            {
                width = MAX;
                height = image.getHeight() * MAX / image.getWidth();
            }
            
            BufferedImage resized = resize(image, height, width);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resized, JPG, baos);
            media = baos.toByteArray();
        }
        else
        {
            media = IOUtils.toByteArray(in);
        }
       
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
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
