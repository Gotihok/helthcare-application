package com.wsei.healthcare.backend.user.api;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.context.ApplicationEvent;

@Getter
@Accessors(chain = true)
public class UserEmailChangedEvent extends ApplicationEvent {
    private final Long userId;
    private final String newEmail;

    public UserEmailChangedEvent(Object source, Long userId, String newEmail) {
        super(source);
        this.userId = userId;
        this.newEmail = newEmail;
    }
}
