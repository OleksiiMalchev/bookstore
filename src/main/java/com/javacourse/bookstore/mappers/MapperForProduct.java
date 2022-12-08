package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.mappers.domain.Product;
import com.javacourse.bookstore.mappers.domain.dto.ProductReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.ProductRespDTO;
import com.javacourse.bookstore.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MapperForProduct {
    private final MapperForAuthor mapperForAuthor;
    private final BookRepository bookRepository;
    private final MapperForBook mapperForBook;

    public Optional<Product> productReqDTOToProduct(ProductReqDTO productReqDTO) {
        return Optional.ofNullable(productReqDTO)
                .stream()
                .findAny()
                .map(p -> Product
                        .builder()
                        .book(bookRepository.findById(p.getBookId()).get())
                        .bookId(p.getBookId())
                        .description(p.getDescription())
                        .build());
    }

    public ProductRespDTO productToProductRespDTO(Product product) {

        return Optional.ofNullable(product)
                .stream()
                .findAny()
                .map(p -> ProductRespDTO.builder()
                        .id(p.getId())
                        .price(p.getPrice())
                        .description(p.getDescription())
                        .book(mapperForBook.toBookRespDTO(p.getBook()))
                        .build())
                .orElse(null);
    }

}
