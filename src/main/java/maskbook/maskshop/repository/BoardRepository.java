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
        if(boardSearch.getSearchOption() != null){
            if(boardSearch.getSearchOption().equals("title")){
                jpql += " where b.title like :title";
            } else if(boardSearch.getSearchOption().equals("userName")){
                jpql += " where u.userName like :userName";
            }
        }else {
            isFirstCondition = false;
        }

        TypedQuery query = em.createQuery(jpql,Board.class);


        if(boardSearch.getSearchOption() != null){
            if(boardSearch.getSearchOption() != null){
                if(boardSearch.getSearchOption().equals("title")){
                    query = query.setParameter("title", boardSearch.getSearch());
                } else if(boardSearch.getSearchOption().equals("userName")){
                    query = query.setParameter("userName", boardSearch.getSearch());
                }
            }else {
                isFirstCondition = false;
            }
        }

        return query.getResultList();

    }

    public void deleteOne(Board board) {
        em.remove(board);
    }
}
