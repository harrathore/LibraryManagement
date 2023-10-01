package models;

import enums.TICKET_STATUS_ENUM;
import library.Book;
import users.Customer;

import java.util.List;

public class Ticket {
    private String ticketId;
    private Customer customer;
    private Long issuedOn;
    private Long closedOn;
    private List<Book> booksIssued;
    private TICKET_STATUS_ENUM ticketStatus;
    private Integer billAmount;
    private Integer fineAmount; //(If any)

    public Ticket(Customer customer, List<Book> booksIssued) {
        this.ticketId = "ticket.id." + String.valueOf(Math.random());
        this.customer = customer;
        this.issuedOn = 0L;   //current time
        this.booksIssued = booksIssued;
        this.closedOn = 0L;
        this.ticketStatus = TICKET_STATUS_ENUM.ISSUED;
        this.billAmount = 0;
        this.fineAmount = 0;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(Long issuedOn) {
        this.issuedOn = issuedOn;
    }

    public Long getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(Long closedOn) {
        this.closedOn = closedOn;
    }

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public TICKET_STATUS_ENUM getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TICKET_STATUS_ENUM ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Integer getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Integer billAmount) {
        this.billAmount = billAmount;
    }

    public Integer getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(Integer fineAmount) {
        this.fineAmount = fineAmount;
    }
}
