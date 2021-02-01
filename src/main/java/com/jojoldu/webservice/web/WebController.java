package com.jojoldu.webservice.web;

import com.jojoldu.webservice.domain.posts.Posts;
import com.jojoldu.webservice.domain.stock.Stocks;
import com.jojoldu.webservice.dto.posts.PostsMainResponseDto;
import com.jojoldu.webservice.service.PostsService;
import com.jojoldu.webservice.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String main(Model model) throws IOException {
        List<PostsMainResponseDto> list = postsService.findAllDesc();

        for(int i = 0; i<list.size(); i++){
            Stocks stock = StockService.addStockData(list.get(i).getContent());
            list.get(i).setAuthor(Integer.toString(stock.getPresentPrice()));
        }
        model.addAttribute("posts",list);

        return "main";
    }

    @GetMapping("/stock")
    public String stock(@RequestParam("index") String code, Model model) throws IOException {

        List<Stocks> stocksList =StockService.getStockData(code);
        model.addAttribute("stocks", stocksList);

        return "stock";
    }
    @GetMapping("/guide")
    public String guide(@RequestParam("index") String code, Model model) throws IOException {

        List<Stocks> stocksList =StockService.getStockData(code);
        model.addAttribute("stocks", stocksList);

        return "guide";
    }
}
