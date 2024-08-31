package com.example.baseballtalk.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MaxDataDTO {
    private int id;
    private int maxHit;
    private int maxHomerun;
    private int maxRbi;
    private int maxStolenBase;
    private int maxWin;
    private float maxInning;
}
