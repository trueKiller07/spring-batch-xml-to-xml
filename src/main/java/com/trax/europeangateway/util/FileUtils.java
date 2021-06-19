package com.trax.europeangateway.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Service
public class FileUtils {


	@Value("${europeangateway.outbound.path}")
	private String outboundBasePath;

	public String getOutputFilePath(String inputFilePath, String subAccount) {
		String userFolder = "testOut";
		StringBuilder outPath = new StringBuilder(outboundBasePath);
		createDirectory(outboundBasePath);

		String fileName = getFileName(inputFilePath);
		outPath.append(EuGatewayConstants.FORWARD_SLASH);
		outPath.append(userFolder);
		createDirectory(outPath.toString());
		outPath.append(EuGatewayConstants.FORWARD_SLASH);
		outPath.append(fileName);
		return outPath.toString();
	}

	protected void createDirectory(String directory) {
		Path path = Paths.get(directory);
		log.debug("Call to create directory {}", directory);
		if (!Files.exists(path)) {
			log.info("Creating directory {}", directory);
			path.toFile().mkdirs();
		} else {
			log.debug("Directory {} already exists. No need to create.", directory);
		}
	}

	public String getFileName(String filePath) {
		if (filePath == null)
			return filePath;
		String[] inputFileArr = filePath.split(Pattern.quote(File.separator));
		return inputFileArr[inputFileArr.length - 1];
	}
}
