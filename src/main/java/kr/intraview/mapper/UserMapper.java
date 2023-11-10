package kr.intraview.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.intraview.model.User;

@Mapper
public interface UserMapper {

  void insertUser(User user);

  User findByEmail(String email);

}
