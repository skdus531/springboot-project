package com.jojoldu.webservice.service;

import com.jojoldu.webservice.domain.stock.Stocks;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    private static String STOCK_DATA_URL = "https://finance.naver.com/item/sise.nhn?code=005930";
    @PostConstruct
    public static List<Stocks> getStockData() throws IOException {

        List<Stocks> stocksList = new ArrayList<>();
        Document doc = Jsoup.connect(STOCK_DATA_URL).get();

        Elements contents = doc.select("table[class=type2 type_tax] tbody");
        Elements img = doc.select("div[class=chart] img");
        Elements company = doc.select("div[class=wrap_company] h2 a");

        for(Element content : contents){
            Elements tdContents = content.select("td");
            Elements flag = content.select("em");
            int sign = 1;
            if (flag.attr("class").contains("dn")) sign = -1;

            Stocks stocks = Stocks.builder()
                    .name(company.text())
                    .presentPrice(Integer.parseInt(tdContents.get(0).text().replaceAll("[^0-9]", "")))
                    .diffFromPrevDay(sign*Integer.parseInt(tdContents.get(2).text().replaceAll("[^0-9]", "")))
                    .marketPrice(Integer.parseInt(tdContents.get(7).text().replaceAll("[^0-9]", "")))
                    .high(Integer.parseInt(tdContents.get(9).text().replaceAll("[^0-9]", "")))
                    .low(Integer.parseInt(tdContents.get(11).text().replaceAll("[^0-9]", "")))
                    .imgURL(img.attr("src"))
                    .build();

            stocksList.add(stocks);
        }
        return stocksList;

    }
}