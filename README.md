﻿# ORM-Hibernate
Here’s a structured walkthrough of each topic—covering definitions, key distinctions, diagrams, code‐level methods, pros/cons, and best practices.

---

## 1. What is JPA?  
- **Java Persistence API** (JPA) is a Java specification (part of Jakarta EE) for object-relational mapping (ORM).  
- Defines standard interfaces (`EntityManager`, `EntityTransaction`, `Query`, etc.) and annotations (`@Entity`, `@Id`, `@OneToMany`, …) so that POJOs can be persisted to relational tables without JDBC boilerplate.  

## 2. What is Hibernate?  
- Hibernate is a mature, open-source ORM framework and a JPA-provider implementation.  
- It implements the JPA interfaces, adds its own native APIs, and offers extra features (e.g. built-in second-level cache, extended mapping capabilities).  

## 3. Difference between JPA and Hibernate  
| Aspect            | JPA                              | Hibernate                                    |
|-------------------|----------------------------------|----------------------------------------------|
| Type              | Specification/API only           | Concrete implementation + native extensions  |
| Packaging         | Part of Jakarta EE / separate API | External library (jar)                       |
| Features          | Defines standard ORM contracts   | JPA + extra features (caching, multi-tenant) |
| Portability       | Switch providers easily          | Tied to Hibernate if using native APIs       |

---

## 4. What is RDBMS?  
- **Relational Database Management System**: stores data in tables (rows & columns).  
- Enforces schemas, ACID transactions, SQL querying, foreign-key relationships.

## 5. Difference between RDBMS and Hibernate  
| Aspect        | RDBMS                                | Hibernate (ORM)                           |
|---------------|--------------------------------------|-------------------------------------------|
| Role          | Data storage & transaction engine    | Maps Java objects ↔ relational tables     |
| API           | SQL                                  | Java APIs (JPA/Hibernate APIs)            |
| Abstraction   | Low-level (tables, joins, SQL)       | High-level (entities, associations)       |
| Schema sync   | Manual DDL                           | Auto-generate/update schema from entities|

---

## 6. Advantages & Disadvantages of Hibernate  
**Advantages**  
- Eliminates JDBC boilerplate (no manual `ResultSet` mapping)  
- Transparent persistence and caching (first & second level)  
- Rich query options (HQL, Criteria, native SQL)  
- Database-independent (dialects)  
- Lazy loading, automatic dirty checking  

**Disadvantages**  
- Learning curve (mapping nuances, session management)  
- Potential performance pitfalls (N+1 selects, over-fetching)  
- Debugging generated SQL can be harder  
- Overkill for simple CRUD without complex domain  

---

## 7. Object/Entity State Diagram  
```
Transient ──persist()──► Persistent ──detach()/close()──► Detached
    ▲                           │                         │
    │                           │ remove()                │ merge()
    └── new object             ▼                         ▼
                              Removed──────────flush()──► Database DELETE
```
- **Transient**: newly instantiated, not associated with `Session`.  
- **Persistent**: associated with open `Session`; changes auto-flushed.  
- **Detached**: previously persistent; session closed or explicitly detached.  
- **Removed**: scheduled for deletion; still persistent until flush.  

**Methods to transition**  
| From/To      | Method               | Explanation                         |
|--------------|----------------------|-------------------------------------|
| Transient → Persistent | `session.persist(entity)` or `session.save(entity)` | Register new entity |
| Persistent → Detached   | `session.evict(entity)` or `session.clear()` or close session | Detach from context |
| Detached → Persistent   | `session.merge(entity)`         | Re-attach/update state  |
| Persistent → Removed    | `session.remove(entity)`        | Mark for deletion       |
| Removed → Database      | `session.flush()`               | Execute DELETE SQL      |

---

## 8. What is ORM?  
- **Object-Relational Mapping**: technique to convert data between incompatible type systems (objects in Java ↔ tables in RDBMS).  
- Automates SQL generation, handles associations, lifecycle, caching.

---

## 9. Implementation of Hibernate & its methods  
1. **Bootstrap**  
   ```java
   // XML-based
   Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
   SessionFactory sf = cfg.buildSessionFactory();
   Session session = sf.openSession();
   session.beginTransaction();
   ```
2. **CRUD operations**  
   ```java
   session.save(entity);    // INSERT
   session.get(Entity.class, id); // SELECT
   session.update(entity);  // UPDATE
   session.remove(entity);  // DELETE
   session.getTransaction().commit();
   session.close();
   ```
3. **Querying**  
   - HQL: `session.createQuery("from User where age> :a")…`  
   - Criteria API: `session.createCriteria(User.class).add(Restrictions.gt("age", a));`  
   - Native SQL: `session.createNativeQuery("SELECT * FROM users");`  

---

## 10. Lazy vs Eager Fetching  
- **Lazy**: associations loaded on access. Pro: performance, avoid unnecessary joins. Con: `LazyInitializationException` if session closed.  
- **Eager**: associations loaded immediately with parent. Pro: no lazy exceptions. Con: potential over-fetching.

## 11. Methods used in Eager & Lazy  
- Controlled via annotations or XML:  
  ```java
  @OneToMany(fetch = FetchType.LAZY)  // default for collections
  @ManyToOne(fetch = FetchType.EAGER) // default for single-valued
  ```
- Can override per-query via HQL:  
  ```java
  SELECT u FROM User u JOIN FETCH u.roles
  ```

---

## 12. Two types of configuration in Hibernate’s SessionFactory  
1. **XML-based** (`hibernate.cfg.xml` + `*.hbm.xml` mapping files)  
2. **Annotation/Java-based** (`@Entity` classes + `Configuration.addAnnotatedClass(...)` or `persistence.xml`)

---

## 13. Three types of relationships using “inverse” (non-owning) and “owning” sides  
| Relationship    | Owning side         | Inverse side (`mappedBy`)         |
|-----------------|---------------------|-----------------------------------|
| One-to-One      | side with FK        | other side: `mappedBy="owning"`   |
| One-to-Many     | Many-side (`@JoinColumn`) | One-side: `@OneToMany(mappedBy="…")` |
| Many-to-Many    | either side         | other side: `@ManyToMany(mappedBy="…")` |

- **Owning** side: the one that updates the join table/foreign key.  
- **Inverse** side: uses `mappedBy` attribute to point to the owning field.

---

## 14. “mappedBy” attribute  
- Added on the **inverse** (non-owning) side.  
- Value equals the field name on the owning side.

---

## 15. Cascade types in Hibernate & why  
| CascadeType    | Effect                                  | Use case                              |
|----------------|-----------------------------------------|---------------------------------------|
| ALL            | apply all operations                    | parent-child full lifecycle           |
| PERSIST        | propagate `save()`                     | save parent → save children           |
| MERGE          | propagate `merge()`                     | reattach detached graph               |
| REMOVE         | propagate `remove()`                    | delete parent → delete children       |
| REFRESH        | propagate `refresh()`                   | refresh graph                         |
| DETACH         | propagate `detach()`                    | detach entire graph                   |

**Why?** to manage entire object graph lifecycles automatically, avoid manual operations on each associated entity.

---

## 16. Query types in Hibernate  
1. **HQL (Hibernate Query Language)** – object-oriented  
2. **Criteria API** – programmatic, type-safe queries  
3. **Native SQL** – raw SQL  
4. **Named Queries** – predefined in annotations or XML  

---

## 17. Differences between HQL and Native SQL  
| Aspect         | HQL                                   | Native SQL                           |
|----------------|---------------------------------------|--------------------------------------|
| Syntax         | Entity names & properties            | Table names & columns                |
| Portability    | Database-independent                  | DB dialect specific                  |
| Result         | Returns entities or scalars           | Can return arbitrary result sets     |
| Features       | Automatic mapping, caching benefits   | Full SQL power (vendor features)     |

---

## 18. First-level vs Second-level Cache  
|                        | First-level cache               | Second-level cache                         |
|------------------------|---------------------------------|--------------------------------------------|
| Scope                  | Session                         | SessionFactory (across sessions)           |
| Enabled by default     | Yes                             | No (configure explicitly)                  |
| Key                     | Entity identifier in session    | Entity identifier in region                |
| Invalidated when       | Session closed or evicted       | Based on TTL/eviction policy               |
| Use case               | Repeat finds in same session    | Share across transactions, reduce DB hits  |

- **Second-level cache** can use providers (EHCache, Infinispan). Improves read performance across sessions.

---

## 19. Importance of Password Encryption & Algorithms  
- **Why**: Protect against data breaches, rainbow-table, brute-force attacks.  
- **Best practice**: store salted, iterated hashes—not reversible encryption.  
- **Algorithms**:  
  - **bcrypt** (adaptive, built-in salt, cost factor)  
  - **PBKDF2** (configurable iterations, HMAC-based)  
  - **scrypt** (memory-hard)  
  - **Argon2** (winner of Password Hashing Competition, memory & CPU hard)  

---
