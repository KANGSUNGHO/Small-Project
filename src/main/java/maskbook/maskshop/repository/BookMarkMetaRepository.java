package maskbook.maskshop.repository;

import maskbook.maskshop.domain.BookMarkMeta;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookMarkMetaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(BookMarkMeta bmMeta){
        em.persist(bmMeta);
    }

    public List<BookMarkMeta> findAll(){
        return em.createQuery("select b from BookMarkMeta b", BookMarkMeta.class).
                getResultList();
    }

    public BookMarkMeta findOne(Long bmtId) {
        return em.find(BookMarkMeta.class, bmtId);
    }

    public void cancel(BookMarkMeta bmt){
        em.remove(bmt);
    }
}
