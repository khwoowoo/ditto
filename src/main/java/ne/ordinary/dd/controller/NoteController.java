package ne.ordinary.dd.controller;

import lombok.RequiredArgsConstructor;
import ne.ordinary.dd.domain.Note;
import ne.ordinary.dd.domain.User;
import ne.ordinary.dd.model.NoteRequestDto;
import ne.ordinary.dd.model.NoteResponseDto;
import ne.ordinary.dd.model.ResponseDTO;
import ne.ordinary.dd.model.UserRequestDto;
import ne.ordinary.dd.repository.NoteRepository;
import ne.ordinary.dd.repository.UserRepository;
import ne.ordinary.dd.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class NoteController {
    public final NoteService noteService;
    public final NoteRepository noteRepository;
    public final UserRepository userRepository;

    @PostMapping("/note")
    public ResponseDTO<Long> register(@RequestBody NoteRequestDto noteRequestDto){
        Long sendId = noteService.send(noteRequestDto);
        return new ResponseDTO<Long>(sendId);
    }

    @GetMapping("/note/{userId}/receive")
    public ResponseDTO<List<NoteResponseDto>> findReceive(@PathVariable Long userId){

        List<Note> notesByReceiverUser = noteRepository.findNotesByReceiverUser(userId);
        return new ResponseDTO<List<NoteResponseDto>>(createResponseDtoList(notesByReceiverUser));
    }

    @GetMapping("/note/{userId}/send")
    public ResponseDTO<List<NoteResponseDto>> findSend(@PathVariable Long userId){
        List<Note> notesBySenderUser = noteRepository.findNotesBySenderUser(userId);
        return new ResponseDTO<List<NoteResponseDto>>(createResponseDtoList(notesBySenderUser));
    }

    public List<NoteResponseDto> createResponseDtoList(List<Note> notes){
        List<NoteResponseDto> list = new ArrayList<NoteResponseDto>();
        for(Note n : notes){
            User sender = userRepository.findUser(n.getSenderId());
            User receiver = userRepository.findUser(n.getReceiverId());
            list.add(NoteResponseDto.builder()
                    .content(n.getContent())
                    .receiverName(sender.getUsername())
                    .senderName(receiver.getUsername())
                    .build());
        }
        return  list;
    }

}
