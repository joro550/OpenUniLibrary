package openUni.Books;

import openUni.Books.Events.BookBorrowedEvent;
import openUni.Events.EventPublisher;
import openUni.Persistence.PersistedObject;
import openUni.Users.User;

public class Book extends PersistedObject {
    private String _title;
    private String _author;
    private String _description;
    private int _quantity;
    
    public String getTitle(){
        return _title;
    }
    
    public void setTitle(String title){
        _title = title;
    }
    
    public String getAuthor(){
        return _author;
    }
    
    public void setAuthor(String author){
        _author = author;
    }
    
    public String getDescription(){
        return _description;
    }
    
    public void setDescription(String description){
        _description = description;
    }
    
    public int getQuantity(){
        return _quantity;
    }
    
    public void setQuantity(int quantity){
        _quantity = quantity;
    }
    
    public void loanBook(User user){
        EventPublisher.getInstance().notify(new BookBorrowedEvent(user, this));
    }
}
