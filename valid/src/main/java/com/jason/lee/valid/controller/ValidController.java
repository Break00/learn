package com.jason.lee.valid.controller;

import com.jason.lee.valid.dto.ValidParamDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * @author huanli9
 * @description
 * @date 2021/9/13 14:45
 */
@Validated
@RestController
public class ValidController {

    @RequestMapping(value = "/valid/1")
    public ValidParamDTO test1(@Valid ValidParamDTO paramDTO) {
        return paramDTO;
    }

    @RequestMapping(value = "/valid/2")
    public ValidParamDTO test2( ValidParamDTO paramDTO) {
        return paramDTO;
    }

    // 必须在类上添加注解@Validated
    @RequestMapping(value = "/valid/3")
    public String test3(@NotBlank String name) {
        return name;
    }

    // 嵌套校验
    @RequestMapping(value = "/valid/4")
    public ValidParamDTO test4(@Valid @RequestBody ValidParamDTO paramDTO) {
        return paramDTO;
    }
}
