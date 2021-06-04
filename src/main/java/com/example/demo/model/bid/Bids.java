package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Bids{
    public List<Bid> bid;

    @Getter
    class Bid {
        public String id;
        public String ts;
        public String ty;
        public String pl;
    }
}
