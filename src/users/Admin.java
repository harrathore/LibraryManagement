package users;

import enums.BOOK_CATEGORY_ENUM;
import library.AdminInterface;
import library.Book;
import library.Library;

public class Admin extends User{
    private AdminInterface adminInterface;
    public Admin(String userName) {
        super(userName);
        this.adminInterface = Library.getLibrary();
    }

    public String createAndAddBooks(){
        Book book = new Book("Gita", "It has all problems solutions", "Krishna Bhagwan",
                BOOK_CATEGORY_ENUM.SELF_HELP);
        adminInterface.addBookInLibrary(book);
        return "Books created and added to library by me";
    }

    public void notifyPeople(){
        adminInterface.notifyAllDue();
    }
}
