package br.com.felipe.books.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaderCreateDto {

    private String email;

    private String name;

    private String cpf;

    private String pin;
}
