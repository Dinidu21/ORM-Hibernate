# Hibernate Annotations

Hibernate provides a set of annotations that can be used to map Java classes to database tables and define relationships. Below is a list of the most commonly used Hibernate annotations:

## 1. **Basic Mapping Annotations**

### `@Entity`
- Specifies that a class is an **entity** that will be mapped to a database table.
- **Example:**
    ```java
    @Entity
    @Table(name = "students")
    public class Student {
        // fields and methods
    }
    ```

### `@Table`
- Defines the name of the table in the database to which the entity will be mapped.
- **Example:**
    ```java
    @Entity
    @Table(name = "students")
    public class Student {
        // fields and methods
    }
    ```

### `@Id`
- Specifies the primary key of the entity.
- **Example:**
    ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ```

### `@GeneratedValue`
- Specifies the generation strategy for the primary key.
    - `GenerationType.IDENTITY`: Auto-incrementing field.
    - `GenerationType.SEQUENCE`: Sequence-based strategy.
    - `GenerationType.TABLE`: Table-based strategy.
- **Example:**
    ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ```

### `@Column`
- Maps a field to a column in the database.
- You can specify column properties such as name, length, nullable, etc.
- **Example:**
    ```java
    @Column(name = "first_name", length = 50)
    private String firstName;
    ```

### `@Transient`
- Specifies that a field should not be persisted to the database.
- **Example:**
    ```java
    @Transient
    private String tempField;
    ```

### `@Lob`
- Marks a field as **Large Object (LOB)** to store large data such as `Clob` or `Blob`.
- **Example:**
    ```java
    @Lob
    private String description;
    ```

## 2. **Relationship Mapping Annotations**

### `@OneToOne`
- Specifies a **one-to-one** relationship between two entities.
- **Example:**
    ```java
    @OneToOne(mappedBy = "student")
    private Student student;
    ```

### `@OneToMany`
- Specifies a **one-to-many** relationship between two entities.
- **Example:**
    ```java
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Course> courses;
    ```

### `@ManyToOne`
- Specifies a **many-to-one** relationship between two entities.
- **Example:**
    ```java
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    ```

### `@ManyToMany`
- Specifies a **many-to-many** relationship between two entities.
- **Example:**
    ```java
    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
    ```

### `@JoinColumn`
- Defines a column for joining entities in a relationship.
- **Example:**
    ```java
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    ```

### `@JoinTable`
- Specifies the **join table** for a many-to-many relationship.
- **Example:**
    ```java
    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
    ```

## 3. **Additional Useful Annotations**

### `@Version`
- Used for **optimistic locking**. It ensures that a record is not updated simultaneously by multiple transactions.
- **Example:**
    ```java
    @Version
    private int version;
    ```

### `@ManyToOne(fetch = FetchType.LAZY)`
- Specifies that a **many-to-one** relationship will be loaded lazily.
- **Example:**
    ```java
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    ```

### `@Fetch`
- Defines fetching strategy (e.g., `FetchType.LAZY`, `FetchType.EAGER`).
- **Example:**
    ```java
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Course> courses;
    ```

### `@NamedQuery`
- Defines a named **JPQL query** that can be reused in the application.
- **Example:**
    ```java
    @NamedQuery(name = "Student.findByName", query = "FROM Student s WHERE s.name = :name")
    ```

### `@Query`
- Used with Spring Data repositories to define a custom query.
- **Example:**
    ```java
    @Query("SELECT s FROM Student s WHERE s.name = :name")
    List<Student> findByName(@Param("name") String name);
    ```

### `@EntityListeners`
- Specifies an entity listener that reacts to certain entity lifecycle events.
- **Example:**
    ```java
    @EntityListeners(AuditingEntityListener.class)
    public class Student {
        // entity code
    }
    ```

### `@Embeddable`
- Marks a class that can be embedded in another entity (used with `@Embedded`).
- **Example:**
    ```java
    @Embeddable
    public class Address {
        private String street;
        private String city;
    }
    ```

### `@Embedded`
- Embeds an `@Embeddable` class into an entity.
- **Example:**
    ```java
    @Embedded
    private Address address;
    ```

### `@Enumerated`
- Specifies how an enum type should be persisted (e.g., `ORDINAL`, `STRING`).
- **Example:**
    ```java
    @Enumerated(EnumType.STRING)
    private Status status;
    ```

### `@Basic`
- Defines basic mappings for properties.
- **Example:**
    ```java
    @Basic(optional = false)
    private String firstName;
    ```

---

## Summary

These annotations form the core of Hibernate's **ORM mapping** functionality. Here's a quick overview:

| Annotation            | Purpose                                       |
|-----------------------|-----------------------------------------------|
| `@Entity`             | Marks the class as a Hibernate entity         |
| `@Table`              | Defines the database table for the entity     |
| `@Id`                 | Marks the primary key field                  |
| `@GeneratedValue`     | Defines primary key generation strategy       |
| `@Column`             | Maps a field to a database column            |
| `@ManyToOne`          | Defines many-to-one relationship             |
| `@OneToMany`          | Defines one-to-many relationship             |
| `@JoinColumn`         | Defines the join column for relationships    |
| `@Lob`                | Defines large object fields                  |
| `@Version`            | Implements optimistic locking                |
| `@Embedded`           | Embeds an embeddable class into an entity    |

---

## Extended Hibernate Annotations


## 1. **Mapping and Relationship Annotations**

### `@ManyToMany(fetch = FetchType.LAZY)`
- Specifies a **many-to-many** relationship with a lazy fetching strategy.
- **Example:**
    ```java
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_course", 
        joinColumns = @JoinColumn(name = "student_id"), 
        inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
    ```

### `@OneToOne(fetch = FetchType.EAGER)`
- Specifies a **one-to-one** relationship with eager fetching.
- **Example:**
    ```java
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profile;
    ```

### `@ManyToOne(fetch = FetchType.LAZY)`
- Specifies a **many-to-one** relationship with lazy fetching.
- **Example:**
    ```java
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    ```

### `@Fetch`
- Allows fine control over the fetching strategy (e.g., `FetchMode.JOIN`, `FetchMode.SELECT`).
- **Example:**
    ```java
    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Course> courses;
    ```

### `@MapKeyColumn`
- Specifies the column to use as the key when using a `Map` collection.
- **Example:**
    ```java
    @ElementCollection
    @MapKeyColumn(name = "course_code")
    private Map<String, Course> courses;
    ```

### `@OrderBy`
- Specifies how elements of a collection should be ordered in the database.
- **Example:**
    ```java
    @OneToMany(mappedBy = "student")
    @OrderBy("course_name ASC")
    private Set<Course> courses;
    ```

## 2. **Entity Lifecycle Annotations**

### `@PrePersist`
- Marks a method to be executed before an entity is persisted.
- **Example:**
    ```java
    @PrePersist
    public void onCreate() {
        this.createdAt = new Date();
    }
    ```

### `@PreUpdate`
- Marks a method to be executed before an entity is updated.
- **Example:**
    ```java
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date();
    }
    ```

### `@PostPersist`
- Marks a method to be executed after an entity has been persisted.
- **Example:**
    ```java
    @PostPersist
    public void onPersist() {
        log.info("Entity persisted!");
    }
    ```

### `@PostRemove`
- Marks a method to be executed after an entity has been removed.
- **Example:**
    ```java
    @PostRemove
    public void onRemove() {
        log.info("Entity removed!");
    }
    ```

### `@PostLoad`
- Marks a method to be executed after an entity has been loaded from the database.
- **Example:**
    ```java
    @PostLoad
    public void onLoad() {
        this.lastAccessed = new Date();
    }
    ```

## 3. **Transaction and Locking Annotations**

### `@Transactional`
- Marks a method or class as being part of a transaction, commonly used in Spring with Hibernate.
- **Example:**
    ```java
    @Transactional
    public void updateStudent(Student student) {
        student.setName("Updated Name");
        session.update(student);
    }
    ```

### `@Lock`
- Specifies the lock type to be applied to an entity during a query execution.
- **Example:**
    ```java
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Student findStudentWithLock(int id) {
        return entityManager.find(Student.class, id);
    }
    ```

### `@OptimisticLock`
- Indicates an optimistic locking strategy by specifying a version column.
- **Example:**
    ```java
    @Version
    private int version;
    ```

## 4. **Advanced Configuration Annotations**

### `@DynamicUpdate`
- Tells Hibernate to generate SQL statements that update only the columns that have changed.
- **Example:**
    ```java
    @Entity
    @DynamicUpdate
    public class Employee {
        private String name;
        private String department;
    }
    ```

### `@DynamicInsert`
- Tells Hibernate to generate SQL statements that insert only the non-null fields of the entity.
- **Example:**
    ```java
    @Entity
    @DynamicInsert
    public class Employee {
        private String name;
        private String department;
    }
    ```

### `@SqlResultSetMapping`
- Maps SQL query results to an entity or DTO.
- **Example:**
    ```java
    @SqlResultSetMapping(
        name = "StudentResult",
        entities = @EntityResult(entityClass = Student.class)
    )
    public class Student {
        // entity code
    }
    ```

### `@SqlFragment`
- Used to include custom SQL in a Hibernate query.
- **Example:**
    ```java
    @Query(value = "SELECT * FROM Student WHERE name = :name", nativeQuery = true)
    @SqlFragment("WHERE age > 18")
    public List<Student> findAdultStudents(@Param("name") String name);
    ```

### `@QueryHint`
- Used to add hints to Hibernate queries.
- **Example:**
    ```java
    @Query("FROM Student s WHERE s.name = :name")
    @QueryHint(name = "org.hibernate.cacheable", value = "true")
    public List<Student> findByName(@Param("name") String name);
    ```

## 5. **Indexing and Performance Annotations**

### `@Index`
- Specifies an index on a column or a set of columns in the database.
- **Example:**
    ```java
    @Entity
    @Table(indexes = @Index(name = "idx_student_name", columnList = "name"))
    public class Student {
        @Id
        private int id;
        private String name;
    }
    ```

### `@NaturalId`
- Marks a field as the natural identifier of an entity, typically used for non-surrogate identifiers (e.g., `email`).
- **Example:**
    ```java
    @NaturalId
    private String email;
    ```

### `@Cacheable`
- Marks an entity to be cacheable in Hibernate’s second-level cache.
- **Example:**
    ```java
    @Cacheable
    @Entity
    public class Product {
        @Id
        private int id;
        private String name;
    }
    ```

### `@Cache`
- Defines a caching strategy for an entity.
- **Example:**
    ```java
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @Entity
    public class Product {
        @Id
        private int id;
        private String name;
    }
    ```

### `@SecondLevelCache`
- Specifies that the second-level cache should be used for the entity.
- **Example:**
    ```java
    @SecondLevelCache
    @Entity
    public class Employee {
        @Id
        private int id;
        private String name;
    }
    ```

## 6. **Custom Hibernate Annotations**

### `@ColumnTransformer`
- Allows transformation of column values using SQL expressions.
- **Example:**
    ```java
    @ColumnTransformer(
        read = "UPPER(name)",
        write = "LOWER(?)"
    )
    private String name;
    ```

### `@Formula`
- Allows you to define a formula for a field that is not directly mapped to a database column, but is calculated.
- **Example:**
    ```java
    @Formula("salary * 0.1")
    private double bonus;
    ```

---

## Summary

These advanced annotations provide you with more control over how Hibernate works with your entities, optimizes performance, handles relationships, and integrates with the rest of your application. By using these annotations effectively, you can optimize your application’s data access layer and take full advantage of Hibernate's capabilities.

| Annotation              | Purpose                                   |
|-------------------------|-------------------------------------------|
| `@DynamicUpdate`         | Generates SQL that updates only changed fields. |
| `@DynamicInsert`         | Generates SQL that inserts only non-null fields. |
| `@Lock`                  | Specifies lock mode for queries.           |
| `@OptimisticLock`        | Defines optimistic locking with a version column. |
| `@SqlResultSetMapping`   | Maps SQL query results to entities or DTOs. |
| `@Cacheable`             | Marks an entity as cacheable in the second-level cache. |
| `@NaturalId`             | Marks a field as a natural identifier.    |
| `@ColumnTransformer`     | Applies SQL transformation to a column value. |
| `@Formula`               | Defines a computed field.                 |
| `@ManyToMany(fetch = FetchType.LAZY)` | Specifies a lazy many-to-many relationship. |

---

