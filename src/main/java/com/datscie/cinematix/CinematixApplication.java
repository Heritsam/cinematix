package com.datscie.cinematix;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.datscie.cinematix.views.AuthView;

import java.awt.EventQueue;

@SpringBootApplication
public class CinematixApplication {

	public static void main(String[] args) {
		// SpringApplication.run(CinematixApplication.class, args);
        // EventQueue.invokeLater(() -> {
        //     new AuthView().setVisible(true);
        // });
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CinematixApplication.class)
                .headless(false).run(args);
        EventQueue.invokeLater(() -> {
            context.getBean(AuthView.class).setLocationRelativeTo(null);
            context.getBean(AuthView.class).setVisible(true);
        });
	}

}
