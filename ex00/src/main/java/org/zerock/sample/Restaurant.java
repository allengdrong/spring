package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

// 자동 생성하게 하는 어노테이션
// @Controller, @Service, @Repository, @Component, @RestController, @Advice
//WEB-INF/spring/root-context.xml 설정이 되어 있어야 한다. component-scan
@Component
@Data
public class Restaurant {

	// @Setter - lombok 사용, @Autowired - Spring 사용
	// 대신 사용 가능한 어노테이션 : @Autowired - Spring, @Inject - java
	@Setter(onMethod_ = @Autowired)
	private Chef chef;
}
