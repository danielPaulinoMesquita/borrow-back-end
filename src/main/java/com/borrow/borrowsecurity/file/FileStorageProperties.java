package com.borrow.borrowsecurity.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Here are the prefix(this case is "file") indicates
 * where is the value or local path for saving the images
 * this prefix is written in the application
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    // uploadDir is another key written in the application to indicate where the dir is and what will save
    private String uploadDir;
}
