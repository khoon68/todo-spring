package todo.asdf.repository;

import todo.asdf.domain.Member;

import java.util.*;

public class MapMemberRepository implements MemberRepository{
    static Map<String, Member> memberStore = new HashMap<>();

    @Override
    public Member save(Member member) {
        memberStore.put(member.getId(), member);
        System.out.println(member.getId() + ", " + member.getPassword());
        return member;
    }

    // member-list 페이지에 member id가 안나오는것 해결하기
    // -> input 태그에서 name 속성과 받는 객체의 변수의 이름을 일치시켜야 함
    @Override
    public Member findById(String id) {
        return memberStore.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memberStore.values());
    }
}
