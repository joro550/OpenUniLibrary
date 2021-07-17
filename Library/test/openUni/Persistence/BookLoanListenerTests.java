package openUni.Persistence;

import openUni.Books.Book;
import openUni.Books.BookLoan;
import openUni.Books.Events.BookBorrowedEvent;
import openUni.Events.EventManager;
import openUni.InMemoryRepository;
import openUni.Persistence.Listeners.BookLoanListener;
import openUni.Users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookLoanListenerTests {
    EventManager _manager;
    BookLoanListener _listener;
    InMemoryRepository<BookLoan> _persistence;
    
    @Before
    public void beforeTests(){
        _manager = new EventManager(BookBorrowedEvent.Name);
        _persistence = new InMemoryRepository<>();
        _listener = new BookLoanListener(_persistence);
        
        _manager.subscribe(BookBorrowedEvent.Name, _listener);
    }
    
    @Test
    public void saveBookLoanTest(){
        Book book = new Book();
        book.setId(1);
        
        User user = new User();
        user.setId(2);
        
        _manager.notify(new BookBorrowedEvent(user, book));
        
        BookLoan loanFromStore = _persistence.get(1);
        Assert.assertEquals(user.getId(), loanFromStore.getUserId());
        Assert.assertEquals(book.getId(), loanFromStore.getBookId());
    }
}
