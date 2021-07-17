package openUni.ui.LoanBook;

import openUni.Books.Commands.BorrowBookCommand;

public class LoanBookPresenter {
    private final BorrowBookCommand _borrowBookCommand;
    
    public LoanBookPresenter(BorrowBookCommand borrowBookCommand) {
        _borrowBookCommand = borrowBookCommand;
    }
    
    public boolean borrowBook(int userId, int bookId){
        if(!_borrowBookCommand.canBorrowBook(userId))
            return false;
        return _borrowBookCommand.loanBook(userId, bookId);
    }
}
