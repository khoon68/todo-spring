package todo.asdf.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import todo.asdf.domain.Member;
import todo.asdf.repository.MapMemberRepository;
import todo.asdf.repository.MemberRepository;

import java.util.List;

@Controller
public class MemberController {

    MemberRepository memberRepository = new MapMemberRepository();

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/members/sign-up")
    public String showSignUpPage() {
        return "/members/sign-up";
    }

    @PostMapping("/members/sign-up")
    public String postSignUp(MemberSignUpForm memberSignUpForm) {
        Member member = new Member();
        member.setId(memberSignUpForm.getId());
        member.setPassword(memberSignUpForm.getPassword());
        memberRepository.save(member);

        return "redirect:/";
    }

    @GetMapping("/members/member-list")
    public String showMemberListPage(Model model) {
        List<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList", memberList);

        return "/members/member-list";
    }
}
