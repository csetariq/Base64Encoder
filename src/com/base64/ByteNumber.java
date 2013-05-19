package com.base64;

public class ByteNumber {
	
	private static final int BYTE = 8; 

	/**
	 * Converts the given number to its Radix-2 representation of specified length
	 * @param num		The number which should be converted
	 * @param length	Desired length of the Radix-2 representation
	 * @return			Radix-2 representation of the <code>num</code> in String form
	 */
	protected static String binValue(byte num, int length) {
		String bin = Integer.toBinaryString(num);
		String pad = "";
		String finalBinValue;
		for(int j = length - bin.length(); j > 0; j--)
			pad += "0";
		finalBinValue = pad + bin;
		
		if(finalBinValue.length() > BYTE)
			finalBinValue = finalBinValue.substring(finalBinValue.length()-8);
		
		return finalBinValue;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0; i<=255; i++)
			System.out.println(i +" " +ByteNumber.binValue((byte)i,8));
	}

}
