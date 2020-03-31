package models;


import java.util.HashSet;
import java.util.Set;


public class Book {

    public Integer id;
    public String title;
    public String author;

    public Book() {}

    Book(Integer id, String title, String author)
    {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    private static Set<Book> set;

    static {
        set = new HashSet<>();
        set.add(new Book(1,"Java", "Rafa"));
        set.add(new Book(2, "C++", "Roger"));
    }

    public static Set<Book> all_books()
    {
        return set;
    }

    public static void add(Book book)
    {
        set.add(book);
    }

    public static Book find_by_id(Integer id)
    {
        for(Book the_books : set)
        {
            if(id == the_books.id)
            {
                return the_books;
            }
        }
        return null;
    }

    public static void remove(Book book)
    {
        set.remove(book);
    }
}
