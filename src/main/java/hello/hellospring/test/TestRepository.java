package hello.hellospring.test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.*;

public class TestRepository implements MemberRepository  {

  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L; //1L :memberA,

  private long id = 0L;

  @Override
  public Member save(Member member) { //memberB
    Long id2 = 0L; //메서드 변수, 지역변수
    sequence++; //1L
    member.setId(sequence);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values()
        .stream()
        .filter(member -> member.getName().equals(name))
        .findAny();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

}
