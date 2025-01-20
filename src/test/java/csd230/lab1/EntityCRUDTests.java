package csd230.lab1;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(locations = "classpath:application-test.properties")
public class EntityCRUDTests {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private MagazineRepository magazineRepository;

	@Autowired
	private DiscMagRepository discMagRepository;

	@Autowired
	private TicketRepository ticketRepository;

	private Book testBook;
	private Magazine testMagazine;
	private DiscMag testDiscMag;
	private Ticket testTicket;

	@BeforeEach
	void setUp() {
		// Test data
		testBook = new Book();
		testBook.setTitle("Test Book");
		testBook.setAuthor("Test Author");
		testBook.setIsbn("123-456-789");
		testBook.setCopies(10);
		testBook.setPrice(19.99);
		testBook.setQuantity(1);
		testBook.setDescription("Book Description");

		testMagazine = new Magazine();
		testMagazine.setTitle("Test Magazine");
		testMagazine.setOrderQty(5);
		testMagazine.setCurrIssue(new Date());
		testMagazine.setCopies(20);
		testMagazine.setPrice(9.99);
		testMagazine.setQuantity(2);
		testMagazine.setDescription("Magazine Description");

		testDiscMag = new DiscMag();
		testDiscMag.setTitle("Test DiscMag");
		testDiscMag.setCopies(50);
		testDiscMag.setPrice(14.99);
		testDiscMag.setQuantity(1);
		testDiscMag.setDescription("DiscMag Description");
		testDiscMag.setHasDisc(true);

		testTicket = new Ticket();
		testTicket.setText("Test Ticket");
		testTicket.setPrice(49.99);
		testTicket.setQuantity(5);
		testTicket.setDescription("Ticket Description");
	}

	@Test
	void testCreateAndReadBook() {
		// CREATE
		Book savedBook = bookRepository.save(testBook);
		assertThat(savedBook.getId()).isNotNull();

		// READ
		Optional<Book> foundBook = bookRepository.findById(savedBook.getId());
		assertThat(foundBook).isPresent();
		assertThat(foundBook.get().getTitle()).isEqualTo("Test Book");
	}

	@Test
	void testUpdateBook() {
		// CREATE
		Book savedBook = bookRepository.save(testBook);

		// UPDATE
		savedBook.setTitle("Updated Title");
		Book updatedBook = bookRepository.save(savedBook);

		// VERIFY UPDATE
		Optional<Book> foundBook = bookRepository.findById(updatedBook.getId());
		assertThat(foundBook).isPresent();
		assertThat(foundBook.get().getTitle()).isEqualTo("Updated Title");
	}

	@Test
	void testDeleteBook() {
		// CREATE
		Book savedBook = bookRepository.save(testBook);

		// DELETE
		bookRepository.delete(savedBook);

		// VERIFY DELETE
		Optional<Book> deletedBook = bookRepository.findById(savedBook.getId());
		assertThat(deletedBook).isEmpty();
	}

	@Test
	void testCreateAndReadMagazine() {
		// CREATE
		Magazine savedMagazine = magazineRepository.save(testMagazine);
		assertThat(savedMagazine.getId()).isNotNull();

		// READ
		Optional<Magazine> foundMagazine = magazineRepository.findById(savedMagazine.getId());
		assertThat(foundMagazine).isPresent();
		assertThat(foundMagazine.get().getTitle()).isEqualTo("Test Magazine");
	}

	@Test
	void testUpdateMagazine() {
		// CREATE
		Magazine savedMagazine = magazineRepository.save(testMagazine);

		// UPDATE
		savedMagazine.setTitle("Updated Magazine Title");
		Magazine updatedMagazine = magazineRepository.save(savedMagazine);

		// VERIFY UPDATE
		Optional<Magazine> foundMagazine = magazineRepository.findById(updatedMagazine.getId());
		assertThat(foundMagazine).isPresent();
		assertThat(foundMagazine.get().getTitle()).isEqualTo("Updated Magazine Title");
	}

	@Test
	void testDeleteMagazine() {
		// CREATE
		Magazine savedMagazine = magazineRepository.save(testMagazine);

		// DELETE
		magazineRepository.delete(savedMagazine);

		// VERIFY DELETE
		Optional<Magazine> deletedMagazine = magazineRepository.findById(savedMagazine.getId());
		assertThat(deletedMagazine).isEmpty();
	}

	@Test
	void testCreateAndReadDiscMag() {
		// CREATE
		DiscMag savedDiscMag = discMagRepository.save(testDiscMag);
		assertThat(savedDiscMag.getId()).isNotNull();

		// READ
		Optional<DiscMag> foundDiscMag = discMagRepository.findById(savedDiscMag.getId());
		assertThat(foundDiscMag).isPresent();
		assertThat(foundDiscMag.get().getTitle()).isEqualTo("Test DiscMag");
	}

	@Test
	void testUpdateDiscMag() {
		// CREATE
		DiscMag savedDiscMag = discMagRepository.save(testDiscMag);

		// UPDATE
		savedDiscMag.setTitle("Updated DiscMag Title");
		DiscMag updatedDiscMag = discMagRepository.save(savedDiscMag);

		// VERIFY UPDATE
		Optional<DiscMag> foundDiscMag = discMagRepository.findById(updatedDiscMag.getId());
		assertThat(foundDiscMag).isPresent();
		assertThat(foundDiscMag.get().getTitle()).isEqualTo("Updated DiscMag Title");
	}

	@Test
	void testDeleteDiscMag() {
		// CREATE
		DiscMag savedDiscMag = discMagRepository.save(testDiscMag);

		// DELETE
		discMagRepository.delete(savedDiscMag);

		// VERIFY DELETE
		Optional<DiscMag> deletedDiscMag = discMagRepository.findById(savedDiscMag.getId());
		assertThat(deletedDiscMag).isEmpty();
	}

	@Test
	void testCreateAndReadTicket() {
		// CREATE
		Ticket savedTicket = ticketRepository.save(testTicket);
		assertThat(savedTicket.getId()).isNotNull();

		// READ
		Optional<Ticket> foundTicket = ticketRepository.findById(savedTicket.getId());
		assertThat(foundTicket).isPresent();
		assertThat(foundTicket.get().getText()).isEqualTo("Test Ticket");
	}

	@Test
	void testUpdateTicket() {
		// CREATE
		Ticket savedTicket = ticketRepository.save(testTicket);

		// UPDATE
		savedTicket.setText("Updated Ticket Text");
		Ticket updatedTicket = ticketRepository.save(savedTicket);

		// VERIFY UPDATE
		Optional<Ticket> foundTicket = ticketRepository.findById(updatedTicket.getId());
		assertThat(foundTicket).isPresent();
		assertThat(foundTicket.get().getText()).isEqualTo("Updated Ticket Text");
	}

	@Test
	void testDeleteTicket() {
		// CREATE
		Ticket savedTicket = ticketRepository.save(testTicket);

		// DELETE
		ticketRepository.delete(savedTicket);

		// VERIFY DELETE
		Optional<Ticket> deletedTicket = ticketRepository.findById(savedTicket.getId());
		assertThat(deletedTicket).isEmpty();
	}
}