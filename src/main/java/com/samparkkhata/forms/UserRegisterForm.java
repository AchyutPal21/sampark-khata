package com.samparkkhata.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRegisterForm {

    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private String userConfirmPassword;
    private String userPhoneNumber;
    private String userAbout;
}
