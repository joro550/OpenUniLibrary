package openUni.LoanBook;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;
import openUni.Books.Book;
import openUni.Books.BookLoan;
import openUni.Books.Commands.BorrowBookCommand;
import openUni.Books.Events.BookBorrowedEvent;
import openUni.Events.EventPublisher;
import openUni.FakeEventListener;
import openUni.Users.User;
import openUni.InMemoryRepository;
import openUni.ui.LoanBook.LoanBookPresenter;
import org.junit.Assert;

public class LoanBookTests {
    LoanBookPresenter _loanBookPresenter;
    BorrowBookCommand _borrowBookCommand;
    InMemoryRepository<User> _userRepository;
    InMemoryRepository<Book> _bookRepository;
    FakeEventListener<BookBorrowedEvent> _listener;
    InMemoryRepository<BookLoan> _bookLoanRepository;
    
    private final int USER_DOES_NOT_EXIST_ID = -1;
    private final int BOOK_DOES_NOT_EXIST_ID = -1;
    private static final int MAX_BORROWED_BOOKS = 3;

    @Before
    public void setUp(){
        _listener = new FakeEventListener<>();
        _userRepository = new InMemoryRepository<>();
        _bookRepository = new InMemoryRepository<>();
        _bookLoanRepository = new InMemoryRepository<>();
        _borrowBookCommand = new BorrowBookCommand(_userRepository, _bookRepository, 
                _bookLoanRepository);
        
        EventPublisher.getInstance().subscribe(BookBorrowedEvent.Name, _listener);
        _loanBookPresenter = new LoanBookPresenter(_borrowBookCommand);
    }
    
    @Test 
    public void whenUserDoesntExist_BookCanNotBeLoaned(){
        int bookIdentifier = _bookRepository.save(new Book());
        boolean canBorrowBook = _loanBookPresenter.borrowBook(USER_DOES_NOT_EXIST_ID, bookIdentifier);
        
        Assert.assertFalse(canBorrowBook);
    }
    
    @Test
    public void whenBookDoesntExist_BookCanNotBeLoaned(){
        int userIdentifier = _userRepository.save(new User());
        boolean canBorrowBook = _loanBookPresenter.borrowBook(userIdentifier, BOOK_DOES_NOT_EXIST_ID);
        
        Assert.assertFalse(canBorrowBook);
    }
    
    @Test
    public void whenUserHasMaxBorrowedBooks_ThenBookCantBeLoaned(){
        int userIdentifier = _userRepository.save(new User());
        int bookIdentifier = _bookRepository.save(createBook(1));
        
        for(int i = 0; i < MAX_BORROWED_BOOKS; i ++){
            BookLoan bookLoan = new BookLoan();
            bookLoan.setBookId(bookIdentifier);
            bookLoan.setUserId(userIdentifier);
            _bookLoanRepository.save(bookLoan);
        }
        
        boolean canBorrowBook = _loanBookPresenter.borrowBook(userIdentifier, bookIdentifier);
        Assert.assertFalse(canBorrowBook);
    }
    
    @Test
    public void whenBookIsOutOfStock_ThenBookCantBeLoaned(){
        int userIdentifier = _userRepository.save(new User());
        int bookIdentifier = _bookRepository.save(createBook(0));
        
        boolean canBorrowBook = _loanBookPresenter.borrowBook(userIdentifier, bookIdentifier);
        Assert.assertFalse(canBorrowBook);
    }
    
    @Test
    public void whenUserCanBorrowBook_ThenBookBorrowsEventIsPublished(){
        int userIdentifier = _userRepository.save(new User());
        int bookIdentifier = _bookRepository.save(createBook(1));
        
        boolean canBorrowBook = _loanBookPresenter.borrowBook(userIdentifier, bookIdentifier);
        ArrayList<BookBorrowedEvent> eventsCaptured = _listener.getEventsCaptured();
        
        Assert.assertTrue(canBorrowBook);
        Assert.assertNotNull(eventsCaptured.get(0));
    }
    
    public Book createBook(int quantity){
        Book book =  new Book();
        book.setQuantity(quantity);
        return book;
    }
}
