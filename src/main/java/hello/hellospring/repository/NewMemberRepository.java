package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface NewMemberRepository extends JpaRepository<Member, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m set m.active = true where m.active = false")
    int bulkUpdateMember();

}
