package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("gun");

        repository.save(member); //member의 주소
        Long id = member.getId(); //1L
        Member result = repository.findById(id).get(); //member의 주소 받아옴

        assertThat(result).isEqualTo(member); //이게 같을까? 같으면 test 통과
    }

    @Test
    public void findByName() {
        Member 짱구 = new Member();
        짱구.setName("짱구");
        repository.save(짱구);

        Member 유리 = new Member();
        유리.setName("유리");
        repository.save(유리);

        짱구.getId(); //1L
        유리.getId(); //2L
        Member result = repository.findByName("유리").get(); //유리

        assertThat(result).isEqualTo(유리);
    }

    @Test
    public void finaAll() {
        Member m1 = new Member();
        m1.setName("kim");
        repository.save(m1);

        Member m2 = new Member();
        m2.setName("lee");
        repository.save(m2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
