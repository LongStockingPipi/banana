package pers.jason.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/28 15:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = -5576610332522504893L;

  private Long id;

  private String cnName;

  private String enName;

  private String username;

  private String password;

  private String tel;

  private String email;

}
