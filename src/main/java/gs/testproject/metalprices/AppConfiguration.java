package gs.testproject.metalprices;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration
{
	@Value("${consumerDateFormat}") private String consumerDateFormat;
	@Value("${apiDateFormat}") private String apiDateFormat;

	@Bean
	public RestTemplate prepareRestTemplate(RestTemplateBuilder builder)
	{
		return builder.build();
	}

	@Bean(name = "consumerDateFormatter")
	public DateTimeFormatter prepareConsumerDateFormatter()
	{
		return DateTimeFormatter.ofPattern(consumerDateFormat);
	}

	@Bean(name = "apiDateFormatter")
	public DateTimeFormatter prepareApiDateFormatter()
	{
		return DateTimeFormatter.ofPattern(apiDateFormat);
	}
}