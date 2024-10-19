package br.com.felipe.books.service;

import br.com.felipe.books.domain.dto.ReaderCreateDto;
import br.com.felipe.books.domain.model.Reader;
import br.com.felipe.books.repository.ReaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReaderService {
    private ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository){
        this.readerRepository = readerRepository;
    }

    public List<Reader> findAll(){
        return  readerRepository.findAll();
    }

    public Reader findByid(Long id){
        Optional<Reader> searchReaderById = readerRepository.findById(id);
        if(searchReaderById.isEmpty()){
            return null;
        }
        return searchReaderById.get();
    }

    public Reader create(ReaderCreateDto readerCreateDto) {
//        boolean isReaderCreated = hasAnyReaderCreated(readerRequest);
//
//        if(isReaderCreated){
//            log.error("Reader já realizado");
//            throw new RuntimeException("Reader já realizado");
//        }

        if(readerRepository.existsByCpf(readerCreateDto.getCpf())){
            log.error("CPF já cadastrado");
            throw new RuntimeException("CPF já cadastrado");
        }

        if (readerRepository.existsByEmail(readerCreateDto.getEmail())){
            log.error("Email já cadastrado");
            throw new RuntimeException("Email já cadastrado");
        }

        return readerRepository.save(readerCreateDto);
    }

    public Reader update(Reader readerUpdateRequest){
        Long id = readerUpdateRequest.getId();

//        Optional<Reader> readerSearchById = readerRepository.findById(id);
//
//        if(readerSearchById.isEmpty())
//            logAndThrowError("Nao existe um usuario com este id para atualizar");
//
//        Reader featuredReader = readerSearchById.get();

        Reader featuredReader = readerRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Nao encontrado reader com este id");
                return new RuntimeException();
            });

        if (readerRepository.existsByEmail(readerUpdateRequest.getEmail()))
            logAndThrowError("Ja existe outro usuario com este email");

        if(readerRepository.existsByCpf(readerUpdateRequest.getCpf()))
            logAndThrowError("Ja existe outro usuario com este cpf");w


        return readerRepository.save(readerUpdateRequest);
    }

    public void deleteById (Long id){
        if (!readerRepository.existsById(id))
            logAndThrowError("Usuário não encontrado com o id " + id);

        readerRepository.deleteById(id);
    }

    private void logAndThrowError(final String message) {
        log.error(message);
        throw new RuntimeException(message);
    }


//    private  boolean hasAnyReaderCreated(Reader ler) {
//
//        boolean hasAnyCpfEquals = readerRepository.findAll()
//                .stream()
//                .filter(readerAtual -> readerAtual.getCpf().equals(ler.getCpf()))
//                .toList()
//                .isEmpty();
//
//        boolean hasAnyEmailEquals = readerRepository.findAll()
//                .stream()
//                .filter(readerAtual -> readerAtual.getEmail().equals(ler.getEmail()))
//                .toList()
//                .isEmpty();
//
//        return hasAnyCpfEquals || hasAnyEmailEquals;
//    }

}
