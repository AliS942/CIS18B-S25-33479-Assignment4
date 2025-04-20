// LibraryTest.java
import java.util.*;

public class LibraryTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryCollection library = new LibraryCollection();

        // Sample books
        library.addBook(new Book("1984", "George Orwell", "Dystopian"));
        library.addBook(new Book("Brave New World", "Aldous Huxley", "Dystopian"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        library.addBook(new Book("Harry Potter", "J.K. Rowling", "Fantasy"));

        System.out.println("Enter genre to browse:");
        String genre = scanner.nextLine();

        Iterator<Book> iterator = library.getGenreIterator(genre);
        System.out.println("Available books in " + genre + ":");
        while (iterator.hasNext()) {
            System.out.println("- " + iterator.next());
        }

        System.out.println("Enter book title to check out:");
        String titleToCheckout = scanner.nextLine();
        try {
            Book book = library.findBookByTitle(genre, titleToCheckout);
            book.checkout();
            System.out.println("You checked out: " + book);
        } catch (BookNotFoundException | BookNotAvailableException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Enter book title to return:");
        String titleToReturn = scanner.nextLine();
        try {
            Book book = library.findBookByTitle(genre, titleToReturn);
            book.returnBook();
            System.out.println("You returned: " + book);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
