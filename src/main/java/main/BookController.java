package main;

import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import resources.BookResource;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Controller
public class BookController {
    @Autowired
    private BookResource resource;

    @GetMapping("/book")
    public String overview(Model model) {
        List<Book> list = resource.getList();
        TreeMap<String, List<Book>> map = list.stream()
                .collect(groupingBy(Book::getAuthorToSort, TreeMap::new, Collectors.toList()));
        model.addAttribute("map", map);
        return "library";
    }
}
