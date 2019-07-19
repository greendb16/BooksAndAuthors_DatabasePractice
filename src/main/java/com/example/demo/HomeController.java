package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;


@Controller
public class HomeController {

    @Autowired
    BookRepository bookList;

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("booklist", bookList.findAll() );
        model.addAttribute("authors", authorRepository.findAll());
        return "index";
    }

    @RequestMapping("/addbook")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("bookAuthors", authorRepository.findAll());
        return "book";
    }

    @RequestMapping("/savebook")
    public String saveBook(@ModelAttribute("aBook") Book book, Model model){
        bookList.save(book);
        return "redirect:/";
    }

    @PostConstruct
    public void fillTables(){
        Author a = new Author();
        a.setFirstName("J.K.");
        a.setLastName("Rowling");
        a.setGenre("Fantasy");
        authorRepository.save(a);

        a = new Author();
        a.setFirstName("J.R.R");
        a.setLastName("Tolkien");
        a.setGenre("Fantasy");
        authorRepository.save(a);

        a = new Author();
        a.setFirstName("Stephen");
        a.setLastName("King");
        a.setGenre("Horror");
        authorRepository.save(a);

    }
}
