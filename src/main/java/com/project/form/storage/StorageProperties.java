package com.project.form.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration("storage")
public class StorageProperties {

    @Value("${spring.storage.file-directory}")
    private String location;

}
