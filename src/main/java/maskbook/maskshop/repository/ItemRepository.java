package maskbook.maskshop.repository;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.item.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Item item){
        if(item.getItemId() == null){
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long itemId){
        return em.find(Item.class, itemId);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
