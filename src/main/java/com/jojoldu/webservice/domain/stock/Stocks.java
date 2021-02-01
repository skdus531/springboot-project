package com.jojoldu.webservice.domain.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class Stocks {

    private String name; // 회사명

    private int presentPrice; // 현재

    private int diffFromPrevDay; // 전일대비

    private int marketPrice; // 시가

    private int high; // 고가

    private int low; // 저가

    private String imgURL;  // 차트

    private String code; // 회사 코드

    private int eps;

    private double per;

    private int reasonable_price; //적정주가
}