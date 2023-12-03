package com.anabada.config;


import com.anabada.config.token.JwtTokenProvider;
import com.anabada.entity.Member;
import com.anabada.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker//웹소켓 메세지 중재자
@RequiredArgsConstructor
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(""); // publish

        registry.enableSimpleBroker("/send","/product","/chat"); // subscribe
    }


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                String token = accessor.getFirstNativeHeader("x-auth-token");

                accessor.setSessionId(accessor.getSessionId());
                if(jwtTokenProvider.isValidToken(token)){
                    Member member = jwtTokenProvider.getTokenConverterMember(token);
                    accessor.setUser( memberService.getAuthentication(member));
                    return message;
                }

                return message;
            }
        });
    }


}

