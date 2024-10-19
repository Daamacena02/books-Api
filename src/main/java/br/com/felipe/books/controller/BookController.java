package br.com.felipe.books.controller;

import br.com.felipe.books.domain.model.Book;
import br.com.felipe.books.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {


    private BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book livroRequest) {

        Book livroSalvo = bookService.create(livroRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        Book livroEncontrado = bookService.findById(id);
        return ResponseEntity.ok(livroEncontrado);
    }

    @PutMapping
    public ResponseEntity<Book> update(@RequestBody Book livroRequest){

        Book livroAtualizado = bookService.update(livroRequest);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> delete(@PathVariable Long id) {
//            bookRepository.deleteById(id);
//            return ResponseEntity.noContent().build();
//
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
//            updatedBook.setId(id);
//            bookRepository.save(updatedBook);
//            return ResponseEntity.ok(updatedBook);
//
//    }
}


