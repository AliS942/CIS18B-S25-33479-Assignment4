// LibraryCollection.java
import java.util.*;

public class LibraryCollection implements Iterable<Book> {
    private Map<String, List<Book>> genreMap;

    public LibraryCollection() {
        genreMap = new HashMap<>();
    }

    public void addBook(Book book) {
        genreMap.computeIfAbsent(book.getGenre(), k -> new ArrayList<>()).add(book);
    }

    public Iterator<Book> getGenreIterator(String genre) {
        List<Book> books = genreMap.getOrDefault(genre, new ArrayList<>());
        return books.stream().filter(Book::isAvailable).iterator();
    }

    public Book findBookByTitle(String genre, String title) throws BookNotFoundException {
        List<Book> books = genreMap.getOrDefault(genre, new ArrayList<>());
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        throw new BookNotFoundException("Book not found in the selected genre.");
    }

    @Override
    public Iterator<Book> iterator() {
        List<Book> allAvailable = new ArrayList<>();
        for (List<Book> books : genreMap.values()) {
            for (Book book : books) {
                if (book.isAvailable()) {
                    allAvailable.add(book);
                }
            }
        }
        return allAvailable.iterator();
    }
}