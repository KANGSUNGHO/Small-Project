package maskbook.maskshop.domain;

import lombok.Getter;
import lombok.Setter;
import maskbook.maskshop.domain.item.Item;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="order_item")
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; // 주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 주문

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량
}
