package maskbook.maskshop.service;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.Board;
import maskbook.maskshop.domain.Reply;
import maskbook.maskshop.repository.BoardRepository;
import maskbook.maskshop.repository.ReplyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long saveReply(Long boardId, String replyUserName, String content){
        Board board = boardRepository.findOne(boardId);
        Reply reply = Reply.wirteReply(board, replyUserName, content);

        return replyRepository.save(reply);

    }

    public Reply findReply(Long replyId){
        return replyRepository.findOne(replyId);
    }

    public List<Reply> findReplys(Long boardId){
        return replyRepository.findAll(boardId);
    }

}
