package openUni.Books.Events;

import openUni.Books.Book;
import openUni.Events.IEvent;
import openUni.Users.User;

public class BookBorrowedEvent implements IEvent {
    public static String Name = "BookBorrowedEvent";
    
    private final User _user;
    private final Book _book;
    private int _quantity;
    
    public BookBorrowedEvent(User user, Book book){
        _user = user;
        _book = book;
        _quantity = 1;
    }
    
    public int getUserId(){
        return _user.getId();
    }
    
    public User getUser(){
        return _user;
    }
    
    public int getBookId(){
        return _book.getId();
    }
    
    public Book getBook(){
        return _book;
    }
    
    public int getQuantity(){
        return _quantity;
    }
    
    public void setQuantity(int quantity){
        _quantity = quantity;
    }

    @Override
    public String getName() {
        return Name;
    }
}
