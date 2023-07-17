package com.zerock.w2.dao;

import com.zerock.w2.domain.MemberVO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class MemberDAO {

    public MemberVO getWithPassword(String mid, String mpw) throws SQLException {

        String query = "SELECT mid, mpw, mname FROM tbl_member WHERE mid = ? and mpw = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, mid);
        preparedStatement.setString(2, mpw);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        MemberVO vo = MemberVO.builder()
                .mid((resultSet.getString(1)))
                .mname(resultSet.getString(3))
                .mpw(resultSet.getString(2))
                .build();
        log.info("vo.getMid()={} , vo.getMpw()={}, vo.getMname()={}", vo.getMid() , vo.getMpw(), vo.getMname());

        return vo;

    }


    public void updateUuid(String mid, String uuid) throws SQLException {

        String query = "UPDATE tbl_member SET uuid = ? WHERE mid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);//sql먼저 보내고 -> 바인딩은 후에 (sql Injection 방지)

        preparedStatement.setString(1,uuid);
        preparedStatement.setString(2,mid);

        preparedStatement.executeUpdate();
    }

    //쿠키 값 이용해서 사용자 정보 로딩 기능
    public void selectUUID(String uuid) throws SQLException {

        String query = "SELECT mid, mpw, mname, uuid from tbl_member WHERE uuid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement =  connection.prepareStatement(query);

        preparedStatement.setString(1,uuid);


    }
}
