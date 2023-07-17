package com.zerock.w2.service;

import com.zerock.w2.dao.MemberDAO;
import com.zerock.w2.domain.MemberVO;
import com.zerock.w2.dto.MemberDTO;
import com.zerock.w2.util.MapperUtil;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;

public enum MemberService {
    INSTANCE;

    private MemberDAO dao;
    private ModelMapper modelMapper; //VO<->DTO 라이브러리

    //의존성 주입
    MemberService() {
        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    //로그인하면 컨트롤러에 전달해줄거니까 dto
    public MemberDTO login(String mid, String mpw) throws SQLException {

        MemberVO memberVO = dao.getWithPassword(mid, mpw);

        return modelMapper.map(memberVO, MemberDTO.class);

    }

    //자동 로그인 체크한 경우
    public void updateUuid(String mid, String uuid) throws SQLException {
        dao.updateUuid(mid, uuid);
    }
}
