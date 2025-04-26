# You should possess a thorough understanding of the following questions:

## What is JPA?

JPA (Java Persistence API) is a specification in Jakarta EE that defines a standard for managing relational data in Java applications. It provides a set of interfaces and annotations to map Java objects (entities) to database tables and handle persistence without dealing directly with SQL. JPA defines how objects should be persisted and retrieved but does not provide an implementation itself. It abstracts the database interaction and lets developers work with Java objects rather than relational tables directly[^1][^2][^3].

---

## What is Hibernate?

Hibernate is an open-source Object-Relational Mapping (ORM) framework for Java that implements the JPA specification. It maps Java classes to database tables and Java data types to SQL types, providing a framework to persist and query data. Hibernate offers additional features beyond JPA, such as caching, lazy loading, and advanced query capabilities. It simplifies database operations by managing the conversion between Java objects and database tables, reducing boilerplate code and improving productivity[^4][^5].

---

## Difference Between JPA and Hibernate

| Aspect | JPA | Hibernate |
| :-- | :-- | :-- |
| Nature | Specification (set of interfaces) | Implementation of JPA + extra features |
| Purpose | Defines ORM standards and APIs | Provides concrete ORM framework |
| Vendor Independence | Yes, allows switching implementations | Vendor-specific features (may cause lock-in) |
| Feature Set | Core ORM functionalities | Extended features like caching, custom SQL, advanced queries |
| Learning Curve | Simpler API | More complex due to additional features |
| Community Support | Standardized but depends on implementation | Large community and extensive resources |

Hibernate is a widely used JPA implementation but also offers features beyond JPA, making it richer but potentially more complex[^1][^5][^8].

---

## What is RDBMS?

RDBMS (Relational Database Management System) is a type of database management system that stores data in tables (rows and columns) and manages relationships between data points. It uses Structured Query Language (SQL) to access and manipulate data. RDBMS provides reliable, consistent, and efficient storage and retrieval of large amounts of structured data and supports operations like transactions, concurrency, and integrity constraints[^9].

---

## Difference Between RDBMS and Hibernate

| Aspect | RDBMS | Hibernate |
| :-- | :-- | :-- |
| Functionality | Database system for storing data | ORM framework for mapping Java objects to RDBMS tables |
| Role | Stores and manages data | Bridges Java application and RDBMS |
| Usage | Directly queried via SQL | Abstracts SQL, manages persistence in Java apps |
| Focus | Data storage and integrity | Object-relational mapping and persistence logic |
| Technology Layer | Backend database | Middleware ORM layer |

Hibernate works on top of an RDBMS to facilitate object persistence without direct SQL coding[^4][^9].

---

## Advantages and Disadvantages of Hibernate

**Advantages:**

- Reduces boilerplate JDBC code by managing object-table mapping.
- Supports caching for performance optimization.
- Provides database independence by abstracting SQL.
- Supports lazy loading and fetching strategies.
- Offers powerful query options (HQL, Criteria API).
- Large community and extensive documentation.

**Disadvantages:**

- Learning curve can be steep due to feature richness.
- Can introduce complexity in configuration and tuning.
- Potential vendor lock-in if Hibernate-specific features are used.
- Overhead of abstraction may impact performance if misused[^4][^5].

---

## Object/Entity State Diagram and Status Changes

In Hibernate/JPA, an entity can be in one of these states:

- **Transient**: The object is newly created and not associated with any database row.
- **Persistent**: The object is associated with a Hibernate session and synchronized with the database.
- **Detached**: The object was persistent but the session is closed or the object is no longer managed.
- **Removed**: The object is scheduled for deletion from the database.

**State Transitions:**

- `persist()` or `save()` moves transient to persistent.
- Closing session or `evict()` detaches an entity (persistent to detached).
- `merge()` reattaches a detached entity to a session.
- `remove()` marks persistent entity for deletion.

---

## What is ORM?

ORM (Object-Relational Mapping) is a programming technique that maps objects in an object-oriented language to relational database tables. It abstracts database interactions, allowing developers to work with objects rather than SQL queries and tables. ORM frameworks handle the conversion between object models and relational models, managing CRUD operations and relationships[^4].

---

## Implementation of Hibernate and Its Methods

Hibernate implementation involves:

- **Configuration**: Setting up `hibernate.cfg.xml` or programmatic configuration with database connection, dialect, and mapping details.
- **SessionFactory**: Created from configuration; a thread-safe factory for sessions.
- **Session**: Represents a single unit of work with the database; used to create, read, update, delete entities.
- **Transaction**: Manages atomic operations within a session.
- **CRUD Methods**:
    - `save()` / `persist()` – insert new entity
    - `get()` / `load()` – retrieve entity by primary key
    - `update()` – update detached entity
    - `delete()` – remove entity
    - `merge()` – merge detached entity state into current session

---

## Lazy and Eager Fetching

- **Lazy Fetching**: Associated data is loaded on-demand when accessed, not immediately. It improves performance by delaying database access until necessary.
- **Eager Fetching**: Associated data is loaded immediately with the parent entity, which can lead to unnecessary data retrieval and performance overhead.

---

## Methods Used in Eager and Lazy Fetching

Fetching strategy is usually defined via annotations or XML:

- `@OneToMany(fetch = FetchType.LAZY)` or `@ManyToOne(fetch = FetchType.EAGER)`
- Hibernate also supports `session.load()` (lazy) vs `session.get()` (eager) for entity retrieval.

---

## Two Types of Configuration in Hibernate’s SessionFactory

1. **XML Configuration**: Using `hibernate.cfg.xml` file to specify database connection, dialect, mappings, and other properties.
2. **Programmatic Configuration**: Using `Configuration` class in code to set properties and mappings dynamically.

---

## Three Types of Relationships Using “Inverse and Owning”

In Hibernate, relationships between entities can be:

- **One-to-One**
- **One-to-Many**
- **Many-to-Many**

In bidirectional relationships, one side is the **owning side** (responsible for the association management and foreign key), and the other is the **inverse side** (mapped by the owning side).

---

## On Which Side is the “mappedBy” Attribute Added?

The `mappedBy` attribute is added on the **inverse side** of the relationship to indicate that the relationship is controlled by the other (owning) side. It tells Hibernate that this side is not responsible for the association mapping[^5].

---

## Cascade Types in Hibernate and Their Use

Cascade types define how operations on one entity affect related entities:

- `CascadeType.PERSIST` – cascade save operations
- `CascadeType.MERGE` – cascade merge operations
- `CascadeType.REMOVE` – cascade delete operations
- `CascadeType.REFRESH` – cascade refresh operations
- `CascadeType.DETACH` – cascade detach operations
- `CascadeType.ALL` – apply all cascades

Used to propagate entity state changes to related entities automatically, simplifying code and ensuring consistency[^5].

---

## Query Types Used in Hibernate

- **HQL (Hibernate Query Language)**: Object-oriented query language similar to SQL but operates on entity objects and properties.
- **Native SQL**: Direct SQL queries executed on the database.
- **Criteria API**: Programmatic, type-safe query construction.
- **Named Queries**: Predefined HQL or SQL queries with names for reuse.

---

## Differences Between HQL and Native SQL

| Aspect | HQL | Native SQL |
| :-- | :-- | :-- |
| Query Language | Object-oriented (entity-based) | Database-specific SQL |
| Portability | Portable across databases | Database dependent |
| Result Type | Returns entities or properties | Returns raw database results |
| Use Case | Complex ORM queries | Complex queries or DB-specific features |


---

## First-Level and Second-Level Cache

- **First-Level Cache**: Session-level cache; each Hibernate session maintains its own cache of entities. It is mandatory and cannot be disabled. Improves performance within a session by avoiding repeated database hits.
- **Second-Level Cache**: SessionFactory-level cache shared across sessions. Optional and configurable. It caches entities across sessions to reduce database access and improve performance. Requires a cache provider like Ehcache or Infinispan.

---

## Importance of Password Encryption and Algorithms

Password encryption secures stored passwords against theft and misuse. Instead of storing plain text passwords, applications store hashed versions to protect user data.

**Common algorithms for secure password storage:**

- **BCrypt**: Adaptive hashing algorithm with salt, widely recommended.
- **PBKDF2**: Password-Based Key Derivation Function, uses salt and iterations.
- **SCrypt**: Memory-hard function to resist hardware attacks.
- **Argon2**: Winner of the Password Hashing Competition, designed for strong security.

Using salted hashes and slow hashing algorithms prevents brute force and rainbow table attacks, ensuring password confidentiality.

---

This comprehensive overview covers your requested topics based on the latest information and best practices in Java persistence and database management. If you need details on the object/entity state diagram visualization or code examples, please let me know.

<div style="text-align: center">⁂</div>

[^1]: https://www.infoworld.com/article/2259807/what-is-jpa-introduction-to-the-java-persistence-api.html

[^2]: https://en.wikipedia.org/wiki/Jakarta_Persistence

[^3]: https://www.tutorialspoint.com/jpa/jpa_introduction.htm

[^4]: https://www.theserverside.com/definition/Hibernate

[^5]: https://www.linkedin.com/pulse/hibernate-vs-jpa-spring-boot-detailed-comparison-kāshān-asim-vl1tf

[^6]: https://docs.oracle.com/html/E13981_01/undejbs003.htm

[^7]: https://en.wikipedia.org/wiki/Hibernation

[^8]: https://stackoverflow.com/questions/9881611/whats-the-difference-between-jpa-and-hibernate

[^9]: https://www.techtarget.com/searchdatamanagement/definition/RDBMS-relational-database-management-system

[^10]: https://www.w3schools.com/mysql/mysql_rdbms.asp

[^11]: https://www.quest.com/learn/what-is-a-relational-database.aspx

[^12]: https://stackoverflow.com/questions/58934013/difference-between-h2-vs-hibernate-and-mysql-in-general-sense

[^13]: https://talent500.com/blog/what-is-relational-database-rdms/

[^14]: https://www.cybersuccess.biz/hibernate-vs-jdbc/

[^15]: https://en.wikipedia.org/wiki/Relational_database

[^16]: https://www.reddit.com/r/java/comments/8xgq4h/frameworks_like_springhibernate_good_bad_in/

[^17]: https://www.java4s.com/hibernate/main-advantage-and-disadvantages-of-hibernates/

[^18]: https://dev.to/anna_golubkova/what-are-the-advantages-of-hibernate-over-other-orm-frameworks-nm7

[^19]: https://www.careerride.com/Hibernate-advantageous-over-JDBC.aspx

[^20]: https://www.shahucollegelatur.org.in/Department/Studymaterial/sci/it/BCA/SY/hbfrmwrk.pdf

[^21]: https://www.edureka.co/blog/what-is-hibernate-in-java/

[^22]: http://javainsimpleway.com/disadvantages-of-hibernate/

[^23]: https://waytoeasylearn.com/learn/hibernate-disadvantages/

[^24]: https://www.eversql.com/hibernate-orm-pros-cons-and-how-to-fix-performance-problems/

[^25]: https://vladmihalcea.com/a-beginners-guide-to-jpa-hibernate-entity-state-transitions/

[^26]: https://docs.jboss.org/hibernate/orm/3.5/reference/en/html/objectstate.html

[^27]: https://www.dineshonjava.com/hibernate/understanding-state-changes-of-object/

[^28]: https://stackoverflow.com/questions/161224/what-are-the-differences-between-the-different-saving-methods-in-hibernate

[^29]: https://howtodoinjava.com/hibernate/hibernate-entity-persistence-lifecycle-states/

[^30]: https://www.youtube.com/watch?v=EgVUsgtRqIs

[^31]: https://thorben-janssen.com/entity-lifecycle-model/

[^32]: https://en.wikipedia.org/wiki/Object–relational_mapping

[^33]: https://www.theserverside.com/definition/object-relational-mapping-ORM

[^34]: https://www.spiceworks.com/tech/data-management/articles/what-is-orm-a-comprehensive-guide-to-object-relational-mapping/

[^35]: https://docs.jboss.org/hibernate/orm/6.2/quickstart/html_single/

[^36]: https://www.altexsoft.com/blog/orm-object-relational-mapping/

[^37]: https://www.digitalocean.com/community/tutorials/hibernate-tutorial-for-beginners

[^38]: https://stackoverflow.com/questions/2192242/what-is-lazy-loading-in-hibernate

[^39]: https://docs.jboss.org/hibernate/orm/4.2/manual/en-US/html/ch20.html

[^40]: https://www.javaguides.net/2023/08/eager-and-lazy-loading-in-hibernate.html

[^41]: https://thorben-janssen.com/entity-mappings-introduction-jpa-fetchtypes/

[^42]: https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api

[^43]: https://backendhance.com/en/blog/2023/jpa-fetching-strategies/

[^44]: https://www.coderscampus.com/hibernate-eager-vs-lazy-fetch-type/

[^45]: https://docs.atlassian.com/hibernate2/2.1.8/reference/session-configuration.html

[^46]: https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/3-ways-to-build-a-Hibernate-SessionFactory-in-Java-by-example

[^47]: https://docs.jboss.org/hibernate/core/3.3/reference/en/html/session-configuration.html

[^48]: https://docs.jboss.org/hibernate/core/3.2/reference/en/html/session-configuration.html

[^49]: https://syntaxsavvy.dev/langs/java/tools/hibernate/setting_up_hibernate/hibernate_session_factory/

[^50]: https://docs.redhat.com/en/documentation/red_hat_jboss_web_server/3/html/hibernate_core_reference_guide/sect-your_first_hibernate_application

[^51]: https://www.digitalocean.com/community/tutorials/hibernate-sessionfactory

[^52]: https://symfonycasts.com/screencast/doctrine-relations/owning-vs-inverse

[^53]: https://stackoverflow.com/questions/60735909/decide-which-class-is-owning-side-in-hibernate

[^54]: https://www.doctrine-project.org/projects/doctrine-orm/en/3.3/reference/unitofwork-associations.html

[^55]: https://stackoverflow.com/questions/8962878/meaning-of-mappedby-attribute-in-an-annotation

[^56]: https://www.javacodegeeks.com/2013/04/jpa-determining-the-owning-side-of-a-relationship.html

[^57]: https://thorben-janssen.com/many-relationships-additional-properties/

[^58]: https://www.youtube.com/watch?v=DrmxYYC_hbo

[^59]: https://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html

[^60]: https://www.tutorialspoint.com/hibernate/hibernate_cascade_types.htm

[^61]: https://stackoverflow.com/questions/58054915/hibernate-when-should-i-use-cascade-all-and-when-should-i-specify-them-separate

[^62]: https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/

[^63]: https://www.javaguides.net/2018/11/guide-to-jpa-and-hibernate-cascade-types.html

[^64]: https://stackoverflow.com/questions/58054915/hibernate-when-should-i-use-cascade-all-and-when-should-i-specify-them-separate/58056589

[^65]: https://howtodoinjava.com/hibernate/hibernate-jpa-cascade-types/

[^66]: https://docs.jboss.org/hibernate/orm/6.3/javadocs/org/hibernate/annotations/CascadeType.html

[^67]: https://docs.jboss.org/hibernate/orm/3.2/api/org/hibernate/Query.html

[^68]: https://docs.redhat.com/en/documentation/red_hat_jboss_enterprise_application_platform/7.2/html/developing_hibernate_applications/hibernate_query_language

[^69]: https://stackoverflow.com/questions/8911894/hql-query-for-multiple-types-classes

[^70]: https://stackoverflow.com/questions/12187358/which-is-better-performance-in-hibernate-native-query-or-hql

[^71]: https://dev.to/yigi/hibernate-hql-vs-sql-36lo

[^72]: https://coderanch.com/t/218994/databases/syntax-differnece-hibernate-hql-sql

[^73]: https://www.baeldung.com/jpql-hql-criteria-query

[^74]: https://www.digitalocean.com/community/tutorials/hibernate-caching-first-level-cache

[^75]: https://vladmihalcea.com/jpa-hibernate-first-level-cache/

[^76]: https://discourse.hibernate.org/t/1st-level-cache-size/5525

[^77]: https://redisson.pro/glossary/hibernate-second-level-cache.html

[^78]: https://hazelcast.com/foundations/caching/hibernate-second-level-cache/

[^79]: https://hazelcast.com/use-cases/hibernate-second-level-cache/

[^80]: https://memgraph.com/blog/why-password-encryption-matters

[^81]: https://proton.me/blog/password-encryption

[^82]: https://teampassword.com/blog/what-is-password-encryption-and-how-much-is-enough

[^83]: https://senhasegura.com/post/encryption-for-cybersecurity

[^84]: https://appwrite.io/blog/post/password-hashing-algorithms

[^85]: https://nicoduj.github.io/CheatSheetSeries/cheatsheets/Password_Storage_Cheat_Sheet.html

[^86]: https://www.authgear.com/post/password-hashing-salting-function-and-algorithm-explained

[^87]: https://guptadeepak.com/secure-password-storage-best-practices-with-modern-hashing-algorithms/

[^88]: https://spring.io/projects/spring-data-jpa

[^89]: https://www.turing.com/kb/jpa-for-database-access

[^90]: https://dev.to/codegreen/what-is-jpa-explain-few-configurations-5flj

[^91]: https://en.wikipedia.org/wiki/Hibernate_(framework)

[^92]: https://stackademic.com/blog/understanding-the-key-differences-jpa-vs-hibernate-orm-in-java-applications-f7c56b980dad

[^93]: https://stackoverflow.com/questions/3500128/what-is-hibernate-in-simple-language

[^94]: https://www.interviewbit.com/blog/jpa-vs-hibernate/

[^95]: https://support.microsoft.com/en-us/windows/shut-down-sleep-or-hibernate-your-pc-2941d165-7d0a-a5e8-c5ad-8c972e8e6eff

[^96]: https://aglowiditsolutions.com/blog/jpa-vs-hibernate/

[^97]: https://dictionary.cambridge.org/dictionary/english/hibernate

[^98]: https://hackernoon.com/the-difference-between-jdbc-jpa-hibernate-and-spring-data-jpa

[^99]: https://www.howtogeek.com/102897/whats-the-difference-between-sleep-and-hibernate-in-windows/

[^100]: https://cloud.google.com/learn/what-is-a-relational-database

[^101]: https://www.oracle.com/sg/database/what-is-a-relational-database/

[^102]: https://www.ibm.com/think/topics/relational-databases

[^103]: https://www.theserverside.com/video/Hibernate-vs-JDBC-How-do-these-database-APIs-differ

[^104]: https://azure.microsoft.com/en-au/resources/cloud-computing-dictionary/what-is-a-relational-database

[^105]: https://www.linkedin.com/pulse/demystifying-database-access-java-jpa-hibernate-jdbc-ali-gb7uf

[^106]: https://hackernoon.com/the-difference-between-jdbc-jpa-hibernate-and-spring-data-jpa

[^107]: https://stackoverflow.com/questions/530215/hibernate-vs-jpa-vs-jdo-pros-and-cons-of-each

[^108]: https://stackoverflow.com/questions/14390275/what-are-the-disadvantages-of-using-hibernates-secondary-cache

[^109]: https://answers.microsoft.com/en-us/windows/forum/all/the-disadvantages-of-hibernating/8a14d25b-1410-46cb-a294-7216f67891b7

[^110]: https://knowledge2life.com/hibernate/limitations.html

[^111]: https://www.reddit.com/r/java/comments/53p253/how_hibernate_almost_ruined_my_career/

[^112]: https://www.baeldung.com/hibernate-entity-lifecycle

[^113]: https://stackoverflow.com/questions/35397046/what-state-is-hibernate-object-in-after-commit

[^114]: https://www.linkedin.com/posts/vladmihalcea_a-beginners-guide-to-entity-state-transitions-activity-7251498574616662016-8eZ8

[^115]: https://www.freecodecamp.org/news/what-is-an-orm-the-meaning-of-object-relational-mapping-database-tools/

[^116]: https://stackoverflow.com/questions/1279613/what-is-an-orm-how-does-it-work-and-how-should-i-use-one

[^117]: https://www.baeldung.com/cs/object-relational-mapping

[^118]: https://docs.jboss.org/hibernate/stable/orm/userguide/html_single/Hibernate_User_Guide.html

[^119]: https://docs.spring.io/spring-framework/reference/data-access/orm/hibernate.html

[^120]: https://hibernate.org/orm/

[^121]: https://stackoverflow.com/questions/72864441/spring-data-jpa-vs-hibernate-where-are-the-implementation-of-methods-in-jparep

[^122]: https://www.baeldung.com/hibernate-lazy-eager-loading

[^123]: https://discourse.hibernate.org/t/entity-loads-eagerly-when-it-should-be-lazy/561

[^124]: https://vladmihalcea.com/fetchtype-eager-fetchgraph/

[^125]: https://www.baeldung.com/hibernate-lazy-loading-workaround

[^126]: https://discourse.hibernate.org/t/manytoone-always-eager-loading/6199

[^127]: https://www.tutorialspoint.com/difference-between-lazy-and-eager-loading-in-hibernate

[^128]: https://www.youtube.com/watch?v=ucuVbL-tsUY

[^129]: https://stackoverflow.com/questions/30076244/hibernate-configuration-file-and-sessionfactory

[^130]: https://docs.spring.io/spring-framework/reference/data-access/orm/hibernate.html

[^131]: https://coderanch.com/t/595728/databases/Newbie-Hibernate-OneToMany-owning-side

[^132]: https://www.baeldung.com/hibernate-one-to-many

[^133]: https://javatechonline.com/entity-relationship-in-jpa-hibernate-orm/

[^134]: https://www.baeldung.com/hibernate-many-to-many

[^135]: https://www.baeldung.com/jpa-joincolumn-vs-mappedby

[^136]: https://www.baeldung.com/jpa-cascade-types

[^137]: https://docs.jboss.org/hibernate/orm/6.5/querylanguage/html_single/Hibernate_Query_Language.html

[^138]: https://www.baeldung.com/jpa-queries

[^139]: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

[^140]: https://docs.redhat.com/en/documentation/red_hat_jboss_enterprise_application_platform/6.4/html/development_guide/sect-hibernate_query_language

[^141]: https://topic.alibabacloud.com/a/the-difference-between-hql-and-sql_8_8_30041248.html

[^142]: https://stackoverflow.com/questions/56555618/hibernate-first-level-cache-is-missed

[^143]: https://www.tutorialspoint.com/difference-between-first-level-cache-and-second-level-cache-in-hibernate

[^144]: https://www.baeldung.com/hibernate-second-level-cache

[^145]: https://stackoverflow.com/questions/7058843/when-and-how-to-use-hibernate-second-level-cache

[^146]: https://ignite.apache.org/docs/latest/extensions-and-integrations/hibernate-l2-cache

[^147]: https://www.digitalocean.com/community/tutorials/hibernate-ehcache-hibernate-second-level-cache

[^148]: https://www.okta.com/sg/identity-101/password-encryption/

[^149]: https://www.i-sprint.com/document-security-password-protection-vs-document-encryption/

[^150]: https://www.titanfile.com/blog/password-protection-vs-encryption/

[^151]: https://www.waldenu.edu/programs/information-technology/resource/cybersecurity-101-why-choosing-a-secure-password-in-so-important

[^152]: https://blog.eskuad.com/the-importance-of-encryption-in-data-security

[^153]: https://supertokens.com/blog/password-hashing-salting

[^154]: https://www.reddit.com/r/golang/comments/m57sxf/what_is_the_best_algorithm_for_hashing_passwords/

[^155]: https://www.okta.com/sg/identity-101/hashing-algorithms/

[^156]: https://cheatsheetseries.owasp.org/cheatsheets/Cryptographic_Storage_Cheat_Sheet.html

