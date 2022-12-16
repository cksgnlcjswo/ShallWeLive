package com.ssafy.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SuppressWarnings("unchecked")
public class SwaggerConfiguration {
	
	private String title = "Shall We Live API ";

	private ApiInfo apiInfo() {
		String descript = "Shall We Live API Reference for Developers<br>";
		
		return new ApiInfoBuilder().title(title).description(descript)
//				.termsOfServiceUrl("https://edu.ssafy.com")
				.contact(new Contact("karin kim", "https://cksgnlcjswo.tistory.com/", "cksgnlcjswoo@naver.com")).license("MIT License")
				.licenseUrl("not registered").version("1.0").build();
	}

	// API마다 구분짓기 위한 설정.
	@Bean
	public Docket userApi() {
		return getDocket("회원", Predicates.or(PathSelectors.regex("/user.*")));
	}

	@Bean
	public Docket searchApi() {
		return getDocket("게시판", Predicates.or(PathSelectors.regex("/board.*")));
	}

	@Bean
	public Docket aptApi() {
		return getDocket("아파트", Predicates.or(PathSelectors.regex("/apt.*")));
	}

	@Bean
	public Docket crawlerApi() {
		return getDocket("크롤링 결과", Predicates.or(PathSelectors.regex("/crawler.*")));
	}
	
	@Bean
	public Docket allApi() {
		return getDocket("전체", Predicates.or(PathSelectors.regex("/*.*")));
	}

	public Docket getDocket(String groupName, Predicate<String> predicate) {
//		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
//		responseMessages.add(new ResponseMessageBuilder().code(200).message("OK !!!").build());
//		responseMessages.add(new ResponseMessageBuilder().code(500).message("서버 문제 발생 !!!").responseModel(new ModelRef("Error")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(404).message("페이지를 찾을 수 없습니다 !!!").build());
		return new Docket(DocumentationType.SWAGGER_2).groupName(groupName).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.home.controller")).paths(predicate)
				.apis(RequestHandlerSelectors.any()).build();
//				.useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET,responseMessages);
	}

	// swagger ui 설정.
	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
	}
}
