package maskbook.maskshop.repository;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.Board;
import maskbook.maskshop.domain.Reply;
import maskbook.maskshop.service.ReplyService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReplyRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Reply reply) {
        em.persist(reply);
        return reply.getReplyId();
    }

    public Reply findOne(Long replyId) {
        return em.find(Reply.class, replyId);
    }

    public List<Reply> findAll(Long boardId) {
        return em.createQuery("select r from Reply r join r.board b where b.boardId = :boardId", Reply.class)
                .setParameter("boardId",boardId)
                .getResultList();
    }

}
