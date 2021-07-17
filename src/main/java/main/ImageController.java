package main;

import entity.Postcard;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import resources.ResourceContainer;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    public static final String LAST_ITEM_SIZE = "200";
    @Autowired
    ServletContext servletContext;

    @Autowired
    ResourceLoader resourceLoader;

    private static ResourceContainer container = new ResourceContainer();

    private static final String JPG = "jpg";

    @RequestMapping(value = "/image/{size}/{name}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(
            @PathVariable("name") String name,
            @PathVariable("size") String size)
            throws IOException {
        return createImage(name, size);
    }

    @RequestMapping(value = "/lastPostcard", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getLast()
            throws IOException {
        Postcard postcard = container.getResource().getList().get(0);
        return createImage(postcard.getImages().get(0), LAST_ITEM_SIZE);
    }

    private ResponseEntity<byte[]> createImage(String name, String size) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        String folder = name.substring(0, 4) + "/";
        InputStream in = resourceLoader.getResource("classpath:" + folder + name).getInputStream();

        byte[] media;

        if (size.chars().allMatch(Character::isDigit)) {
            media = resize(in, Integer.parseInt(size));
        } else {
            media = IOUtils.toByteArray(in);
        }

        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.setContentType(MediaType.IMAGE_JPEG);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }

    private byte[] resize(InputStream in, int maxSize) throws IOException {
        byte[] media;
        BufferedImage image = ImageIO.read(in);
        boolean isVertical = image.getHeight() > image.getWidth();
        int width, height;
        if (isVertical) {
            height = maxSize;
            width = image.getWidth() * maxSize / image.getHeight();
        } else {
            width = maxSize;
            height = image.getHeight() * maxSize / image.getWidth();
        }
        BufferedImage resized = resize(image, height, width);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resized, JPG, baos);
        media = baos.toByteArray();
        return media;
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        BufferedImage scaledBI = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledBI.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(img, 0, 0, width, height, null);
        g.dispose();
        return scaledBI;
    }
}
