import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
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

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        Member m1 = new Member();
        m1.setName("kim");
        repository.save(m1);

        Member m2 = new Member();
        m2.setName("lee");
        repository.save(m2);

        Member result = repository.findByName("lee").get();

        assertThat(result).isEqualTo(m2);
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
