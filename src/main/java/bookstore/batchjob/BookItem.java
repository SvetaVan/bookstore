package bookstore.batchjob;

import bookstore.entity.Book;


public class BookItem extends Book {
    private String itemAuthorId;
    private String itemGenreId;
    private String itemBookName;

    public BookItem(String itemAuthorId, String itemGenreId, String itemBookName) {
        this.itemAuthorId = itemAuthorId;
        this.itemGenreId = itemGenreId;
        this.itemBookName = itemBookName;
    }

    public String getItemAuthorId() {
        return itemAuthorId;
    }

    public String getItemGenreId() {
        return itemGenreId;
    }

    public String getItemBookName() {
        return itemBookName;
    }
}

