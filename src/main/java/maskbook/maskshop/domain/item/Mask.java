package maskbook.maskshop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter @Setter
@DiscriminatorValue("M")
public class Mask extends Item {

    private String kinds; // kf-ad, kf-80, kf-94, normal

    @Enumerated(EnumType.STRING)
    private ItemArea area; // [KOREA, CHINA]
}
