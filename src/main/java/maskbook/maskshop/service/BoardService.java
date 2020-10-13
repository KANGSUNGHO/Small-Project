package maskbook.maskshop.service;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.Board;
import maskbook.maskshop.domain.User;
import maskbook.maskshop.repository.BoardRepository;
import maskbook.maskshop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveBoard(Long userId, String content){
        User user = userRepository.findOne(userId);

        Board board = Board.writeBoard(user,content);

        boardRepository.save(board);
        return board.getBoardId();
    }

    public Board findBoard(Long boardId){
        return boardRepository.findOne(boardId);
    }

    public List<Board> findBoards(){
        return boardRepository.findAll();
    }


}
