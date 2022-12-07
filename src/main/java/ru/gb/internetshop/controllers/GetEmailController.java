package ru.gb.internetshop.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.internetshop.exceptions.ResourceNotFoundException;
import ru.gb.internetshop.services.UserService;
import ru.gb.internetshop.utils.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/get_my_email")
@RequiredArgsConstructor
@Slf4j
public class GetEmailController {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @GetMapping
    public String getMyEmail(HttpServletRequest request) {
        System.out.println(request);
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String token = authHeader.substring(7);
                return userService.findByUsername(jwtTokenUtil.getUsernameFromToken(token)).get().getEmail();
            } catch (ExpiredJwtException e) {
                log.info("The token is expired");
                throw new ResourceNotFoundException("Email не найден");
            }
        }
        return "Error";
    }
}