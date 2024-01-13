package hello.hellospring.test;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TestRepositoryTest {

  TestRepository repositoryA = new TestRepository();
  TestRepository repositoryB = new TestRepository();

  @Test
  @DisplayName("Member 저장하기")
  public void save() {
    Member member = new Member();
    member.setName("영은");

    repositoryA.save(member);
    Member result = repositoryA.findByName(member.getName()).get();

    assertThat(result).isEqualTo(member);
  }

  @Test
  @DisplayName("서로 다른 repository에서 Member를 저장하면 sequence는 어떻게 동작할까")
  public void sequence_test() {
    Member memberA = new Member();
    memberA.setName("짱구");

    Member memberB = new Member();
    memberB.setName("유리");

    repositoryA.save(memberA);
    repositoryB.save(memberB);

    Long idA = memberA.getId();
    Long idB = memberB.getId();

    assertThat(idA).isEqualTo(1L);
    assertThat(idB).isEqualTo(2L);
  }



}