package kr.intraview.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.intraview.model.User;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testInsertUser() {
    // given
    String email = "jeonghomoon@yahoo.com";
    User user = new User(UUID.randomUUID().toString(), email, "1q2w3e4r1!");

    // when
    userMapper.insertUser(user);

    // then
    User insertedUser = userMapper.findByEmail(email);
    assertEquals(insertedUser.getId(), user.getId());
    assertEquals(insertedUser.getEmail(), user.getEmail());
    assertEquals(insertedUser.getPassword(), user.getPassword());
  }

}

