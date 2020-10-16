package maskbook.maskshop.repository;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardSearch {

    private String searchOption; // 검색 옵션[글제목, 작성자]
    private String search; // 검색명
}
