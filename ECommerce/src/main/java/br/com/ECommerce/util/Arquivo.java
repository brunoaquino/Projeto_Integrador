package br.com.ECommerce.util;

public class Arquivo {

	private byte[] file;
	private String fileName;
	private String contentType;

	public byte[] getFile() {
		return file;
	}

	public String getFileName() {
		return fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}