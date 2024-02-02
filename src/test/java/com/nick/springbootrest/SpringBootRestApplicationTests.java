package com.nick.springbootrest;

import com.nick.springbootrest.controller.StorageController;
import com.nick.springbootrest.model.Storage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootRestApplicationTests {

    @Autowired
    private StorageController storageController;

    @Test
        void contextLoads() {
            assertThat(storageController).isNotNull();
        }


}
