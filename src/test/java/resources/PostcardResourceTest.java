package resources;

import entity.Postcard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PostcardResourceTest
{
    @Test
    public void testGetList() {
        List<Postcard> postcards = PostcardResource.readTsv("/TestScope.tsv");

        assertAll("Test get List",
                () -> assertEquals(16, postcards.size()),

                () -> assertEquals("20210621DE", postcards.get(14).getId()),
                () -> assertEquals(1, postcards.get(14).getSenders().size()),
                () -> assertEquals("JanaFrank", postcards.get(14).getSenders().get(0)),
                () -> assertEquals(2, postcards.get(14).getImages().size()),
                () -> assertEquals("ImageName 1.jpg",
                        postcards.get(14).getImages().get(0)),
                () -> assertEquals("ImageName 2.jpg",
                        postcards.get(14).getImages().get(1)),
                () -> assertTrue(postcards.get(14).isMine()),
                () -> assertEquals("15.06.2020", postcards.get(14).getDateSentString()),
                () -> assertEquals("21.06.2021", postcards.get(14).getDateReceivedString()),
                () -> assertEquals("2021", postcards.get(14).getYear()),

                () -> assertEquals("20210129DE", postcards.get(0).getId()),
                () -> assertEquals(1, postcards.get(0).getTags().size()),
                () -> assertEquals("project", postcards.get(0).getTags().get(0)),
                () -> assertEquals("text <a href=\"https://www.someUrl.com/\" target=\"_blank\">link title</a>|continue text.",
                        postcards.get(0).getDescription()),
                () -> assertEquals(100, postcards.get(0).getHeight()),
                () -> assertEquals(50, postcards.get(0).getWidth()),
                () -> assertEquals(5000, postcards.get(0).getSquare()),

                () -> assertNull(postcards.get(15).getYear())
        );
    }

    @Test
    public void testOtherList() {
        List<Postcard> postcards = PostcardResource.getOtherList();

        assertAll("Test list of 'Others' postcards, which wasn't sent to me.",
                () -> assertEquals(2, postcards.size()),
                () -> assertFalse(postcards.get(0).isMine())
        );
    }
}
