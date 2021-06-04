package com.example.demo.model.bid;

import lombok.Getter;

@Getter
public enum BidType {
    ZU("ZU"),
    AQ("AQ");

    private final String type;

    BidType(String type) {
        this.type = type;
    }

}
