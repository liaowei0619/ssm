package com.louis.utilTools;

import java.util.Random;

public class MathUtils {

	/**
	 * 
	 * @return
	 */
	public static int getSixNum() {
		Random dom = new Random();

		return dom.nextInt(1000000);
	}

	public static void main(String[] args) {
		System.out.println(getSixNum());
	}
}
