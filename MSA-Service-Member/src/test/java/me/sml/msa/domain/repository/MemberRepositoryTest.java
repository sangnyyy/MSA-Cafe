package me.sml.msa.domain.repository;

import me.sml.msa.domain.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원확인() {
        //given
        String memberName1 = "SML";
        String memberName2 = "MDL";
        memberRepository.save(
                Member.builder()
                        .name(memberName1)
                        .build()
        );
        //when
        Member member1 = memberRepository.findByName(memberName1);
        Member member2 = memberRepository.findByName(memberName2);
        //then
        assertThat(member1).isNotNull();
        assertThat(member2).isNull();
    }

}