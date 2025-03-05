import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration()
                .addAnnotatedClass(entity.Student.class)
                .configure();

        Session session;
        SessionFactory factory = config.buildSessionFactory();
        session = factory.openSession();

        Transaction tx = session.beginTransaction();
//        session.merge(student); // when we use merge if data not exist then will insert and update

//        Student student = session.find(Student.class, 2);
//        session.remove(student);

        Student s1 = new Student();
        s1.setId(1);
        s1.setName("John");
        s1.setAge(22);
        session.persist(s1);
        tx.commit();

//        Student s1 = session.byId(Student.class).load(1); // Eager loading .get(), .find()
//        Student s1 = session.byId(Student.class).getReference(1); // Lazy loading

        session.close();
        factory.close();
    }
}
