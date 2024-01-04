package com.suresh.TimeSheetApp.util;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class TimeCardId {
	
private String CHARACTERS = "0123456789";
	
	
	public  String timeCardId() {
		int length =10;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }
	

}
