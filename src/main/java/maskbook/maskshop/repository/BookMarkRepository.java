package maskbook.maskshop.repository;

import maskbook.maskshop.domain.BookMark;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookMarkRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(BookMark bookMark) {
        em.persist(bookMark);
    }
}
