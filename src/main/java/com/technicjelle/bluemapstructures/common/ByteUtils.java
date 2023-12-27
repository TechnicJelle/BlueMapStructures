package com.technicjelle.bluemapstructures.common;

import java.nio.ByteBuffer;

public class ByteUtils {
	// From https://stackoverflow.com/questions/4485128/how-do-i-convert-long-to-byte-and-back-in-java

	public static byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(x);
		return buffer.array();
	}

	public static long bytesToLong(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.put(bytes);
		buffer.flip();
		return buffer.getLong();
	}

	public static byte[] intToBytes(int x) {
		ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
		buffer.putInt(x);
		return buffer.array();
	}

	public static int bytesToInt(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
		buffer.put(bytes);
		buffer.flip();
		return buffer.getInt();
	}
}
