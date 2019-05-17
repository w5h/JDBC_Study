package com.sikiedu.exception;

import java.util.ArrayList;

public class ExceptionDemo01 {
public static void main(String[] args) {
	try {
		ArrayList<Integer> list =null;
		list.add(34);
		list.add(344);
	}
	 catch (NullPointerException e) {
	//	System.out.println("hellow word");
		throw e;
	}
}
}
