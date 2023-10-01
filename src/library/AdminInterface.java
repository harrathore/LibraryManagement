package library;

import users.Customer;

public interface AdminInterface {
    public String addBookInLibrary(Book book);
    public String removeBookFromLibrary(Book book);
    public String notifyAllDue();
}
