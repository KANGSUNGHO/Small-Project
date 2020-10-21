package maskbook.maskshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class BookMarkMeta {

    @Id @GeneratedValue
    private Long bmtId;
    private String title;
    private String description;
    private String image;
    private String userComment;
}
