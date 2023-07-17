package com.zerock.w2.dao;

import com.zerock.w2.domain.TodoVO;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TodoDAO { //repository같은 기능, DB랑 연결되는

    public String getTime() {

        String now = null;

        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select now()");
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                now = resultSet.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    //ervice에서 vo로 넘겨줌 ㅇㅋㅇㅋ

    public void insert(TodoVO vo) throws SQLException {
        log.info("호출");
        String sql = "INSERT INTO tbl_todo (title, duedate, finished) VALUES (?,?,?)";

        //try - with 문 => 자원 자동 반환
        try (Connection connection = ConnectionUtil.INSTANCE.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            //ConnectionUtil 로드될 때 DataSource 생성

            //바인딩 값 넘겨주기
            ps.setString(1, vo.getTitle());
            ps.setDate(2, Date.valueOf(vo.getDueDate()));
            ps.setBoolean(3, vo.isFinished());

            ps.executeUpdate();//insert제외->Update, insert ->Query


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   public List<TodoVO> selectAll() throws SQLException {

        String sql = "SELECT * FROM tbl_todo";

       @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
       @Cleanup PreparedStatement ps = connection.prepareStatement(sql);

       @Cleanup ResultSet rs = ps.executeQuery(); //ResultSet은 DB쪽에 위치함, 여기로 필요한 데이터만 가져오기

       List<TodoVO> list = new ArrayList<>();

       while(rs.next()) {
           TodoVO vo = TodoVO.builder()
                   .tno(rs.getLong("tno"))
                   .title(rs.getString("title"))
                   .dueDate(rs.getDate("dueDate").toLocalDate())
                   .finished(rs.getBoolean("finished"))
                   .build();

           list.add(vo);
       }

       return list;

   }


   public TodoVO selectOne(Long tno) throws SQLException {
        String sql = "SELECT * FROM tbl_todo WHERE tno = ?";

       @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
       @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

       preparedStatement.setLong(1, tno);

       @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

       resultSet.next(); //다음 문장이 하나라 ㄱㅊ

       return TodoVO.builder()
               .tno(resultSet.getLong("tno"))
               .title(resultSet.getString("title"))
               .dueDate(resultSet.getDate("dueDate").toLocalDate())
               .finished(resultSet.getBoolean("finished"))
               .build();
   }

   public void deleteOne(Long tno) throws SQLException {
        String sql = "DELETE FROM tbl_todo WHERE tno = ?";

       @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
       @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

       preparedStatement.setLong(1,tno);

       int resultCount = preparedStatement.executeUpdate();

   }

   public void updateOne(TodoVO todoVO) throws SQLException {
       String sql = "UPDATE tbl_todo SET title = ?, dueDate = ?, finished = ? WHERE tno = ?";

       @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
       @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);


       preparedStatement.setString(1, todoVO.getTitle());
       preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
       preparedStatement.setBoolean(3,todoVO.isFinished());
       preparedStatement.setLong(4,todoVO.getTno());

       int resultCount = preparedStatement.executeUpdate();
   }

}
