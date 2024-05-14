package todo.asdf.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import todo.asdf.AppConfig;
import todo.asdf.domain.Member;

@SpringBootTest
@Transactional
class JdbcTemplateMemberRepositoryTest {

//    private final ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//    private final MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("가입 후 멤버 조회")
    public void readAfterSignIn() {
        // given
        Member member = new Member();
        member.setId("asdf");
        member.setPassword("1234");

        // when
        memberRepository.save(member);

        // then
        Assertions.assertThat(member.getId()).isEqualTo(memberRepository.findById(member.getId()).getId());
    }

    @Test
    @DisplayName("멤버 전원 조회")
    public void readAllMember() {
        // given
        Member member1 = new Member();
        member1.setId("asdf");
        member1.setPassword("1234");

        Member member2 = new Member();
        member2.setId("zxcv");
        member2.setPassword("4567");

        // when
        memberRepository.save(member1);
        memberRepository.save(member2);

        // then
        Assertions.assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }
}