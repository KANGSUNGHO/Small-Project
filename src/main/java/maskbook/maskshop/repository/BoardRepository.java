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

    public List<Board> findBoardAllByString(BoardSearch boardSearch){

        String jpql ="select b from Board b join b.boardUser u";
        Boolean isFirstCondition = true;

        // 글 이름 검색
        if(StringUtils.hasText(boardSearch.getTitle())){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            } else{
                jpql += " and";
            }
            jpql += " b.title like :title";
        }

        if(StringUtils.hasText(boardSearch.getUserName())){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            } else{
                jpql += " and";
            }
            jpql += " u.userName like :userName";
        }
        TypedQuery query = em.createQuery(jpql,Board.class);

        if(StringUtils.hasText(boardSearch.getTitle())){
            query = query.setParameter("title", boardSearch.getTitle());
        }
        if(StringUtils.hasText(boardSearch.getUserName())){
            query = query.setParameter("userName", boardSearch.getUserName());
        }
        return query.getResultList();

//       return em.createQuery("select b from Board b", Board.class)
//               .getResultList();
    }

    public void deleteOne(Board board) {
        em.remove(board);
    }
}
