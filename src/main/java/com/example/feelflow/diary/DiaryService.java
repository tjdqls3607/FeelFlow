package com.example.feelflow.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    public void saveDiary(Diary diary) {
        diaryRepository.save(diary);
    }

    public List<Diary> getAllDiaries() {
        return diaryRepository.findAll();
    }
}
