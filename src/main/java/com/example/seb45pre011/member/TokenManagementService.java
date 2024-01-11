package com.example.seb45pre011.member;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TokenManagementService {

    private TokenBlackList tokenBlackList;

    public TokenManagementService(TokenBlackList tokenBlackList){
        this.tokenBlackList = tokenBlackList;
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void removeExpiredTokens() {
        tokenBlackList.removeExpiredTokens();
    }
}
