package maskbook.maskshop.repository;

import maskbook.maskshop.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(Long userId){
        return em.find(User.class, userId);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public List<User> findByName(String userName){
        return em.createQuery("select u from User u where u.userName = :userName", User.class)
                .setParameter("userName", userName)
                .getResultList();
    }

}
