package PJ.libmanagement;

import java.util.ArrayList;
import java.util.List;


class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Book borrowed: " + title);
        } else {
            System.out.println("Sorry, the book is currently unavailable.");
        }
    }

    public void returnBook() {
        isAvailable = true;
        System.out.println("Book returned: " + title);
    }
}

// 👤 Member class
class Member {
    private int memberId;
    private String name;
    private List<Book> borrowedBooks;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.borrowBook();
        } else {
            System.out.println("Book is not available for borrowing.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();
        } else {
            System.out.println("You haven't borrowed this book.");
        }
    }
}

// 👩‍💼 Librarian class
class Librarian {
    private int employeeId;
    private String name;

    public Librarian(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void removeBook(Library library, Book book) {
        library.removeBook(book);
        System.out.println("Book removed: " + book.getTitle());
    }
}

// 🏛️ Library class
class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Librarian> librarians;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        librarians = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void addLibrarian(Librarian librarian) { // ✅ FIXED method
        librarians.add(librarian);
    }

    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}


public class LibraryManagementSystem {
    public static void main(String[] args) {
       
        Library library = new Library();

        
        Librarian librarian = new Librarian(1, "Alice");
        library.addLibrarian(librarian);

        Book book1 = new Book(101, "Java Programming", "James Gosling");
        Book book2 = new Book(102, "Data Structures", "Robert Lafore");

        librarian.addBook(library, book1);
        librarian.addBook(library, book2);

     
        Member member1 = new Member(1, "John");
        Member member2 = new Member(2, "Jane");

        library.addMember(member1);
        library.addMember(member2);

        
        member1.borrowBook(book1);
        member2.borrowBook(book2);

       
        member1.returnBook(book1);
        member2.returnBook(book2);

        Book searchedBook = library.searchBook("Java Programming");
        if (searchedBook != null) {
            System.out.println("Book found: " + searchedBook.getTitle());
        } else {
            System.out.println("Book not found.");
        }
    }
}
