package kr.intraview;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.theokanning.openai.service.OpenAiService;

@Configuration
public class OpenAiConfig {

  @Bean
  public OpenAiService openAiService(
    @Value("${openai.api-key}") String apiKey
  ) {
    return new OpenAiService(apiKey, Duration.ofMinutes(1));
  }

}
