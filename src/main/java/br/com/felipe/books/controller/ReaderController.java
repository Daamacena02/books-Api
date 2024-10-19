package br.com.felipe.books.controller;

import br.com.felipe.books.domain.dto.ReaderCreateDto;
import br.com.felipe.books.domain.model.Reader;
import br.com.felipe.books.service.ReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/readers")
public class ReaderController {

    private ReaderService readerService;

    public ReaderController(ReaderService readerService){
        this.readerService = readerService;
    }

    @GetMapping
    public ResponseEntity<List<Reader>> findAll(){
        List<Reader> readers = readerService.findAll();
        return ResponseEntity.ok(readers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reader> findById(@PathVariable Long id){
        Reader readerEncontrado = readerService.findByid(id);
        return ResponseEntity.ok(readerEncontrado);
    }

    @PostMapping
    public ResponseEntity<Reader> create(@RequestBody ReaderCreateDto readerCreateDto){
        Reader readerSalvo = readerService.create(readerCreateDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(readerSalvo);
    }

    @PutMapping
    public ResponseEntity<Reader> update(@RequestBody Reader readerRequest){
        Reader readerAtualizado = readerService.update(readerRequest);
        return ResponseEntity.ok(readerAtualizado);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        readerService.deleteById(id);
        return  ResponseEntity.ok().build();
    }
}
