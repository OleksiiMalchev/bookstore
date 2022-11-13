package com.javacourse.bookstore.repositories;

import com.javacourse.bookstore.domain.Author;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class AuthorRepositories {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/bookstore";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private final Map<Long, Author> baseAuthor = new HashMap<>();
    private final Random randomID = new Random();


//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public List<Author> getAllAuthor() throws SQLException {
//        List<Author> authors = new ArrayList<>();
//        Statement statement = connection.createStatement();
//        String SQL = "SELECT * FROM author";
//        ResultSet resultSet = statement.executeQuery(SQL);
//        while (resultSet.next()){
//            Author author = Author.builder().build();
//            author.setId(resultSet.getLong("id"));
//            author.setFirstName(resultSet.getString("first_name"));
//            authors.add(author);
//        }
//        return authors;

//        return baseAuthor.values()
//                .stream()
//                .collect(Collectors.toList());
        return null;
    }

    public Optional<Author> getAuthorByID(Long ID) {
        return Optional.ofNullable(baseAuthor.get(ID));

    }

    public Author saveAuthorInBase(Author author) {
        if (author != null) {
            author.setId(randomID.nextLong());
            baseAuthor.put(author.getId(), author);
            return author;
        }
        return null;
    }

    public Author updateAuthorByID(Long ID, Author author) {
        if (baseAuthor.containsKey(ID)) {
            author.setId(ID);
            baseAuthor.put(author.getId(), author);
            return baseAuthor.get(author.getId());
        }
        return null;
    }

    public Optional<Author> deleteAuthorByID(Long ID) {
        return Optional.ofNullable(baseAuthor.remove(ID));
    }


    public Author findAuthorByBook(Long idBook) {
//        Book bookByID = baseAuthor.entrySet()
//                .stream()
//                .flatMap(a -> a.getValue()
//                        .getBooks()
//                        .stream())
//                .filter(f -> f.getId().equals(idBook))
//                .findAny()
//                .orElse(null);
//        return Optional.ofNullable(bookByID).stream()
//                .map(Book::getAuthor).findAny().orElse(null);
        return null;
    }

}
