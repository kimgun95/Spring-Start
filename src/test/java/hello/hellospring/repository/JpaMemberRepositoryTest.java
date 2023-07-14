package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@ActiveProfiles("test")
@SpringBootTest
public class JpaMemberRepositoryTest {

    @Autowired
    private NewMemberRepository memberRepository;

    @Test
    @Transactional
    void not_bulk_update_using_two_member() {

        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("kim");
        member2.setName("park");
        memberRepository.save(member1);
        memberRepository.save(member2);
        System.out.println("----------------------");
        member1.setActive(true);
        member2.setActive(true);
        memberRepository.flush();

        assertThat(member1.getActive()).isEqualTo(memberRepository.findById(member1.getId()).get().getActive());
    }

    @Test
    @Transactional
    void bulk_update_using_two_member() {

        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("kim");
        member2.setName("park");
        memberRepository.save(member1);
        memberRepository.save(member2);
        System.out.println("----------------------");
        memberRepository.bulkUpdateMember();

        assertThat(member1.getActive()).isEqualTo(memberRepository.findById(member1.getId()).get().getActive());
    }

    @Test
    @Transactional
    void bulk_update_and_check_value_using_two_member() {

        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("kim");
        member2.setName("park");
        memberRepository.save(member1);
        memberRepository.save(member2);
        System.out.println("----------------------");
        memberRepository.bulkUpdateMember();

        Member updateMember = memberRepository.findById(member1.getId()).get();
        System.out.println(updateMember.getActive());
        assertThat(updateMember.getActive()).isEqualTo(true);
    }
}
