package maskbook.maskshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long boardId; // 글 번호

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User boardUser; // 작성자

    private String title; // 제목
    private String content; // 내용

    private String insertDate; // 작성 날짜

    public static Board writeBoard(User user, String content, String title){
        Board board = new Board();
        board.setBoardUser(user);
        board.setContent(content);
        board.setTitle(title);
        board.setInsertDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss")));

        return board;
    }

}
