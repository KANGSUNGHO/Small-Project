package maskbook.maskshop.service;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.*;
import maskbook.maskshop.domain.item.Item;
import maskbook.maskshop.repository.OrderRepository;
import maskbook.maskshop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    @Transactional
    public Long order(Long userId, Long itemId, int count){
        // 엔티티 조회
        User user = userRepository.findOne(userId);
        Item item = itemService.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(user.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(user, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getOrderId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){

        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        // 주문 취소
        order.cancel();
    }

}
