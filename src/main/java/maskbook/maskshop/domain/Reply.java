package maskbook.maskshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter @Setter
public class Reply {

    @Id @GeneratedValue
    private Long replyId; // 댓글 번호

    private String replyUserName; // 댓글 작성자
    private String replyContent; // 댓글 내용
    private String insertDate; // 댓글 작성날짜


    @JoinColumn(name="board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board; // 게시글 번호 찾기 위함.

    public static Reply wirteReply(Board board, String replyUserName, String content) {
        Reply reply = new Reply();
        reply.setBoard(board);
        reply.setReplyUserName(replyUserName);
        reply.setReplyContent(content);
        reply.setInsertDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss")));

        return reply;
    }
}
