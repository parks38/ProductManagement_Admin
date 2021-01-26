package com.example.study.controller;


import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
// api 주소 매핑 - localhost:8080/api
@RequestMapping("/api")
public class GetController {
    // localhost:8080/api/getMethod
    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")
    public String getRequest() {

        return "Hi getMethod";
    }

    //get 처리 - 메소드 지정하지않아도되고 path만 지정
    // localhost:8080/api/getParameter?id=1234&password=abcd
     @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam String password) {
        /*  Spring 만약, 지역내 변수로 password를 사용하는경우
            ** 추천 : parameter로 들어오는 값은 local 변수로 지정하지 않기
            (@RequestParam String id, @RequestParam(name = "password") String pwd)
            String password = "bbbb";
            * return id+pwd;
         */
        System.out.println("id: " + id);
        System.out.println("password: " + password);

        return id+password;
    }

    //localhost:8080/api/getMultiParameter?account=abcd&email=study@test.com&page=10
    // model - SearchParam 통해 정보 받기 : getter, Setter
    /* SearchParam searchParam =>
     { @RequestParam String account,@RequestParam String email,@RequestParam String page }
     */
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());
        // { "account" : "", "email" : "" , "page" : 0 } JSON 형태
        return searchParam;
    }

    // Header Testing
    @GetMapping("/header")
    public Header getHeader() {
        // {"resultcode" : "OK" , "description" : "OK" }
        return Header.builder().resultCode("OK").description("OK").build();
    }

}
