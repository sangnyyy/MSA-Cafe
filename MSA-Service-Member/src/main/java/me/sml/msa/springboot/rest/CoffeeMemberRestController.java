package me.sml.msa.springboot.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import me.sml.msa.domain.repository.MemberRepository;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequiredArgsConstructor
public class CoffeeMemberRestController {

    private final MemberRepository memberRepository;

    @HystrixCommand
    @GetMapping("/coffeeMember/v1.0/{memberName}")
    public boolean isMember(@PathVariable("memberName") String memberName){
        if(memberRepository.findByName(memberName) != null){
            return true;
        }else{
            return false;
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackFunction")
    @GetMapping("/fallbackTest")
    public String fallbackTest() throws Throwable{
        throw new Throwable("fallbackTest");
    }
    public String fallbackFunction(){
        return "fallbackFunction()";
    }

}
