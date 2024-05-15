package todo.asdf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import todo.asdf.domain.Member;
import todo.asdf.domain.Todo;
import todo.asdf.repository.MapMemberRepository;
import todo.asdf.repository.MapTodoRepository;
import todo.asdf.repository.MemberRepository;
import todo.asdf.repository.TodoRepository;

import java.util.List;
import java.util.Objects;

@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;
    TodoRepository todoRepository = new MapTodoRepository();
    static Member loginMember;
    static boolean isLogin = false;

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("isLogin", isLogin);
        model.addAttribute("member", loginMember);

        if(isLogin) {
            model.addAttribute("todoList", todoRepository.findListByMemberId(loginMember.getId()));
        }

        return "home";
    }

    @PostMapping("/login")
    public String loginHomePage(MemberForm memberForm) {
        Member findMember = memberRepository.findById(memberForm.getId());
        if(Objects.equals(findMember.getPassword(), memberForm.getPassword())) {
            isLogin = true;
            loginMember = new Member();
            loginMember.setId(memberForm.getId());
            loginMember.setPassword(memberForm.getPassword());
        }
        return "redirect:/";
    }

    @PostMapping("/todo")
    public String postTodo(TodoForm todoForm) {
        Todo todo = new Todo();
        todo.setContent(todoForm.getContent());
        todo.setMemberId(loginMember.getId());
        todoRepository.save(todo);

        return "redirect:/";
    }

    // 로그아웃 버튼을 눌렀을 떄 로그인 정보를 없애고 홈페이지로 돌아오는 기능을 추가할 것
    @GetMapping("/logoutHomePage")
    public String logoutHomePage() {
        loginMember = null;
        isLogin = false;

        return "redirect:/";
    }

    @GetMapping("/members/sign-up")
    public String showSignUpPage() {
        return "/members/sign-up";
    }

    @PostMapping("/members/sign-up")
    public String postSignUp(MemberForm memberForm) {
        Member member = new Member();
        member.setId(memberForm.getId());
        member.setPassword(memberForm.getPassword());
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
