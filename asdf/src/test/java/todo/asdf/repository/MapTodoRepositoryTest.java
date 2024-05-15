package todo.asdf.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import todo.asdf.domain.Member;
import todo.asdf.domain.Todo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MapTodoRepositoryTest {
    @Autowired MemberRepository memberRepository;
    @Autowired TodoRepository todoRepository;

    @Test
    void findAll() {
        // given
        Member member = new Member();
        member.setId("asdf");
        member.setPassword("1234");

        memberRepository.save(member);

        Todo todo1 = new Todo();
        todo1.setMemberId(member.getId());
        todo1.setContent("스프링 공부하기");

        Todo todo2 = new Todo();
        todo2.setMemberId(member.getId());
        todo2.setContent("백준 1문제 풀기");
        // when
        todoRepository.save(todo1);
        todoRepository.save(todo2);

        // then
        Assertions.assertThat(todoRepository.findListByMemberId(member.getId()).size()).isEqualTo(2);
    }
}