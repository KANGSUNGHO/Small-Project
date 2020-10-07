package maskbook.maskshop.repository;

import lombok.Getter;
import lombok.Setter;
import maskbook.maskshop.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {

    private String userName; // 회원이름
    private OrderStatus orderStatus; // 주문상태[ORDER, CANCEL]
}
