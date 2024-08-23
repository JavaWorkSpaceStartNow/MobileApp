package com.mobile.service;

import java.io.IOException;

public interface FileUplodeService {

	String uploadBase64Image(byte[] decodeBase64, long id, String type, String fileName) throws IOException;

}
