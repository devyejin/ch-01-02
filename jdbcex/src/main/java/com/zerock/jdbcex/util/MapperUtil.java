package com.zerock.jdbcex.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

/**
 * ModelMapper란
 * - VO <-> DTO 변환 편리하게해주는 라이브러리
 *
 * - 라이브러리니까 객체 생성해서 이용하겠쥬? 싱글턴으로 이용하게 ENUM 이용
 */
public enum MapperUtil {

    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true) //vo, dto 필드 매칭한다는설정같음
                .setFieldAccessLevel(Configuration
                        .AccessLevel.PRIVATE) //private필드에도 접근 가능
                .setMatchingStrategy(MatchingStrategies.STRICT);
    } //생성자니까 MapperUtil 생성되면 modelMapper가 생성되지

    public ModelMapper get() {
        return modelMapper; //생성+설정완료한 modelMapper 반환
    }




}
