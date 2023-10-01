package library;

import models.SearchBookRequestDto;
import models.Ticket;
import users.Customer;

import java.util.List;

public interface LibraryInterfaceForCustomer {
    public Boolean registerCustomerToLibrary(Customer customer);
    public Customer removeCustomerFromLibrary(Customer customer);

    public List<Book> searchBooksFromLibrary(SearchBookRequestDto searchBookRequestDto);

    public Ticket issueBookToMe(Customer customer, List<Book> bookList);

    public Ticket returnBooks(Ticket ticket);
}
