package users;

import enums.BOOK_CATEGORY_ENUM;
import library.LibraryInterfaceForAdmin;
import library.Book;
import library.Library;

public class Admin extends User{
    private LibraryInterfaceForAdmin libraryInterfaceForAdmin;
    public Admin(String userName) {
        super(userName);
        this.libraryInterfaceForAdmin = Library.getLibrary();
    }

    public String createAndAddBooks(){
        Book book = new Book("Gita", "It has all problems solutions", "Krishna Bhagwan",
                BOOK_CATEGORY_ENUM.SELF_HELP);

        Book book2 = new Book("Gita", "It has all problems solutions with explanation", "Krishna Bhagwan",
                BOOK_CATEGORY_ENUM.SELF_HELP);

        Book book3 = new Book("Gita", "It has all problems solutions with explanation", "Krishna Bhagwan",
                BOOK_CATEGORY_ENUM.SELF_HELP);

        Book book4 = new Book("Gita", "It has all problems solutions with explanation", "Krishna Bhagwan",
                BOOK_CATEGORY_ENUM.SELF_HELP);

        Book book5 = new Book("Gita", "It has all problems solutions with explanation", "Krishna Bhagwan",
                BOOK_CATEGORY_ENUM.SELF_HELP);

        Book book6 = new Book("Gita", "It has all problems solutions with explanation", "Krishna Bhagwan",
                BOOK_CATEGORY_ENUM.SELF_HELP);

        libraryInterfaceForAdmin.addBookInLibrary(book);
        libraryInterfaceForAdmin.addBookInLibrary(book2);
        libraryInterfaceForAdmin.addBookInLibrary(book3);
        libraryInterfaceForAdmin.addBookInLibrary(book4);
        libraryInterfaceForAdmin.addBookInLibrary(book5);
        libraryInterfaceForAdmin.addBookInLibrary(book6);
        return "Books created and added to library by me";
    }

}
