package com.example.seb45pre011.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberDto {
    @Setter
    @Getter
    public static class PostDto{
        private String email;
        private String username;
        private String password;
        private String gender;
        private String phone;
        private String nick;

    }

    @Getter
    public static class LoginDto{
        private String email;
        private String password;
    }

    @Getter
    public static class PatchDto{
        private String password;
        private String phone;
        private String nick;
    }
}