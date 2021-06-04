package com.example.demo.model.bid;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Bids{
    public List<Bid> bid;

    @Getter
    @Builder
    public class Bid {
        public String id;
        public String ts;
        public String ty;
        public String pl;
    }
}
