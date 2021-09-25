package com.jason.lee.valid.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author huanli9
 * @description
 * @date 2021/9/13 16:33
 */
@Data
public class Item {

    @Length(min = 3)
    private String itemName;
}
