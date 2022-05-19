package com.szmirren.utils;

import com.szmirren.common.Constant;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author Rock
 * @date 2022/4/6 3:05 下午
 * @description
 */
public class FileUtil {

    public static String getFilePath(){
        // 项目路径
        String projectFilePath = Thread.currentThread().getContextClassLoader().getResource(Constant.TEMPLATE_DIR_NAME).getFile();
        // 打包路径
        String jarFilePath = Paths.get(Constant.TEMPLATE_DIR_NAME).toFile().getName();

        // 这里优先返回项目路径
        File file = new File(projectFilePath);
        if (file.exists() && file.isDirectory() && Arrays.stream(file.list()).allMatch(s -> s.endsWith(".ftl"))) {
            return projectFilePath;
        }
        return jarFilePath;
    }
}
