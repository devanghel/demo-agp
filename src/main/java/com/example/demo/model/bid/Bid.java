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

//    Log the bid id(id), timestamp(ts), queue “name”( ty), and payload (decoded from Base64 pl) when the queueing is completed.
// TODO: 06/06/2021 use placeholders for string concatenation : %s
    @Override
    public String toString() {
        return "Bid{" +
                "id='" + id + '\'' +
                ", timestamp='" + ts + '\'' +
                ", queue='" + ty + '\'' +
                ", payload='" + pl + '\'' +
                '}';
    }
}
