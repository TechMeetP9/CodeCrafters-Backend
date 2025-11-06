package com.code_crafters.app.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class UserTest {
     @Test
    void testOnCreateSetsTimestamps() {
        User user = new User();
        user.onCreate();  

        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
        assertEquals(user.getCreatedAt(), user.getUpdatedAt());
    }

    @Test
    void testOnUpdateChangesUpdatedAt() throws InterruptedException {
        User user = new User();
        user.onCreate();
        LocalDateTime firstUpdatedAt = user.getUpdatedAt();

        Thread.sleep(5);
        user.onUpdate();

        assertTrue(user.getUpdatedAt().isAfter(firstUpdatedAt));
    }
}
