package com.massimo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AlmanacItem {

    private Long destinationStart;
    private Long sourceStart;
    private Long range;

    public boolean isInRange(Long sourceNumber) {
        return sourceNumber >= sourceStart && sourceNumber <= sourceStart + range;
    }

    public Long findDestination(Long number) {
        return destinationStart + (number - sourceStart);
    }

}
