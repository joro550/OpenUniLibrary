package openUni.Books.Commands;

import openUni.Books.Book;
import openUni.Books.BookLoan;
import openUni.Books.Events.BookBorrowedEvent;
import openUni.Books.Specifications.BookIsInStockSpecification;
import openUni.Events.EventManager;
import openUni.Events.EventPublisher;
import openUni.Persistence.IRepository;
import openUni.Users.Specifications.BookBorrowLimitSpecification;
import openUni.Users.User;

public class BorrowBookCommand extends EventPublisher {
    IRepository<User> _userRepository;
    IRepository<Book> _bookRepository;
    IRepository<BookLoan> _bookLoanRepository;
    
    public BorrowBookCommand(IRepository<User> userRepo, 
            IRepository<Book> bookRepo, 
            IRepository<BookLoan> bookLoanRepo) {
        super(new EventManager(BookBorrowedEvent.Name));

        _userRepository = userRepo;
        _bookRepository = bookRepo;
        _bookLoanRepository = bookLoanRepo;
    }
    
    /**
     * Checks that user can borrow book
     * @param userId The users id 
     * @return true if user can borrow, false otherwise
     */
    public boolean canBorrowBook(int userId){
        User user = _userRepository.get(userId);
        
        // If user does not exist then we can't borrow the book
        if(user == null) 
            return false;
        
        /* 
            Create and run specificcation to check users book loans to
            know if user has less than maximum amount of loans
        */
        BookBorrowLimitSpecification borrowLimit = 
                new BookBorrowLimitSpecification(_bookLoanRepository, user);
        return borrowLimit.execute();
    }

    /**
     * When possible creates a book loan record that associates the user
     * and the book
     * @param userId The user who is loaning the book
     * @param bookId The book being loaned
     * @return false when book can't be borrowed
     */
    public boolean loanBook(int userId, int bookId) {
        User user = _userRepository.get(userId);
        Book book = _bookRepository.get(bookId);
        
        // If user or book does not exist then we can't borrow the book
        if(user == null || book == null) 
            return false;
        
        /*
            Create and run the specification to check whether the book is in 
            stock
        */
        BookIsInStockSpecification inStock = new BookIsInStockSpecification(book);
        if(!inStock.execute())
            return false;
        
        // Notify all subscribers of the book being borrowed by the user
        notify(BookBorrowedEvent.Name, new BookBorrowedEvent(user, book));
        return true;
    }
}
