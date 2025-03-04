import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration().configure();

        Student student = new Student();
        student.setId(1);
        student.setName("Dinidu");
        student.setAge(23);

        Session session;
        try (SessionFactory factory = config.buildSessionFactory()) {
            session = factory.openSession();
        }
        session.persist(student);


    }
}
