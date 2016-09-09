package com.yanlin.test;

import java.io.File;

/**
 * 移动文件测试
 * @author bubblelin
 *
 */
public class MoveFileTest {

	public static void main(String[] args) {
		File dest = new File("D:\\ProgramFiles\\MyJava\\Template.java");
		File src = new File("D:\\bubblelin\\Download\\MyJava\\Template.java");
		src.renameTo(dest);
	}
}
