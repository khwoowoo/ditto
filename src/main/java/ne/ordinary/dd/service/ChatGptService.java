//package ne.ordinary.dd.service;
//
//import com.theokanning.openai.completion.chat.ChatCompletionRequest;
//import com.theokanning.openai.completion.chat.ChatMessage;
//import com.theokanning.openai.service.OpenAiService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ChatGptService {
//    private static final String MODEL = "gpt-3.5-turbo";
//    private OpenAiService openAiService;
//
//    @Value("${chatgpt.key}")
//    private String apiKey;
//
//    // ChatGPT Mbti Test
//    public int createGptComment(String content) {
//        this.openAiService = new OpenAiService(apiKey, Duration.ofSeconds(60));
//        String prompt = "내가 작성한 다이어리를 한 줄 단위로 감정분석한 결과는 다음과 같다.\n"
//                + content
//                + "모든 문장의 postive와 negative 값을 분석해서 positive 80 이상이면 happy, positive 60 이상이면 smile, positive 40 이상이면 not bad, negative 60 이상이면 sad, negative 80 이상이면 upset을 의미한다.\n"
//                + "오늘 하루 내 감정이 어떤 것에 가까운지 알려줘.\n"
//                + "단 어떠한 설명도 없이 happy, smile, not bad, sad, upset 중 한 단어로만 알려줘.";
//
//        ChatCompletionRequest requester = ChatCompletionRequest.builder()
//                .model(MODEL)
//                .maxTokens(2048)
//                .messages(List.of(
//                        new ChatMessage("user", prompt)
//                )).build();
//        return Integer.parseInt(openAiService.createChatCompletion(requester).getChoices().get(0).getMessage().getContent());
//    }
//}
