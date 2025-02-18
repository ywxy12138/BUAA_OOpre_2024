import java.util.ArrayList;

public class Bookshelf {
    private int id;
    private ArrayList<Book> books;

    public Bookshelf(int id) {
        this.id = id;
        this.books = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    } //serve for clone

    public void addBook(Book book) {
        books.add(book);
    }

    public Bookshelf cloneBookshelf() {
        Bookshelf clone = new Bookshelf(id);
        for (Book book : books) {
            Book cloneBook = book.clone();
            clone.addBook(cloneBook);
        }
        return clone;
    }

    public void filter(int num) {
        books.removeIf(book -> book.getScore() < num);
    } //new way to filter

    public void join(Bookshelf bookShelf) {
        for (Book book : bookShelf.books) {
            boolean hasBook = false;
            for (Book book1 : books) {
                if (book1.equals(book)) {
                    hasBook = true;
                    break;
                }
            }
            if (!hasBook) {
                books.add(book);
            }
        }
    }

    public void addMagic(String magic) {
        for (Book book : books) {
            book.addMagic(magic);
        }
    }

    public void subMagic(int a, int b) {
        for (Book book : books) {
            book.subMagic(a, b);
        }
    }

    public int getNum1() {
        return books.size();
    }

    public int getNum2(String s) {
        int res = 0;
        for (Book book : books) {
            if (book.contains(s)) {
                res++;
            }
        }
        return res;
    }
}
