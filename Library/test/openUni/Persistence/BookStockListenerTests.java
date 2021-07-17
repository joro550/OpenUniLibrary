package openUni.Persistence;

import openUni.Books.Book;
import openUni.Books.Events.BookBorrowedEvent;
import openUni.Events.EventManager;
import openUni.InMemoryRepository;
import openUni.Persistence.Listeners.BookStockListener;
import openUni.Users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookStockListenerTests {
    EventManager _manager;
    BookStockListener _listener;
    InMemoryRepository<Book> _persistence;
    
    @Before
    public void beforeTests(){
        _manager = new EventManager(BookBorrowedEvent.Name);
        _persistence = new InMemoryRepository<>();
        _listener = new BookStockListener(_persistence);
        
        _manager.subscribe(BookBorrowedEvent.Name, _listener);
    }
    
    @Test
    public void updateStockTest(){
        Book book = new Book();
        book.setId(1);
        book.setQuantity(100);
        _persistence.save(book);
        
        User user = new User();
        user.setId(2);
        
        BookBorrowedEvent borrowedEvent = new BookBorrowedEvent(user, book);
        _manager.notify(BookBorrowedEvent.Name, borrowedEvent);
        
        Book bookFromStore = _persistence.get(book.getId());
        Assert.assertEquals(99, bookFromStore.getQuantity() );
    }
    
    @Test
    public void updateStockWithQuantityTest(){
        Book book = new Book();
        book.setId(1);
        book.setQuantity(100);
        _persistence.save(book);
        
        User user = new User();
        user.setId(2);
        
        BookBorrowedEvent borrowedEvent = new BookBorrowedEvent(user, book);
        borrowedEvent.setQuantity(100);
        
        _manager.notify(BookBorrowedEvent.Name, borrowedEvent);
        
        Book bookFromStore = _persistence.get(book.getId());
        Assert.assertEquals(0, bookFromStore.getQuantity() );
    }
}
