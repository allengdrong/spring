package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Data;

//자동 생성하게 하는 어노테이션
//@Controller, @Service, @Repository, @Component, @Restcontroller, @Advice
///WEB-INF/spring/appServlet/root-context.xml 설정이 되어 있어야 한다. component-scan
@Component
@Data
public class Chef {

}
