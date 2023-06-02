package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import resources.BookResource;

@Controller
public class BookController {
    @Autowired
    private BookResource resource;

    @GetMapping("/book")
    public String overview(Model model) {
        model.addAttribute("list", resource.getList());
        return "library";
    }
}
