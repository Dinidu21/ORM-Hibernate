import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.addAnnotatedClass(entity.Student.class);
        config.configure();

        Student student = new Student();
        student.setId(1);
        student.setName("Dinidu");
        student.setAge(23);

        Session session;
        SessionFactory factory = config.buildSessionFactory();
        session = factory.openSession();


        Transaction tx = session.beginTransaction();
        session.persist(student);
        tx.commit();
    }
}
