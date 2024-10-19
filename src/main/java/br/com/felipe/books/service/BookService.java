package br.com.felipe.books.service;

import br.com.felipe.books.domain.model.Book;
import br.com.felipe.books.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        Optional<Book> searchBookById = bookRepository.findById(id);
        if(searchBookById.isEmpty()){
            return null;
        }
        return searchBookById.get();
    }

    public Book create(Book livroRequest){
        boolean isNewBook = hasBookSameAuthorAndTitle(livroRequest);

        if(!isNewBook){
            log.error("Livro já cadastrado");
            throw new RuntimeException("Livro já cadastrado");
        }

        return bookRepository.save(livroRequest);
    }

    public Book update(Book livroRequest){
        boolean isBookUpdateValid = hasBookSameAuthorAndTitle(livroRequest);

        if (!isBookUpdateValid) {
            log.error("Nome informado já existe para o mesmo autor");
            throw new RuntimeException("Nome informado já existe para o mesmo autor");
        }
        return  bookRepository.save(livroRequest);
    }

    public void deleteById (Long id){

        if (!bookRepository.existsById(id)) {
            log.error("Livro não encontrado com id: {}", id);
            return;
        }
        bookRepository.deleteById(id);
    }


    private  boolean hasBookSameAuthorAndTitle(Book livro){
        return bookRepository.findAll()
                .stream()
                .filter(livroAtual -> livroAtual.getAutor().equals(livro.getAutor()))
                .filter(livroAtual -> livroAtual.getTitulo().equals(livro.getTitulo()))
                .toList()
                .isEmpty();
    }



}
