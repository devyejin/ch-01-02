package com.zerock.springex.sample;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.PrimitiveIterator;

/**
 * Application Context :  자바 빈 들을 관리하기 위한 공간
 * - root-context.xml (빈 이름 명시 등록) 파일을 기반으로 스프링 실행시 Application Context 객체 생성
 * - 즉 실행 시 Application Context 공간에 등록된 빈들이 객체로 생성되서 관리된다.
 */
@ToString
public class SampleService {

    @Autowired
    private SampleDAO sampleDAO;
}
