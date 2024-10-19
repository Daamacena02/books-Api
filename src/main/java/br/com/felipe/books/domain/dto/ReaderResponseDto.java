package br.com.felipe.books.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaderResponseDto {
    private Long id;

    private String email;

    private String name;

    private String cpf;
}
