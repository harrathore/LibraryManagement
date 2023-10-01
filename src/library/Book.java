package library;

import enums.BOOK_AVAILABILITY_STATUS_ENUM;
import enums.BOOK_CATEGORY_ENUM;

public class Book {
    private String bookId;
    private String bookName;
    private String bookDescription;
    private String author;
    private BOOK_CATEGORY_ENUM bookCategory;
    private BOOK_AVAILABILITY_STATUS_ENUM bookAvailabilityStatus;

    public Book(String bookName, String bookDescription, String author, BOOK_CATEGORY_ENUM bookCategory) {
        this.bookId = "book.id." + String.valueOf(Math.random());
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.author = author;
        this.bookCategory = bookCategory;
        this.bookAvailabilityStatus = BOOK_AVAILABILITY_STATUS_ENUM.AVAILABLE;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BOOK_CATEGORY_ENUM getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BOOK_CATEGORY_ENUM bookCategory) {
        this.bookCategory = bookCategory;
    }

    public BOOK_AVAILABILITY_STATUS_ENUM getBookAvailabilityStatus() {
        return bookAvailabilityStatus;
    }

    public void setBookAvailabilityStatus(BOOK_AVAILABILITY_STATUS_ENUM bookAvailabilityStatus) {
        this.bookAvailabilityStatus = bookAvailabilityStatus;
    }
}
