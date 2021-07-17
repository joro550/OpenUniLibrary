package openUni.Books;

import openUni.Persistence.PersistedObject;

public class BookLoan extends PersistedObject {
    public int _bookId;
    public int _userId;
    
    public int getBookId(){
        return _bookId;
    }
    
    public void setBookId(int bookId){
        _bookId = bookId;
    }
    
    public int getUserId(){
        return _userId;
    }
    
    public void setUserId(int userId){
        _userId = userId;
    }
}
