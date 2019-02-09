package me.sml.msa.springboot.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("MSA-Service-Member")
public interface IMSAServiceMember {

    @GetMapping("/coffeeMember/v1.0/{memberName}")
    boolean cafeMember(@PathVariable("memberName") String memberName);
}
