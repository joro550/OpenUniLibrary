package openUni.Books;

import openUni.Books.Events.BookBorrowedEvent;
import openUni.Events.EventManager;
import openUni.Events.EventPublisher;

public class LibraryStock extends EventPublisher {
    public LibraryStock(){
        super(new EventManager(BookBorrowedEvent.Name));
    }
}
