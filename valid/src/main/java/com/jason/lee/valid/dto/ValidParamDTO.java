package com.jason.lee.valid.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author huanli9
 * @description
 * @date 2021/9/13 14:45
 */
@Data
public class ValidParamDTO {

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private List<Item> itemList;

}
