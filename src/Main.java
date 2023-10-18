import java.util.*;

public class Main {
    public static void main(String[] args) {
        Book bookFirst = new Book(1, "451 Градус по Фаренгейту", BookStatus.RESERVED);
        Book bookSecond = new Book(2, "Библия", BookStatus.FREE);
        Book bookThird = new Book(3, "Чистый код", BookStatus.OVERDUE);
        Book bookFourth = new Book(4, "Как устроена экономика", BookStatus.RESERVED);
        Book bookFifth = new Book(5, "Убить пересмешника", BookStatus.OVERDUE);

        Student daniil = new Student(null, Set.of(bookThird, bookFourth));
        Student vera = new Student("Вера", Set.of(bookFifth, bookFirst));
        Student alex = new Student("Алекс", Set.of());
        Student tatiana = new Student("Татьяна", Set.of());

        List<Student> students = new ArrayList<>();
        students.add(daniil);
        students.add(vera);
        students.add(alex);
        students.add(tatiana);


        //есть студенты, найти тех, у которых имя длиной больше 5 символов
        //Получить имена студентов, у которых больше 5 символов

        List<String> result = students.stream()
                .map(Student::getName)    // student.getName() -  метод референс
                .filter(Objects::nonNull)   // name != null    :: метод референс
                .filter(name -> name.length() > 5)
                .toList();
//        System.out.println(result);

        //найти имена студентов, у которых больше одной книги

//        long count =  students.stream()
//                .filter(student -> student.getBooks() !=null)
//                .filter(student -> student.getBooks().size() > 1)
//                        .map(Student::getName)
//                                .count();
//        System.out.println(count);

        List<String> names = students.stream()
                .filter(student -> student.getBooks() != null)
                .filter(student -> student.getBooks().size() > 1)
                .map(Student::getName)
                .filter(Objects::nonNull)
                .toList();
//        System.out.println(names);


        // имена студентов, у которых больше одной книги и есть просроченные книги

        List<String> names1 = students.stream()
                .filter(student -> student.getBooks() != null)
                .filter(student -> student.getBooks().size() > 1)
                .filter(Student::checkOverdueBooks)
                .map(Student::getName)
                .filter(Objects::nonNull)
                .toList();
        System.out.println(names1);


        // найти людей, у кого просроченные книги


        students.stream()
                .filter(student -> {
                    Set<Book> books = student.getBooks();
                    // пройтись по книгам и найти просроченные
                    for (Book book : books) {
                        if (book.getStatus() == BookStatus.OVERDUE) {
                            return true;
                        }
                    }
                    return false;

                });

        // найти все просроченные книги, длина названия которых больше 8 букв

        List<Book> books = students.stream()
                .map(Student::getBooks)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .filter(book -> book.getStatus() == BookStatus.OVERDUE)
                .filter(book -> book.getName() != null)
                .filter(book -> book.getName().length() > 8)
                .toList();
        System.out.println(books);

        // найти все зарезервированные книги на людей на букву А

        List<Book> answer = students.stream()
                .filter(student -> student.getName() != null && student.getName().startsWith("A"))
                .map(Student::getBooks)
                .flatMap(Collection::stream)
                .filter(book -> book != null && book.getStatus() != null && book.getStatus() == BookStatus.RESERVED)
                .toList();
        System.out.println(answer);

        //Найти все книги, у которых id делится на 2

        List<Book> answer1 = students.stream()
                .map(Student::getBooks)
                .flatMap(Collection::stream)
                .filter(book -> book != null && book.getId() % 2 == 0)
                .toList();
        System.out.println(answer1);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.entrySet().stream()
                .filter(x -> x.getKey().startsWith("A"))
                .count();


    }


}