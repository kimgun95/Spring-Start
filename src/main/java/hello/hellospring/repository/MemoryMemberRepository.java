package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L;

  @Override
  public Member save(Member member) { //member의 주소
    sequence++;
    member.setId(sequence); //member 주소에 있는 id를 설정하겠다.
    store.put(member.getId(), member); //sequence : member의 주소
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) { //1L
    return Optional.ofNullable(store.get(id)); //1L : member주소
  }

  @Override
  public Optional<Member> findByName(String name) { //유리
    return store.values()
        .stream()
        .filter(member -> member.getName().equals(name))
        .findAny();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  public void clearStore() {
    store.clear();
  }
}
