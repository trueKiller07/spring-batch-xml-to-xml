package com.trax.europeangateway.itemwriter.omegaxml;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class EdsClientItemWriter<T> implements ItemWriter<T> {

	@Override
	public void write(List<? extends T> items) throws Exception {}

}