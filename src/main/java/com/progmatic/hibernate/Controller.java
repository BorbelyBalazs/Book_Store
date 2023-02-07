package com.progmatic.hibernate;

import com.progmatic.hibernate.model.*;
import jakarta.persistence.EmbeddedId;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class Controller {

    private HibernateContext hibCont = new HibernateContext();
    Scanner sc = new Scanner(System.in);


    public void init() {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        try {
            Author a1 = new Author("Lione Stanislav", LocalDate.of(1988, 6, 10));
            Author a2 = new Author("George Orwell", LocalDate.of(1903, 6, 25));
            Author a3 = new Author("Lev Tolsztoj", LocalDate.of(1828, 9, 9));
            Author a4 = new Author("Margaret Atwood", LocalDate.of(1939, 8, 24));
            Author a5 = new Author("Dosztojevszkij", LocalDate.of(1921, 11, 11));

            Book b1 = new Book("1234567890110", "Csillagom", Year.of(2022), a1, true);
            Book b2 = new Book("1234567890112", "1984", Year.of(1948), a2, true);
            Book b3 = new Book("1234567890118", "Állatfarm", Year.of(1945), a2, true);
            Book b4 = new Book("1234567890123", "Ivan Ilijics Halála", Year.of(1886), a3, true);
            Book b5 = new Book("1234567890125", "Anna Karenina", Year.of(1873), a3, true);
            Book b6 = new Book("1234567890129", "Háború és Béke", Year.of(1865), a3, true);
            Book b7 = new Book("1234567890134", "A Szolgálólány Meséje", Year.of(2017), a4, true);
            Book b8 = new Book("1234567890142", "Testamentumok", Year.of(2019), a4, true);
            Book b9 = new Book("1234567890148", "Bűn és Bűnhődés", Year.of(1866), a5, true);

            Store s1 = new Store("Líra", "1086 Budapest, Dankó u. 4", true);
            Store s2 = new Store("Alexandra", "1036 Budapest, Lajos u. 74-76.", true);
            Store s3 = new Store("Libri Aréna", "1087 Budapest Kerepesi út 9.", true);
            Store s4 = new Store("Libri Sugár", "1148 Budapest Örs Vezér tere 24.", true);
            Store s5 = new Store("Libri KÖKI", "1191 Budapest Vak Bottyán u. 75.", true);
            Store s6 = new Store("Bookline Astoria", "1072 Budapest, Rákóczi út 12.", true);

            List<Book> a1Books = new ArrayList<>(List.of(b1));
            List<Book> a2Books = new ArrayList<>(Arrays.asList(b2, b3));
            List<Book> a3Books = new ArrayList<>(Arrays.asList(b4, b5, b6));
            List<Book> a4Books = new ArrayList<>(Arrays.asList(b7, b8));
            List<Book> a5Books = new ArrayList<>(List.of(b9));

            a1.setBooks(a1Books);
            a2.setBooks(a2Books);
            a3.setBooks(a3Books);
            a4.setBooks(a4Books);
            a5.setBooks(a5Books);


//            List<Store> b1Stores = new ArrayList<>(Arrays.asList(s1,s3,s4,s6));
//            List<Store> b2Stores = new ArrayList<>(Arrays.asList(s1,s2,s3,s4,s5));
//            List<Store> b3Stores = new ArrayList<>(Arrays.asList(s2,s3,s5,s6));
//            List<Store> b4Stores = new ArrayList<>(Arrays.asList(s3,s5,s6));
//            List<Store> b5Stores = new ArrayList<>(Arrays.asList(s1,s3,s4));
//            List<Store> b6Stores = new ArrayList<>(Arrays.asList(s1,s3,s5,s6));
//            List<Store> b7Stores = new ArrayList<>(Arrays.asList(s1,s2,s3,s4,s5,s6));
//            List<Store> b8Stores = new ArrayList<>(Arrays.asList(s1,s2,s3,s4,s6));
//            List<Store> b9Stores = new ArrayList<>(Arrays.asList(s2,s3,s5,s6));
//
//            List<Book> books = new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5,b6,b7,b8,b9));
//
//            List<List<Store>> listOfStoresPerBook = new ArrayList<>(Arrays.asList(
//                    b1Stores, b2Stores, b3Stores, b4Stores, b5Stores, b6Stores, b7Stores, b8Stores, b9Stores
//            ));
//
//            for(int i = 0; i < listOfStoresPerBook.size(); i++) {
//                List<BookConnStore> bcs = new ArrayList<>();
//                for(var j: listOfStoresPerBook.get(i)) {
//                    BookConnStore tempBcs = new BookConnStore();
//                    tempBcs.setStore(j);
//                    tempBcs.setBook(books.get(i));
//                    tempBcs.setQuantity((int) (Math.random() * 21));
//
//
//                    bcs.add(tempBcs);
//                }
//                books.get(i).setBcs(bcs);
//            }


            List<Store> stores = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5, s6));

            List<Book> s1Books = new ArrayList<>(Arrays.asList(b1, b2, b5, b6, b7, b8));
            List<Book> s2Books = new ArrayList<>(Arrays.asList(b2, b3, b7, b8, b9));
            List<Book> s3Books = new ArrayList<>(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9));
            List<Book> s4Books = new ArrayList<>(Arrays.asList(b1, b2, b5, b7, b8));
            List<Book> s5Books = new ArrayList<>(Arrays.asList(b2, b3, b4, b6, b7, b9));
            List<Book> s6Books = new ArrayList<>(Arrays.asList(b1, b3, b4, b6, b7, b8, b9));

            List<List<Book>> listOfBooksInStores = new ArrayList<>(Arrays.asList(
                    s1Books, s2Books, s3Books, s4Books, s5Books, s6Books));

            for (int i = 0; i < listOfBooksInStores.size(); i++) {
                List<BookConnStore> bcs = new ArrayList<>();
                for (var b : listOfBooksInStores.get(i)) {
                    BookConnStore tempBcs = new BookConnStore();
                    tempBcs.setBook(b);
                    tempBcs.setStore(stores.get(i));
                    tempBcs.setQuantity((int) (Math.random() * 21));

                    bcs.add(tempBcs);
                }
                stores.get(i).setBcs(bcs);
            }

            s.persist(a1);
            s.persist(a2);
            s.persist(a3);
            s.persist(a4);
            s.persist(a5);

            s.persist(b1);
            s.persist(b2);
            s.persist(b3);
            s.persist(b4);
            s.persist(b5);
            s.persist(b6);
            s.persist(b7);
            s.persist(b8);
            s.persist(b9);

            s.persist(s1);
            s.persist(s2);
            s.persist(s3);
            s.persist(s4);
            s.persist(s5);
            s.persist(s6);

            tx.commit();

        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        }
    }

    public void newBook(String title, String isbn, Year year, String authorOfBook) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        try {
            SelectionQuery<Author> q = s.createSelectionQuery("SELECT a FROM Author a", Author.class);
            List<Author> authors = new ArrayList<>(q.list());
            Author author = new Author();

            for (Author a : authors) {
                if (a.getName().equals(authorOfBook)) {
                    author = a;
                }
            }

            Book newBook = new Book(isbn, title, year, author, true);

            s.persist(newBook);

            tx.commit();


        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        }
    }

    public void newAuthor(String name, LocalDate dob) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        try {
            Author newAuthor = new Author(name, dob);

            s.persist(newAuthor);

            tx.commit();


        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        }
    }

    public boolean isAuthorInTable(String authorOfBook) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        SelectionQuery<Author> q = s.createSelectionQuery("SELECT a FROM Author a", Author.class);
        List<Author> authors = new ArrayList<>(q.list());


        for (Author a : authors) {
            if (authorOfBook.contains(a.getName())) {
                tx.commit();
                return true;
            }
        }
        tx.commit();
        return false;
    }

    public void modifyBookTitle(long id, String newTitle) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        boolean bb = true;

        SelectionQuery<Book> q = s.createSelectionQuery("SELECT b FROM Book b", Book.class);
        List<Book> books = new ArrayList<>(q.list());
        Book book;

        for (Book b : books) {
            if (id == b.getBook_id()) {
                book = b;
                book.setTitle(newTitle);
                s.persist(book);
                bb = false;
                break;
            }
        }
        if (bb) {
            System.out.println("Ilyen azonosítójú könyv nincs az adatbázisban, kérlek válassz mást");
        }

        tx.commit();
    }

    public void modifyBookISBN(long id, String newISBN) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        boolean bb = true;

        SelectionQuery<Book> q = s.createSelectionQuery("SELECT b FROM Book b", Book.class);
        List<Book> books = new ArrayList<>(q.list());
        Book book;

        for (Book b : books) {
            if (id == b.getBook_id()) {
                book = b;
                book.setIsbn(newISBN);
                s.persist(book);
                bb = false;
                break;
            }
        }
        if (bb) {
            System.out.println("Ilyen azonosítójú könyv nincs az adatbázisban, kérlek válassz mást");
        }

        tx.commit();
    }

    public void modifyBookYear(long id, Year newYear) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        boolean bb = true;

        SelectionQuery<Book> q = s.createSelectionQuery("SELECT b FROM Book b", Book.class);
        List<Book> books = new ArrayList<>(q.list());
        Book book;

        for (Book b : books) {
            if (id == b.getBook_id()) {
                book = b;
                book.setYear(newYear);
                s.persist(book);
                bb = false;
                break;
            }
        }
        if (bb) {
            System.out.println("Ilyen azonosítójú könyv nincs az adatbázisban, kérlek válassz mást");
        }

        tx.commit();
    }

    public void searchBookTitle(String searchedTitle, boolean isAv) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        SelectionQuery<Book> qB = s.createSelectionQuery("SELECT b FROM Book b", Book.class);
        List<Book> books = new ArrayList<>(qB.list());

        SelectionQuery<Store> qS = s.createSelectionQuery("SELECT s FROM Store s", Store.class);
        List<Store> stores = new ArrayList<>(qS.list());

        SelectionQuery<BookConnStore> qBcs = s.createSelectionQuery("SELECT bcs FROM BookConnStore bcs", BookConnStore.class);
        List<BookConnStore> bcss = new ArrayList<>(qBcs.list());


        boolean bool = true;

        for (Book b : books) {
            if (b.getTitle().contains(searchedTitle)) {
                if (isAv || b.isAvailable()) {
                    System.out.println(b);
                    System.out.println("Itt kapható:");
                    for (var i : bcss) {
                        if (i.getBook().getBook_id() == b.getBook_id()) {
                            for (Store st : stores) {
                                if (st.getStore_id() == i.getStore().getStore_id()) {
                                    System.out.println(st.getName() + ", " + i.getQuantity() + "db van belőle");
                                }
                            }
                        }
                    }
                    bool = false;
                }
            }
        }
        if (bool) {
            System.out.println("\nNincs ilyen könyv az adatbázisban");
        }

        tx.commit();
    }

    public void searchBookIsbn(String searchedIsbn) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        SelectionQuery<Book> qB = s.createSelectionQuery("SELECT b FROM Book b", Book.class);
        List<Book> books = new ArrayList<>(qB.list());

        SelectionQuery<Store> qS = s.createSelectionQuery("SELECT s FROM Store s", Store.class);
        List<Store> stores = new ArrayList<>(qS.list());

        SelectionQuery<BookConnStore> qBcs = s.createSelectionQuery("SELECT bcs FROM BookConnStore bcs", BookConnStore.class);
        List<BookConnStore> bcss = new ArrayList<>(qBcs.list());


        boolean bool = true;

        for (Book b : books) {
            if (b.getIsbn().equals(searchedIsbn)) {
                System.out.println(b);
                System.out.println("Itt kapható:");
                for (var i : bcss) {
                    if (i.getBook().getBook_id() == b.getBook_id()) {
                        for (Store st : stores) {
                            if (st.getStore_id() == i.getStore().getStore_id()) {
                                System.out.println(st.getName() + ", " + i.getQuantity() + "db van belőle");
                            }
                        }
                    }
                }

                bool = false;
            }
        }
        if (bool) {
            System.out.println("\nNincs ilyen könyv az adatbázisban");
        }

        tx.commit();
    }


    public void searchBookAuthor(String searchedAuthor, boolean isAv) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        SelectionQuery<Book> qB = s.createSelectionQuery("SELECT b FROM Book b", Book.class);
        List<Book> books = new ArrayList<>(qB.list());

        SelectionQuery<Store> qS = s.createSelectionQuery("SELECT s FROM Store s", Store.class);
        List<Store> stores = new ArrayList<>(qS.list());

        SelectionQuery<BookConnStore> qBcs = s.createSelectionQuery("SELECT bcs FROM BookConnStore bcs", BookConnStore.class);
        List<BookConnStore> bcss = new ArrayList<>(qBcs.list());

        SelectionQuery<Author> qA = s.createSelectionQuery("SELECT a FROM Author a", Author.class);
        List<Author> authors = new ArrayList<>(qA.list());

        boolean bool = true;

        for (Book b : books) {
            for (Author a : authors) {
                if (a.getName().contains(searchedAuthor) && b.getAuthor().getAuthor_id() == a.getAuthor_id()) {
                    if (isAv || b.isAvailable()) {
                        System.out.println(b);
                        System.out.println("Itt kapható:");
                        for (var i : bcss) {
                            if (i.getBook().getBook_id() == b.getBook_id()) {
                                for (Store st : stores) {
                                    if (st.getStore_id() == i.getStore().getStore_id()) {
                                        System.out.println(st.getName() + ", " + i.getQuantity() + "db van belőle");
                                    }
                                }
                            }
                        }
                        bool = false;
                    }
                }
            }
        }
        if (bool) {
            System.out.println("\nNincs ilyen könyv az adatbázisban");
        }

        tx.commit();
    }

    public void oopBook(String chosenAtt, boolean isTitle) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        SelectionQuery<Book> qB = s.createSelectionQuery("SELECT b FROM Book b", Book.class);
        List<Book> books = new ArrayList<>(qB.list());

        boolean bool = true;

        for (Book b : books) {
            if (isTitle) {
                if (b.getTitle().equals(chosenAtt)) {
                    b.setAvailable(false);
                    s.persist(b);
                    bool = false;
                }
            } else {
                if (b.getIsbn().equals(chosenAtt)) {
                    b.setAvailable(false);
                    s.persist(b);
                    bool = false;
                }
            }
        }
        if (bool) {
            System.out.println("\nIlyen könyv nincs az adatbázisban");
        }

        tx.commit();
    }

    public void modifyAuthorName(String newName, long id) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        Author a = s.find(Author.class, id);

        if (a == null) {
            System.out.println("\nIlyen azonosítójú szerző nincs az adatbázisban, kérlek válassz mást!");
        } else {
            a.setName(newName);
            s.persist(a);
        }

        tx.commit();
    }

    public void modifyAuthorDate(LocalDate newDate, long id) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        Author a = s.find(Author.class, id);

        if (a == null) {
            System.out.println("\nIlyen azonosítójú szerző nincs az adatbázisban, kérlek válassz mást!");
        } else {
            a.setDob(newDate);
            s.persist(a);
        }

        tx.commit();
    }

    public void deleteAuthor(int id) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        Author a = s.find(Author.class, id);

        if (a == null) {
            System.out.println("\nIlyen azonosítójú szerző nincs az adatbázisban, kérlek válassz mást!");
        } else {
            s.remove(a);
        }

        tx.commit();
    }

    public void lowQuantity() {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        SelectionQuery<BookConnStore> qBcs = s.createSelectionQuery("SELECT bcs FROM BookConnStore bcs", BookConnStore.class);
        List<BookConnStore> bcss = new ArrayList<>(qBcs.list());

        for (var i : bcss) {
            if (i.getQuantity() < 5) {
                Store st = s.find(Store.class, i.getStore().getStore_id());
                Book b = s.find(Book.class, i.getBook().getBook_id());
                System.out.println(st.getName() + " - " + b.getTitle());
            }
        }

        tx.commit();
    }

    public void newStore(String newStoreName, String newStoreAddress, Set<Long> ids) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        Store newStore = new Store(newStoreName, newStoreAddress, true);

        s.persist(newStore);
        s.flush();

        List<Book> booksIntoNewStore = new ArrayList<>();

        for (var i : ids) {
            booksIntoNewStore.add(s.find(Book.class, i));
        }

        for (var b : booksIntoNewStore) {
            BookConnStore bcs = new BookConnStore();
            bcs.setStore(newStore);
            bcs.setBook(b);
            bcs.setQuantity(10);

            s.persist(bcs);
        }
        tx.commit();
    }

    public void modifyStore(String newAtt, int id, boolean isName) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        Store store = s.find(Store.class, id);

        if (store == null) {
            System.out.println("\nIlyen azonosítójú bolt nincs az adatbázisban.");
        } else {
            if (isName) {
                store.setName(newAtt);
            } else {
                store.setAddress(newAtt);
            }
        }

        s.persist(store);

        tx.commit();
    }

    public boolean checkStore(long id) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        Store store = s.find(Store.class, id);

        tx.commit();
        return store == null;
    }

    public void terminateContractStore(long id) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        Store store = s.find(Store.class, id);
        store.setActive(false);
        s.persist(store);

        tx.commit();
    }

    public void mostBookDeliver() {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        SelectionQuery<BookConnStore> qBcs = s.createSelectionQuery("SELECT bcs FROM BookConnStore bcs", BookConnStore.class);
        List<BookConnStore> bcss = new ArrayList<>(qBcs.list());
        List<BookConnStore> lowQbcs = new ArrayList<>();

        int limitQ = 5;

        for (var i : bcss) {
            if (i.getQuantity() < limitQ) {
                lowQbcs.add(i);
            }
        }

        Map<Long, Integer> storeIdsLowQ = new HashMap<>();
        for (var i : lowQbcs) {
            long tempId = i.getStore().getStore_id();
            storeIdsLowQ.put(tempId, storeIdsLowQ.getOrDefault(tempId, (limitQ - i.getQuantity())) + (limitQ - i.getQuantity()));
        }

//        System.out.println(storeIdsLowQ);

        Map<Long, Integer> reverseStoreIdsLowQ = new LinkedHashMap<>();

        storeIdsLowQ.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseStoreIdsLowQ.put(x.getKey(), x.getValue()));

//        System.out.println(reverseStoreIdsLowQ);

        System.out.println("\nA 3 könyvesbolt, ahova a legtöbbet kell szállítani: ");
        int count = 0;
        for (Map.Entry<Long, Integer> entry : reverseStoreIdsLowQ.entrySet()) {
            if (entry.getValue() < 10 || count == 3) {
                break;
            }
            Store store = s.find(Store.class, entry.getKey());
            System.out.println(store.getName());
            count++;
        }

        tx.commit();

    }

    public void carryBooksToStores(Map<Long, Integer> bIdsAndQuant, long sId) {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        for (Map.Entry<Long, Integer> entry : bIdsAndQuant.entrySet()) {
            BookStoreQ bsq = new BookStoreQ(entry.getKey(), sId);
            BookConnStore bcs = s.find(BookConnStore.class, bsq);

            if (bcs == null) {
                bcs = new BookConnStore();
                bcs.setBook(s.find(Book.class, entry.getKey()));
                bcs.setStore(s.find(Store.class, sId));
                bcs.setQuantity(entry.getValue());

            } else {
                bcs.setQuantity(entry.getValue() + bcs.getQuantity());

            }
            s.persist(bcs);
        }
        tx.commit();
    }
}
