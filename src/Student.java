import java.util.Objects;
import java.util.Set;

public class Student {
    private String name;
    private Set<Book> books;


    public boolean checkOverdueBooks() {

        long count = books.stream()
                .filter(Objects::nonNull)
                .filter(book -> book.getStatus() == BookStatus.OVERDUE)
                .count();
        return count > 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", books=" + books +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Student(String name, Set<Book> books) {
        this.name = name;
        this.books = books;
    }
}
