package ne.ordinary.dd.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatGptService {
    private static final String MODEL = "gpt-3.5-turbo";
    private OpenAiService openAiService;

    @Value("${chatgpt.key}")
    private String apiKey;

    // ChatGPT Mbti Test
    public int createMBTITest(String content) {
        this.openAiService = new OpenAiService(apiKey, Duration.ofSeconds(60));
        String prompt = "너는 지금부터 MBTI 검사 판독기야.\n" +
                "\n" + content +
                "너는 4가지 질문을 보고 질문의 평균값을 구해서 100점 만점에 내가 몇 점짜리 F형 인간인지  알려줘.\n" +
                "무조건 점수로만 말해줘. 다른 부연설명이나 분석없이 무조건 숫자로만 대답해줘. (예. 80)";

        ChatCompletionRequest requester = ChatCompletionRequest.builder()
                .model(MODEL)
                .maxTokens(2048)
                .messages(List.of(
                        new ChatMessage("user", prompt)
                )).build();
        return Integer.parseInt(openAiService.createChatCompletion(requester).getChoices().get(0).getMessage().getContent());
    }
}
