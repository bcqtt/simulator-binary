package com.demo.netty;

import java.util.Random;

import javax.xml.bind.DatatypeConverter;

public class StringTest {

	public static void main(String[] args) {
		String str = "d 8e 14 0 1 0 1 40 0 e4 60 b2 46 0 b0 d4 b2 46 0 50 92 b2 46 0 ca 8e 1a 47 1 d0 e8 1a 47 1 9f 83 1a 47 1 c1 77 5a 47 1 8d 1 2a 47 1 d9 f3 5b 47 1 3c 58 9c 45 0 c9 81 9 47 1 98 81 21 c5 0 81 e0 9 47 1 9a 76 c7 42 0";
		String data[] = str.split(" ");
		byte dataByte[] = new byte[data.length];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			Integer in = Integer.valueOf(data[i], 16);
			dataByte[i] = in.byteValue();
			
		}
		System.out.println(sb.toString());
		for (byte b : dataByte) {
			System.out.print(b + " ");
		}

		str = "000AB";
		Integer in = Integer.valueOf(str, 16);
		String st = Integer.toHexString(in).toUpperCase();
		System.out.println(st);
		st = String.format("%5s", st);
		st = st.replaceAll(" ", "0");
		System.out.println(st);
		
		for(int a=0;a<5;a++) {
			int index = new Random().nextInt(3) + 1;
			System.out.println(index);
		}
		
		int btTest =((byte)0x8e & (byte)0x80);
		System.out.println(btTest);
		
		byte stationAddr[] = {(byte)0xdc,(byte)0x1e,(byte)0x66,(byte)0x00};
		String stationHex = DatatypeConverter.printHexBinary(stationAddr);
		System.out.println(stationHex);
	}

}
