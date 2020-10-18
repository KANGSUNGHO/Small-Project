package maskbook.maskshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long boardId; // 글 번호

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User boardUser; // 작성자

    @OneToMany(mappedBy = "board")
    private List<Reply> replys = new ArrayList<>();

    private String title; // 제목
    private String content; // 내용

    private String insertDate; // 작성 날짜

    public void changeBoardUser(User boardUser){ // 연관관계 편의 메서드
        this.boardUser = boardUser;
        boardUser.getBoards().add(this); // this는 현재 클래스 Board임
    }
    /*  changeBoardUser(User boardUser) 메소드를 따로 해주는 이유는
        두 테이블 양쪽에 값을 설정하기 위해
     */

    public static Board writeBoard(User user, String content, String title){
        Board board = new Board();
        board.setBoardUser(user);
        board.setContent(content);
        board.setTitle(title);
        board.setInsertDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss")));

        return board;
    }
}
