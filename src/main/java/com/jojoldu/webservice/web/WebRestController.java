package com.jojoldu.webservice.web;

import com.jojoldu.webservice.domain.covid.KoreaStats;
import com.jojoldu.webservice.dto.posts.PostsSaveRequestDto;
import com.jojoldu.webservice.service.CoronaVirusDataService;
import com.jojoldu.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2017. 12. 24.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsService postsService;
    private Environment env;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto){
        return postsService.save(dto);
    }

    @GetMapping("/profile")
    public String getProfile () {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }
}