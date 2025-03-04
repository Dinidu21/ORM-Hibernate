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

/*        Student student = new Student();
        student.setId(1);
        student.setName("Dinidu");
        student.setAge(23);

        Transaction tx = session.beginTransaction();
        session.persist(student);
        tx.commit();*/

//        Student s1 = session.byId(Student.class).load(1); // Eager loading .get(), .find()
        Student s1 = session.byId(Student.class).getReference(1); // Lazy loading

        session.close();
        factory.close();
    }
}
