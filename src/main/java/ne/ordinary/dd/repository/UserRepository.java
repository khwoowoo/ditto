package ne.ordinary.dd.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public Long save(User user){
        em.persist(user);

        return user.getId();
    }

    public User findUser(Long id){
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public List<User> findByUuid(String uuid){
        return em.createQuery("select u from User u where u.uuid = :uuid", User.class)
                .setParameter("uuid", uuid)
                .getResultList();
    }
}
