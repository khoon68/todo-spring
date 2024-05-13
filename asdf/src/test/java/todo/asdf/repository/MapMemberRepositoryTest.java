package todo.asdf.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import todo.asdf.domain.Member;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MapMemberRepositoryTest {

    MemberRepository memberRepository = new MapMemberRepository();

    @Test
    @DisplayName("회원 조회 테스트")
    void saveTest() {
        // given
        Member member1 = new Member();
        member1.setId("asdf");
        member1.setPassword("123");

        // when
        memberRepository.save(member1);

        // then
        org.assertj.core.api.Assertions.assertThat(member1.getId()).isEqualTo(memberRepository.findById(member1.getId()).getId());
    }
}