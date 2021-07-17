package openUni.Persistence.Listeners;

import openUni.Books.BookLoan;
import openUni.Books.Events.BookBorrowedEvent;
import openUni.Events.IEventListener;
import openUni.Persistence.IRepository;

public class BookLoanListener implements IEventListener<BookBorrowedEvent>{
    
    private final IRepository<BookLoan> _bookLoanPersistence;
    
    public BookLoanListener(IRepository<BookLoan> bookLoanPersistence){
        _bookLoanPersistence = bookLoanPersistence;
    }

    @Override
    public void update(BookBorrowedEvent eventType) {
        BookLoan bookLoan = new BookLoan();
        bookLoan.setUserId(eventType.getUserId());
        bookLoan.setBookId(eventType.getBookId());
        
        _bookLoanPersistence.save(bookLoan);
    }
}
