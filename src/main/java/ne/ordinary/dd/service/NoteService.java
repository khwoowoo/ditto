package ne.ordinary.dd.service;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.domain.Note;
import ne.ordinary.dd.domain.User;
import ne.ordinary.dd.model.NoteRequestDto;
import ne.ordinary.dd.repository.NoteRepository;
import ne.ordinary.dd.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;


    @Transactional
    public Long send(NoteRequestDto dto){
        User user = userRepository.findUser(dto.getSenderId());
        Note note = Note.createNote(user, dto.getContent(), dto.getSenderId(), dto.getSenderId());
        return noteRepository.save(note);
    }




}
