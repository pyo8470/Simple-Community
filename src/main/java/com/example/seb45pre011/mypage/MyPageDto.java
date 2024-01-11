package com.example.seb45pre011.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MyPageDto {
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ResponseDto{
        private long userId;
        private String email;
        private String username;
        private String gender;
        private String phone;
        private String nick;
        private int userPostCount;
        private int userCommentCount;
    }
}
