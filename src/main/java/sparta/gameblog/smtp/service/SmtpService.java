package sparta.gameblog.smtp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.gameblog.dto.TokenRequestDto;
import sparta.gameblog.entity.Token;
import sparta.gameblog.entity.User;
import sparta.gameblog.repository.TokenRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SmtpService {

    private final JavaMailSender mailSender;
    private final TokenRepository tokenRepository;

    @Async
    public void sendEmail(String toEmail, String title, String content) {
        SimpleMailMessage message = createEmailForm(toEmail, title, content);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            log.error("MailService.sendEmail exception occur toEmail: {}, " +
                    "title: {}, content: {}", toEmail, title, content);
            throw new RuntimeException("이메일 전송 실패");
        }
    }

    private SimpleMailMessage createEmailForm(String toEmail, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(content);

        return message;
    }

    // token 의 만료 확인
    public User getUserByToken(TokenRequestDto requestDto) {
        Token token = this.tokenRepository.findByToken(requestDto.getToken())
                .orElseThrow(RuntimeException::new);
        if (token.expired()) {
            throw new RuntimeException("expired token");
        }
        User user = token.getUser();
        this.tokenRepository.delete(token);
        return user;
    }
}
