package guru.springframework.sfgdi.config;

import com.springframework.pets.PetService;
import com.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.context.annotation.*;

@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class GreetingServiceConfig {
	@Bean
	PetServiceFactory petServiceFactory() {
		return new PetServiceFactory();
	}

	@Profile({"dog", "default"})
	@Bean
	PetService dogPetService(PetServiceFactory petServiceFactory) {
		return petServiceFactory.getPetService("dog");
	}

	@Profile({"cat"})
	@Bean
	PetService catPetService(PetServiceFactory petServiceFactory) {
		return petServiceFactory.getPetService("cat");
	}

	@Bean
	EnglishGreetingRepository englishGreetingRepository() {
		return new EnglishGreetingRepositoryImpl();
	}

	@Profile({"EN"})
	@Bean("i18nService")
	I18nEnglishGreetingService i18nEnglishGreetingService(EnglishGreetingRepository englishGreetingRepository) {
		return new I18nEnglishGreetingService(englishGreetingRepository);
	}

	@Profile({"ES", "default"})
	@Bean("i18nService")
	I18nSpanishGreetingService i18nSpanishGreetingService() {
		return new I18nSpanishGreetingService();
	}

	@Primary
	@Bean
	PrimaryGreetingService primaryGreetingService() {
		return new PrimaryGreetingService();
	}

	@Bean
	PropertyGreetingService propertyGreetingService() {
		return new PropertyGreetingService();
	}

	@Bean
	SetterGreetingService setterGreetingService() {
		return new SetterGreetingService();
	}
}
