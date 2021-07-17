package openUni.Books.Specifications;

import openUni.Books.Book;

public class BookIsInStockSpecification extends openUni.Specification {
    private final Book _book;
    
    public BookIsInStockSpecification(Book book){
        _book = book;
    }
    
    @Override
    public boolean execute() {
        return _book.getQuantity() > 0;
    }
}
