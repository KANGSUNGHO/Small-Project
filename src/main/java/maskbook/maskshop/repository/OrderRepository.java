package maskbook.maskshop.repository;

import maskbook.maskshop.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long orderId){
        return em.find(Order.class, orderId);
    }
}
