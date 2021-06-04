package com.example.demo.model.bid;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Bid {
    private String id;
    private String ts;
    private String ty;
    private String pl;
}
