package com.deep;

import org.mybatis.generator.api.ShellRunner;

public class CreateMapperApp {

	public static void main(String[] args) {
        args = new String[] { "-configfile", "src\\main\\resources\\config\\mybatis-generator-config.xml", "-overwrite" };
        ShellRunner.main(args);
    }
}
