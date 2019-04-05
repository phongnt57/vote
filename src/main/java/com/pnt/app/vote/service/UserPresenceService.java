package com.pnt.app.vote.service;

import com.pnt.app.vote.interfaces.IUserPresenceService;
import com.pnt.app.vote.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;

@Component
public class UserPresenceService extends ChannelInterceptorAdapter implements IUserPresenceService {
  @Autowired
  private UserService userService;

  @Override
  public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
    StompHeaderAccessor stompDetails = StompHeaderAccessor.wrap(message);

    if(stompDetails.getCommand() == null) { return; }

    switch(stompDetails.getCommand()) {
      case CONNECT:    
      case CONNECTED:
        toggleUserPresence(stompDetails.getUser().getName().toString(), true);
        break;
      case DISCONNECT:
        toggleUserPresence(stompDetails.getUser().getName().toString(), false);
        break;
      default:
        break;
    }
  }

  private void toggleUserPresence(String userEmail, Boolean isPresent) {
    try {
      User user = userService.findOne(userEmail);
//      userService.setIsPresent(user, isPresent);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}