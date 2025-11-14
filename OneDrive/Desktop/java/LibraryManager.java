import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {  
    private String title;
    private String author;
    private boolean isAvailable;  

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book: " + title + " by " + author + " (Available: " + (isAvailable ? "Yes" : "No") + ")";
    }
}

public class LibraryManager {
    private static List<Book> library = new ArrayList<>();  // Our collection of books.
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Threw in some starter books to test with.
        library.add(new Book("Effective Java", "Joshua Bloch"));
        library.add(new Book("Clean Code", "Robert C. Martin"));

        System.out.println("Welcome to my super basic Library Manager! Let's get started.");

        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Add a new book");
            System.out.println("2. View all books");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit this thing");
            System.out.print("Pick an option (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    System.out.println("Thanks for using the library! Catch you later.");
                    scanner.close(); 
                    return;
                default:
                    System.out.println("Whoops, that's not a valid choice. Try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("What's the title? ");
        String title = scanner.nextLine().trim();

        System.out.print("And the author? ");
        String author = scanner.nextLine().trim();
        boolean alreadyThere = false;
        for (Book b : library) {
            if (b.getTitle().equalsIgnoreCase(title) && b.getAuthor().equalsIgnoreCase(author)) {
                alreadyThere = true;
                break;
            }
        }

        if (alreadyThere) {
            System.out.println("Hey, that book is already in the library. Can't add it twice.");
            return;
        }

        library.add(new Book(title, author));
        System.out.println("Cool, added '" + title + "' to the collection!");
    }

    private static void viewBooks() {
        if (library.isEmpty()) {
            System.out.println("The library's empty right now. Add some books!");
            return;
        }

        System.out.println("\nHere's what's in the library:");
        for (int i = 0; i < library.size(); i++) {
            System.out.println((i + 1) + ". " + library.get(i));
        }
    }

    private static void borrowBook() {
        System.out.print("Which book title do you want to borrow? ");
        String title = scanner.nextLine().trim();

        for (Book book : library) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Success! You now have '" + title + "'. Enjoy reading!");
                return;
            }
        }
        System.out.println("Sorry, couldn't find that book or it's already checked out.");
    }

    private static void returnBook() {
        System.out.print("Which book are you returning (title)? ");
        String title = scanner.nextLine().trim();

        for (Book book : library) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("Thanks for returning '" + title + "'. It's back on the shelf!");
                return;
            }
        }
        System.out.println("That book isn't checked out right now, or doesn't exist.");
    }
}