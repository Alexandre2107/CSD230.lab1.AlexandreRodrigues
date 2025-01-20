package csd230.lab1;

import com.github.javafaker.Faker;
import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private DiscMagRepository discMagRepository;

	@Autowired
	private MagazineRepository magazineRepository;

	@Autowired
	private TicketRepository ticketRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker();

		// Create and save a Cart
		Cart cart = new Cart();
		cartRepository.save(cart);

		// Create and save Books
		for (int i = 0; i < 5; i++) {
			Book book = new Book();
			book.setAuthor(faker.book().author());
			book.setIsbn(faker.code().isbn13());
			book.setTitle(faker.book().title());
			book.setPrice(Double.parseDouble(faker.commerce().price(10.0, 100.0).replace(",", ".")));
			book.setQuantity(faker.number().numberBetween(1, 10));
			book.setDescription(faker.lorem().sentence());
			book.setCopies(faker.number().numberBetween(1, 100));
			book.setCart(cart);
			bookRepository.save(book);
		}

		// Create and save DiscMags
		for (int i = 0; i < 5; i++) {
			DiscMag discMag = new DiscMag();
			discMag.setTitle(faker.book().title());
			discMag.setCopies(faker.number().numberBetween(1, 100));
//			discMag.setOrderQty(faker.number().numberBetween(1, 10));
//			discMag.setCurrIssue(faker.date().birthday());
			discMag.setHasDisc(faker.bool().bool());
			discMag.setPrice(Double.parseDouble(faker.commerce().price(10.0, 100.0).replace(",", ".")));
			discMag.setQuantity(faker.number().numberBetween(1, 10));
			discMag.setDescription(faker.lorem().sentence());
			discMag.setCart(cart);
			discMagRepository.save(discMag);
		}

		// Create and save Magazines
		for (int i = 0; i < 5; i++) {
			Magazine magazine = new Magazine();
			magazine.setTitle(faker.book().title());
			magazine.setCopies(faker.number().numberBetween(1, 100));
			magazine.setOrderQty(faker.number().numberBetween(1, 10));
			magazine.setCurrIssue(faker.date().birthday());
			magazine.setPrice(Double.parseDouble(faker.commerce().price(10.0, 100.0).replace(",", ".")));
			magazine.setQuantity(faker.number().numberBetween(1, 10));
			magazine.setDescription(faker.lorem().sentence());
			magazine.setCart(cart);
			magazineRepository.save(magazine);
		}

		// Create and save Tickets
		for (int i = 0; i < 5; i++) {
			Ticket ticket = new Ticket();
			ticket.setText(faker.lorem().sentence());
			ticket.setPrice(Double.parseDouble(faker.commerce().price(10.0, 100.0).replace(",", ".")));
			ticket.setQuantity(faker.number().numberBetween(1, 10));
			ticket.setDescription(faker.lorem().sentence());
			ticket.setCart(cart);
			ticketRepository.save(ticket);
		}

		// Read and print all CartItems
		cart.getItems().forEach(System.out::println);

		// Update a CartItem
		Book bookToUpdate = (Book) cart.getItems().stream().filter(item -> item instanceof Book).findFirst().orElse(null);
		if (bookToUpdate != null) {
			bookToUpdate.setPrice(99.99);
			bookRepository.save(bookToUpdate);
		}

		// Delete a CartItem
		Book bookToDelete = (Book) cart.getItems().stream().filter(item -> item instanceof Book).skip(1).findFirst().orElse(null);
		if (bookToDelete != null) {
			bookRepository.delete(bookToDelete);
		}
	}
}