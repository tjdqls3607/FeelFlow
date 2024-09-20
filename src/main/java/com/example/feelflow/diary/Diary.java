package com.example.feelflow.diary;

import com.example.feelflow.user.SiteUser; // SiteUser 엔티티를 참조
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 일기 ID

    private String title; // 일기 제목
    private String content; // 일기 내용
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시간

    @ManyToOne // 여러 일기가 하나의 사용자에 속함
    @JoinColumn(name = "user_id", nullable = false) // user_id를 외래 키로 설정, 필수 값
    private SiteUser user; // 해당 일기를 작성한 사용자

    // getters and setters는 Lombok을 사용하므로 생략
}
