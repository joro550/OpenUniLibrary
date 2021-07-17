package openUni.Users.Specifications;

import java.util.List;
import openUni.Books.BookLoan;
import openUni.Persistence.IRepository;
import openUni.Users.User;

public class BookBorrowLimitSpecification extends openUni.Specification {
    private final User _user;
    private final IRepository<BookLoan> _bookLoanRepository;
    
    private final int MAX_BORROWED_BOOKS  = 3;

    public BookBorrowLimitSpecification(IRepository<BookLoan> _bookLoanRepository, User user) {
        this._bookLoanRepository = _bookLoanRepository;
        this._user = user;
    }

    @Override
    public boolean execute() {
        List<BookLoan> loans = 
                _bookLoanRepository.filter((loan) -> loan.getUserId() == _user.getId());
        return loans.size() < MAX_BORROWED_BOOKS;
    }
}
