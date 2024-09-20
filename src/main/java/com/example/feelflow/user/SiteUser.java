package com.example.feelflow.user;

import com.example.feelflow.diary.Diary;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사용자 ID

    @Column(unique = true)
    private String username; // 사용자 이름

    private String password; // 비밀번호

    @Column(unique = true)
    private String email; // 이메일 주소

    @Column(unique = true)
    private String nickname; // 닉네임

    private String name; // 실제 이름

    private LocalDate birthday; // 생일

    private String gender; // 성별

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // 하나의 사용자는 여러 개의 일기를 가질 수 있음
    private List<Diary> diaries; // 사용자가 작성한 일기 리스트

    // getters and setters는 Lombok을 사용하므로 생략
}
