package com.do_an.clinic.components;


import com.do_an.clinic.exceptions.DataInvalidParamException;
import com.do_an.clinic.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {
    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.secretKey}")
    private String secretKey;

    private String generateSecretKey(){
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32];
        random.nextBytes(keyBytes);
        String secretKey = Encoders.BASE64.encode(keyBytes);
        return secretKey;
    }

    public String generateToken(User user) throws DataInvalidParamException {
        Map<String, Object> claims = new HashMap<>();
//        this.generateSecretKey();
        claims.put("phoneNumber", user.getPhoneNumber());
        try{
            String toke = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getPhoneNumber())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return toke;
        } catch (Exception e) {
            throw new DataInvalidParamException("Cannot create jwt token, error: " + e.getMessage());
        }
    }
    private Key getSignInKey(){
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        //Keys.hmacShaKeyFor(Decoders.BASE64.decode("328/wt6kp3Kia0s93Ff5Zrv1wgvbGkOkkRpyZWXSTHU="))
        return Keys.hmacShaKeyFor(bytes);
    }

    // Hàm trích xuất tất cả các claims từ token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())         // Thiết lập khóa bí mật để giải mã token
                .build()                               //Tạo một đối tượng JwtParser từ builder
                .parseClaimsJws(token)                 // Parse token và trích xuất các claims
                .getBody();                            // Lấy phần claims từ token
    }
    // Hàm lấy một claim cụ thể từ token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);  // Trích xuất toàn bộ claims
        return claimsResolver.apply(claims);            // Áp dụng hàm để trích xuất claim cụ thể
    }

    //Hàm kiểm tra token đã hết hạn chưa
    public boolean isTokenExpired(String token){
        Date expirationDate = this.extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    // Trích xuất ra phoneNumber
    public String extractPhoneNumber(String token){

        return extractClaim(token, Claims::getSubject);
    }
    // Kiểm tra UserName với token còn hạn hay không và cái phone number có trùnguraSecurytiFig với userName của UserDetail không
    public boolean validateToken(String token, UserDetails userDetails){
        String phoneNumber = extractPhoneNumber(token);
        return (phoneNumber.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));
    }
}
