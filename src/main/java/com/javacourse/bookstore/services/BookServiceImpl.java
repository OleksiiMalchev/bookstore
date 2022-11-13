package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.BookReqDTO;
import com.javacourse.bookstore.domain.dto.BookRespDTO;
import com.javacourse.bookstore.mappers.MapperForBook;
import com.javacourse.bookstore.repositories.AuthorRepositories;
import com.javacourse.bookstore.repositories.BookRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepositories bookRepositories;
    private final AuthorRepositories authorRepositories;

    private final MapperForBook mapperForBook;

    public List<BookRespDTO> allBooks() {
        return bookRepositories.findAll()
                .stream()
                .map(mapperForBook::toBookRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookRespDTO> allBooksAuthor(Long idAuthor) {
        return bookRepositories.findAllByAuthorID(idAuthor)
                .stream()
                .map(mapperForBook::toBookRespDTO)
                .collect(Collectors.toList());
    }

    public BookRespDTO getBookById(Long idBook) {
        return bookRepositories.findById(idBook)
                .map(mapperForBook::toBookRespDTO)
                .orElse(null);
    }

    public BookRespDTO create(BookReqDTO bookReqDTO) {
//        Author authorByID = authorRepositories.getAuthorByID(bookReqDTO.getAuthorID()).get();
//        Book book = mapperForBook.getBook(bookReqDTO);
//        book.setAuthor(authorByID);
//        Book saveBook = bookRepositories.save(book);
//        authorByID.addBook(saveBook);
//        authorRepositories.updateAuthorByID(authorByID.getId(), authorByID);
//        return mapperForBook.toBookRespDTO(saveBook);
        return null;
    }

    public BookRespDTO update(Long idBook, BookReqDTO bookReqDTO) {
//        Author authorByIdFromBookReqDTO = authorRepositories.getAuthorByID(bookReqDTO.getAuthorID()).get();
//        if (authorByIdFromBookReqDTO!=null && idBook != null) {
//            Book bookFromReqDTO = mapperForBook.getBook(bookReqDTO);
//            Book bookInBase = bookRepositories.findById(idBook).get();
//            Author authorByIdFromBookInBase = bookInBase.getAuthor();
//            bookFromReqDTO.setESBI(bookInBase.getESBI());
//            bookFromReqDTO.setId(bookInBase.getId());
//            bookFromReqDTO.setAuthor(authorByIdFromBookReqDTO);
//            Book updateBook = bookRepositories.update(idBook, bookFromReqDTO);
//            if (authorByIdFromBookReqDTO.getId() == authorByIdFromBookInBase.getId()) {
//                authorByIdFromBookInBase.delete(bookInBase);
//                authorByIdFromBookInBase.addBook(updateBook);
//                authorRepositories.updateAuthorByID(authorByIdFromBookInBase.getId(), authorByIdFromBookInBase);
//            } else {
//                authorByIdFromBookInBase.delete(bookInBase);
//                authorRepositories.updateAuthorByID(authorByIdFromBookInBase.getId(), authorByIdFromBookInBase);
//                authorByIdFromBookReqDTO.addBook(updateBook);
//                authorRepositories.updateAuthorByID(authorByIdFromBookReqDTO.getId(), authorByIdFromBookReqDTO);
//            }
//            return mapperForBook.toBookRespDTO(updateBook);
//        }
        return null;
    }

    public BookRespDTO delete(Long id) {
        return bookRepositories.remove(id)
                .map(mapperForBook::toBookRespDTO)
                .stream()
                .findAny()
                .orElse(null);
    }
}
