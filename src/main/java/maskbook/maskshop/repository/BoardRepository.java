package maskbook.maskshop.repository;

import maskbook.maskshop.domain.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
