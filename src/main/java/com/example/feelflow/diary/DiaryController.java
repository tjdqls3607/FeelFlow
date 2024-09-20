package com.example.feelflow.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    // 일기 작성 페이지로 이동
    @GetMapping("/write")
    public String showDiaryForm(Model model) {
        model.addAttribute("diary", new Diary());
        return "diary/write_diary"; // 파일 이름에 맞춰 수정
    }

    // 일기 제출 처리
    @PostMapping("/write")
    public String submitDiary(Diary diary) {
        diaryService.saveDiary(diary);
        return "redirect:/diary/list"; // 작성 완료 후 목록 페이지로 리디렉션
    }

    // 작성한 일기 목록 페이지
    @GetMapping("/list")
    public String showDiaryList(Model model) {
        model.addAttribute("diaries", diaryService.getAllDiaries());
        return "diary/list"; // 목록 페이지
    }
}
