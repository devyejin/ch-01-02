package com.zerock.springex.sample;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ExtendWith(SpringExtension.class) //스프링 테스트 사용하기위한 설정
@ContextConfiguration(locations ="file:src/main/webapp/WEB-INF/root-context.xml") //설정정보 로딩 ( xml 파일 로딩 어노테이션, 자바파일일 경우 classes 속성 )
class SampleServiceTest {

    @Autowired //필드 주입 방식, 타입이 일치하는 빈 등록하는지 확인하고 런타임시 주입
    private SampleService sampleService;

    @Test
    public void testService() {
        log.info(sampleService);
        Assertions.assertNotNull(sampleService);
    }
}