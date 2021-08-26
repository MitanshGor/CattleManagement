package com.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
public class FcmInitialization{
	

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        File initialFile = new File("royal-counselling-app-firebase-adminsdk-9mn28-e7f7fadfd9.json");
        InputStream targetStream = new FileInputStream(initialFile);
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(targetStream);
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
        return FirebaseMessaging.getInstance(app);
    }
}