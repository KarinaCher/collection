package resources;

import entity.Postcard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

                () -> assertEquals("20210129DE", postcards.get(0).getId()),
                () -> assertEquals(1, postcards.get(0).getTags().size()),
                () -> assertEquals("project", postcards.get(0).getTags().get(0)),
                () -> assertEquals("test1 <a href=\"https://www.someUrl.com/\" target=\"_blank\">link title</a>|continue text.",
                        postcards.get(0).getDescription())
        );
    }
}
