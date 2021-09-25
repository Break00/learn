package com.jason.lee.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;
import java.util.Map;

/**
 * @author huanli9
 * @description
 * @date 2021/1/27 9:19
 */
public class FieldMappingUtil {

    public static void main(String[] args) {
        fieldMapper("test.xlsx", 1,"patient", "patientVisitInfoDTO");
    }

    public static void fieldMapper(String path, int index, String javaBeanName, String entityBeanName) {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(path), index);
        List<Map<String,Object>> readAll = reader.readAll();
        readAll.stream().forEach(map -> {
            // 数据库字段
            String dbName = map.get("dbField").toString();
            // 接口字段
            String javaName = map.get("javaField").toString();
            if (!StrUtil.hasEmpty(dbName, javaName)) {
                dbName = convertWord(dbName);
                javaName = fistLetterToUpper(javaName);
                String template = "{}.set{}({}.set{});";
                System.out.println(StrUtil.format(template, javaBeanName, javaName, entityBeanName, dbName));
            }
        });
    }

    private static String convertWord(String input) {
        String split = "_";
        StringBuilder output = new StringBuilder();
        String[] words = input.toLowerCase().split(split);
        for (int i = 0; i < words.length; i++) {
            output.append(fistLetterToUpper(words[i]));
        }
        return output.toString();
    }

    private static String fistLetterToUpper(String input) {
        if (StrUtil.isEmpty(input)) {
            return "";
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
