package library;

import models.SearchBookDto;
import users.Customer;

import java.util.List;

public interface CustomerInterface {
    public List<Book> searchBooksFromLibrary(SearchBookDto searchBookDto);

    public Ticket issueBookToMe(Customer customer, List<Book> bookList);

    public Ticket returnBooks(Ticket ticket);
}
