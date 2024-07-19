package com.samparkkhata.helpers;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AlertMessages {
    private String message;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private MessageType messageType = MessageType.INFO;

}
