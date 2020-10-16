package maskbook.maskshop.service;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.Board;
import maskbook.maskshop.domain.User;
import maskbook.maskshop.repository.BoardRepository;
import maskbook.maskshop.repository.BoardSearch;
import maskbook.maskshop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveBoard(Long userId, String content, String title){
        User user = userRepository.findOne(userId);

        Board board = Board.writeBoard(user,content,title);


        boardRepository.save(board);
        return board.getBoardId();
    }

    public Board findBoard(Long boardId){
        return boardRepository.findOne(boardId);
    }

    public List<Board> findBoards(BoardSearch boardSearch){
        return boardRepository.findBoardAllByString(boardSearch);
    }

    @Transactional
    public void updateForm(Long id, String title, String content, String updateDate){
        Board updateBoard = boardRepository.findOne(id);
        updateBoard.setTitle(title);
        updateBoard.setContent(content);
        updateBoard.setInsertDate(updateDate);
    }

    @Transactional
    public void deleteBoard(Board board){
        boardRepository.deleteOne(board);
    }


}
