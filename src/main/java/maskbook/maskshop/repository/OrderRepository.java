package maskbook.maskshop.repository;

import maskbook.maskshop.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public List<Order> findAllByString(OrderSearch orderSearch){

        String jpql = "select o from Order o join o.user u";
        boolean isFirstCondition = true;

        // 주문상태 검색
        if(orderSearch.getOrderStatus() != null) {
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        // 회원 이름 검색
        if(StringUtils.hasText(orderSearch.getUserName())){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " u.userName like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000); // 최대 1000건

        if(orderSearch.getOrderStatus() != null){
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }


        if(StringUtils.hasText(orderSearch.getUserName())){
            query = query.setParameter("name", orderSearch.getUserName());
        }


        return query.getResultList();

    }
}
