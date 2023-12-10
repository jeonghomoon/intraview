package kr.intraview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import kr.intraview.model.AnswerDTO;
import kr.intraview.model.AnswerResponse;

@Service
public class GptApiService {

  private static final String MODEL_GPT4 = "gpt-4";

  private final ObjectMapper objectMapper;
  private final AnswerService answerService;
  private final OpenAiService openAiService;
  private final QuestionService questionService;

  @Value("${openai.prompt.resume.system}")
  private String resumeSystemPrompt;

  @Value("${openai.prompt.resume.user}")
  private String resumeUserPrompt;

  @Value("${openai.prompt.answer.system}")
  private String answerSystemPrompt;

  @Value("${openai.prompt.answer.assistant}")
  private String answerAssistantPrompt;

  @Value("${openai.prompt.answer.user}")
  private String answerUserPrompt;

  public GptApiService(
    ObjectMapper objectMapper,
    AnswerService answerService,
    OpenAiService openAiService,
    QuestionService questionService
  ) {
    this.objectMapper = objectMapper;
    this.answerService = answerService;
    this.openAiService = openAiService;
    this.questionService = questionService;
  }

  public void generateQuestions(
    String interviewId,
    String resume
  ) throws Exception {
    List<ChatMessage> messages = List.of(
      new ChatMessage(ChatMessageRole.SYSTEM.value(), resumeSystemPrompt),
      new ChatMessage(
        ChatMessageRole.USER.value(),
        String.format(resumeUserPrompt, resume))
    );

    String content = getChatCompletionContent(
      buildChatCompletionRequest(messages)
    );

    List<String> questions = objectMapper.readValue(
      content,
      new TypeReference<List<String>>() {}
    );
    for (String question: questions) {
      questionService.createQuestion(interviewId, question);
    }
  }

  public void generateFeedbackAndFollowup(
    String questionId,
    String answer
  ) throws Exception {
    String question = questionService.loadQuestionById(questionId).getContent();

    List<ChatMessage> messages = List.of(
      new ChatMessage(ChatMessageRole.SYSTEM.value(), answerSystemPrompt),
      new ChatMessage(
        ChatMessageRole.ASSISTANT.value(),
        String.format(answerAssistantPrompt, question)
      ),
      new ChatMessage(
        ChatMessageRole.USER.value(),
        String.format(answerUserPrompt, answer)
      )
    );

    String content = getChatCompletionContent(
      buildChatCompletionRequest(messages)
    );

    AnswerResponse answerResponse = objectMapper.readValue(
      content,
      AnswerResponse.class
    );
    AnswerDTO answerDto = new AnswerDTO(
      questionId,
      answer,
      answerResponse.getFeedback(),
      answerResponse.getFollowup()
    );
    answerService.createAnswer(answerDto);
  }

  private ChatCompletionRequest buildChatCompletionRequest(List<ChatMessage> messages) {
    return ChatCompletionRequest.builder()
        .model(MODEL_GPT4)
        .messages(messages)
        .build();
  }

  private String getChatCompletionContent(ChatCompletionRequest completionRequest) {
    return openAiService.createChatCompletion(completionRequest)
      .getChoices()
      .get(0)
      .getMessage()
      .getContent();
  }

}
