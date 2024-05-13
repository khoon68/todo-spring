package todo.asdf.repository;

import todo.asdf.domain.Member;

import java.util.*;

public class MapMemberRepository implements MemberRepository{
    private static long sequence = 0L;
    static Map<Long, Member> memberStore = new HashMap<>();

    @Override
    public Member save(Member member) {
        member.setIndex(sequence++);
        memberStore.put(member.getIndex(), member);
        return member;
    }

    // id로 회원 조회기능 수정
    // member-list 페이지에 member id가 안나오는것 해결하기
    @Override
    public Member findById(String id) {
        return memberStore.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memberStore.values());
    }
}
