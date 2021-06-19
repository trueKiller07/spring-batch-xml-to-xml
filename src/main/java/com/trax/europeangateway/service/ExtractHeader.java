package com.trax.europeangateway.service;

public interface ExtractHeader<T> {

	public T readHeader(String filePath);
}
