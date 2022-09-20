package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션이 없습니다.";
        }

        // 세션 데이터 출력

        // 세션 이름 및 Member 정보 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name-> log.info("session name={}, value={}", name, session.getAttribute(name)));
        // 세션 Id
        log.info("sessionId={}", session.getId());
        // 비활성화 최대 시간 (1800초 (30분))
        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
        // 세션 생성 일자
        log.info("creationTime={}", new Date(session.getCreationTime()));
        // 마지막 세션 접근 시간
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
        // 새로 생성된 세션인지 확인
        log.info("isNew={}", session.isNew());

        return "세션 출력";
    }
}
