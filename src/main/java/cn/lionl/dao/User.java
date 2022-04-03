package cn.lionl.dao;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true,length = 30)
    private String openid;

    @Column(length = 200)
    private String name;
    @Column(length = 200)
    private String nickname;
    @Column(length = 200)
    private String schoolName;
    @Column(length = 200)
    private String collegeName;
    @Column(length = 200)
    private String className;
    @Column(length = 30)
    private String studentOn;
    @Column
    private boolean sex;
    @Column
    private String imgUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp timestamp;
}
