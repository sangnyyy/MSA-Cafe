package me.sml.msa.springboot.rest;

import me.sml.msa.domain.model.Member;
import me.sml.msa.domain.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CoffeeMemberRestController.class)
public class CoffeeMemberRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberRepository memberRepository;


    @Test
    public void 회원유뮤확인_API() throws Exception {
        //given
        final String memberName1 = "SML";
        final String memberName2 = "CML";
        when(memberRepository.findByName(memberName1)).thenReturn(Member.builder().name("SML").build());

        //when, then
        mockMvc.perform(get("/api/msa/v1.0/" + memberName2))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }

}