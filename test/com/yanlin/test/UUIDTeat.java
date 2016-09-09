package com.yanlin.test;

import java.util.UUID;

public class UUIDTeat {

	public static void main(String[] args) {
		String name = UUID.randomUUID().toString().replace("-", "");
		System.out.println(name);
	}
}
