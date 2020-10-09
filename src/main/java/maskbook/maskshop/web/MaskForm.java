package maskbook.maskshop.web;

import lombok.Getter;
import lombok.Setter;
import maskbook.maskshop.domain.item.ItemArea;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @Setter
public class MaskForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String kinds; // kf-ad, kf-80, kf-94, normal

    @Enumerated(EnumType.STRING)
    private ItemArea area; // [KOREA, CHINA]
}
