package com.example.feelflow.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userCreateForm", new UserCreateForm());
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            userService.create(userCreateForm.getUsername(),
                    userCreateForm.getEmail(),
                    userCreateForm.getPassword1(),
                    userCreateForm.getNickname(),
                    userCreateForm.getName(),
                    userCreateForm.getBirthday(),
                    userCreateForm.getGender());
        } catch (Exception e) {
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/user/signup_success";
    }

    @GetMapping("/signup_success")
    public String signupComplete() {
        return "signup_success";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @GetMapping("/mypage")
    public String myPage(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/user/login";
        }
        String username = authentication.getName();
        SiteUser user = userService.getByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "mypage";
    }
}