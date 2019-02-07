package me.sml.member.springboot.rest;

import lombok.RequiredArgsConstructor;
import me.sml.member.domain.repository.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class CoffeeMemberRestController {

    private final MemberRepository memberRepository;

    @GetMapping("/v1.0/{memberName}")
    public boolean isMember(@PathVariable("memberName") String memberName){
        if(memberRepository.findByName(memberName) != null){
            return true;
        }else{
            return false;
        }
    }

    @GetMapping("/v1.0")
    public boolean isMemberWithNoName(){
        return true;
    }
}
