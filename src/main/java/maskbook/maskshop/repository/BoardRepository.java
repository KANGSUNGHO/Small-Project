package maskbook.maskshop.repository;

import maskbook.maskshop.domain.Board;
import maskbook.maskshop.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BoardRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }

    public Board findOne(Long boardId){
       return  em.find(Board.class, boardId);
    }

    public List<Board> findAll(){
       return em.createQuery("select b from Board b", Board.class)
               .getResultList();
    }

    public void deleteOne(Board board) {
        em.remove(board);
    }
}
