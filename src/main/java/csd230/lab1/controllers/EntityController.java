package csd230.lab1.controllers;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EntityController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private DiscMagRepository discMagRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // Book Endpoints
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPrice(bookDetails.getPrice());
        book.setQuantity(bookDetails.getQuantity());
        book.setDescription(bookDetails.getDescription());
        book.setCopies(bookDetails.getCopies());
        return bookRepository.save(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    // Similar endpoints for Magazine, DiscMag, and Ticket
    // ...
}