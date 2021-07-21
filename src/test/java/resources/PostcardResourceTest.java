package resources;

import entity.Postcard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static util.TsvUtil.readTsv;

public class PostcardResourceTest {
    @Test
    public void testGetList() {
        List<Postcard> postcards = readTsv("TestScope.tsv");

        Postcard postcard = postcards.stream()
                .filter(p -> p.getId().equals("20210621DE"))
                .findFirst()
                .get();
        Postcard postcard1 = postcards.stream()
                .filter(p -> p.getId().equals("20210129DE"))
                .findFirst()
                .get();
        Postcard postcard2 = postcards.stream()
                .filter(p -> p.getId().equals("20210628GB"))
                .findFirst()
                .get();
        assertAll("Test get List",
                () -> assertEquals(16, postcards.size()),

                () -> assertEquals("20210621DE", postcard.getId()),
                () -> assertEquals(1, postcard.getSenders().size()),
                () -> assertEquals("JanaFrank", postcard.getSenders().get(0)),
                () -> assertEquals(2, postcard.getImages().size()),
                () -> assertEquals("ImageName 1.jpg",
                        postcard.getImages().get(0)),
                () -> assertEquals("ImageName 2.jpg",
                        postcard.getImages().get(1)),
                () -> assertTrue(postcard.isMine()),
                () -> assertEquals("15.06.2020", postcard.getDateSentString()),
                () -> assertEquals("21.06.2021", postcard.getDateReceivedString()),
                () -> assertEquals("2021", postcard.getYear()),

                () -> assertEquals("20210129DE", postcard1.getId()),
                () -> assertEquals(1, postcard1.getTags().size()),
                () -> assertEquals("project", postcard1.getTags().get(0)),
                () -> assertEquals("text <a href=\"https://www.someUrl.com/\" target=\"_blank\">link title</a>|continue text.",
                        postcard1.getDescription()),
                () -> assertEquals(100, postcard1.getHeight()),
                () -> assertEquals(50, postcard1.getWidth()),
                () -> assertEquals(5000, postcard1.getSquare()),
                () -> assertEquals(1, postcard1.getTags().size()),

                () -> assertNull(postcard2.getYear()),
                () -> assertEquals(0, postcard2.getTags().size())
        );
    }

    @Test
    public void testOtherList() {
        List<Postcard> postcards = (new PostcardOtherResource()).getList();

        assertAll("Test list of 'Others' postcards, which wasn't sent to me.",
                () -> assertEquals(2, postcards.size()),
                () -> assertFalse(postcards.get(0).isMine())
        );
    }
}
