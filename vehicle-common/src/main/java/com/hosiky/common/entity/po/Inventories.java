package com.hosiky.common.entity.po;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Inventories {

    private Long id;
    private Long sellerId;
    private Long carId;
    private Integer quantity;
    private LocalDateTime updatedAt;
}
