package service;

import model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    private List<Book> listBook = new ArrayList<>(Arrays.asList(
            new Book(1,"Lap trinh Windows","Nguyen Huy CUong",99999),
            new Book(2,"Lap trinh web","Nguyen Huy CUong",12345),
            new Book(3,"Lap trinh Ung dung java","Nguyen Huy CUong",234567),
            new Book(1,"Thuong Mai Dien Tu","Nguyen Huy CUong",99999)
    ));

    public List<Book> getAll(){
        return listBook;
    }

    public Book get( int id){
        var findBook = listBook.stream().filter(p->p.getId()==id).findFirst().orElse(null);
        if(findBook==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return findBook;

    }

    public Book add(Book newbook)
    {
        var maxId = listBook
                .stream()
                .mapToInt(v -> v.getId())
                .max().orElse(0);
        newbook.setId(maxId+1);
        listBook.add(newbook);
        return  newbook;


    }

    public void remove( int id) {
        var findBook = listBook.stream().filter(p->p.getId()==id).findFirst().orElseThrow();
        listBook.remove(findBook);
    }

    public List<Book> search(String key) {
        return listBook.stream().filter(p->p.getAuthor().toLowerCase().contains(key)|| p.getTitle.toLowerCase().contains(key)).toList();
    }
}
