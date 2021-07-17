package openUni.Persistence.Listeners;

import openUni.Books.Book;
import openUni.Books.Events.BookBorrowedEvent;
import openUni.Events.IEventListener;
import openUni.Persistence.IRepository;

public class BookStockListener implements IEventListener<BookBorrowedEvent>{
    private final IRepository<Book> _bookPersistence;
    
    public BookStockListener(IRepository<Book> bookPersistence){
        _bookPersistence = bookPersistence;
    }

    @Override
    public void update(BookBorrowedEvent eventType) {
        Book book = _bookPersistence.get(eventType.getBookId());
        book.setQuantity(book.getQuantity() - eventType.getQuantity());
        
        _bookPersistence.update(book);
    }
    
}
