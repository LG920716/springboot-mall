package com.ray.springmail.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {

    @NotEmpty
    private List<BuyItem> buyItemList;
}
