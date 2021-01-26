package com.jojoldu.webservice.web;

import com.jojoldu.webservice.domain.covid.KoreaStats;
import com.jojoldu.webservice.service.CoronaVirusDataService;
import com.jojoldu.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2017. 12. 31.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        System.out.println("fuck");
        return "main";
    }
    private final CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/korea")
    public String korea(Model model) throws IOException {

        List<KoreaStats> koreaStatsList = coronaVirusDataService.getKoreaCovidDatas();
        model.addAttribute("koreaStats", koreaStatsList);

        return "korea";
    }

}
