package controller;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import service.BookService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class Bcontroller {
    @Autowired
    private BookService bookService;
    public Bcontroller()
    {
        bookService = new BookService();
    }
    @GetMapping("/books")
    public List<Book> GetAll(){return bookService.getAll();}
    @GetMapping("/books/{id}")
    public Book get(@PathVariable int id ){return bookService.get(id); }
    @PostMapping("/books")
    @ResponseBody
    public Book create(@RequestBody Book book){
        bookService.add(book);
        return book;
    }
    @DeleteMapping("/books/{id}")
    @ResponseBody
    private void delete(@PathVariable int id){bookService.remove(id);}
}


