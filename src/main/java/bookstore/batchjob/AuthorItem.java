package bookstore.batchjob;

import bookstore.entity.Book;

public class AuthorItem extends Book {

    private String authorId;
    private String authorName;

    public AuthorItem(String authorId, String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }
}
