package com.base.common.sercurity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.common.sercurity.entity.Menu;
import com.base.common.sercurity.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jingwk on 2019/12/01
 */
public class JwtTokenUtils {

    public static final String SPLIT_COMMA = ",";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "datax_admin";
    private static final String ISS = "admin";

    // 角色的key
    private static final String ROLE_CLAIMS = "rol";

    // 过期时间是3600秒，既是24个小时
    private static final long EXPIRATION = 86400L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 3600*27 * EXPIRATION;

    // 创建token
    public static String createToken(Long id, String username, Role role, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        HashMap<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, String.valueOf(role.getRoleName()));
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("username", username);
        //json.put("role", JSON.toJSONString(role));
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(json.toJSONString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    // 从token中获取用户组
    public static List<Menu> getMenus(String token) {
        JSONObject json = getJson(token);
        return JSONObject.parseArray(json.getString("menus"), Menu.class);
    }

    // 从token中获取角色
    public static Role getRole(String token) {
        JSONObject json = getJson(token);
        return JSON.parseObject(json.getString("role"), Role.class);
    }

    // 从token中获取用户名
    public static String getUsername(String token) {
        JSONObject json = getJson(token);
        return json.getString("username");
    }

    public static JSONObject getJson(String token){
        return JSONObject.parseObject(getTokenBody(token).getSubject());
    }

    // 从token中获取用户名
    public static Long getUserId(String token) {
        return getJson(token).getLong("id");
    }

    // 获取用户角色
    public static String getUserRole(String token) {
        return (String) getTokenBody(token).get(ROLE_CLAIMS);
    }

    // 是否已过期
    public static boolean isExpiration(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
