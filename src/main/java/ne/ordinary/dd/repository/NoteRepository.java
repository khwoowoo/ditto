package ne.ordinary.dd.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.domain.Note;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoteRepository {
    private EntityManager em;

   public Long save(Note note){
       em.persist(note);

       return note.getId();
   }

   public Note findOne(Long id){
       return em.find(Note.class, id);
   }

    public List<Note> findNotesBySenderUser(Long userId) {
        return em.createQuery("SELECT n FROM Note n JOIN FETCH n.user WHERE n.senderId = :userId", Note.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Note> findNotesByReceiverUser(Long userId) {
        return em.createQuery("SELECT n FROM Note n JOIN FETCH n.user WHERE n.receiverId = :userId", Note.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Note> findNotesBySenderUser(Long userId, int page, int size) {
        return em.createQuery("SELECT n FROM Note n JOIN FETCH n.user WHERE n.senderId = :userId", Note.class)
                .setParameter("userId", userId)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    public List<Note> findNotesByReceiverUser(Long userId, int page, int size) {
        return em.createQuery("SELECT n FROM Note n JOIN FETCH n.user WHERE n.receiverId = :userId", Note.class)
                .setParameter("userId", userId)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }
}
