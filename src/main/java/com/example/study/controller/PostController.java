package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
// method 주소가 동일하다면 에러 발생
// but, class request mapping은 중복되더라도 문제가 되지않는다.
@RequestMapping("/api")
public class PostController {
        /*  사용 예제 : html form 태그 / ajax 검색
            * http post body -> data ; @RequestBody 이용
            * json, xml, multipart-form, text-plain 형태로 전달 가능
            * json이외에 다른 형태 파일 받을 시에는 :
              => @PostMapping(value = "/postMethod", produces = {"application-json"})
         */

        //@RequestMapping(method = RequestMethod.POST, path = "/postMetod")
        @PostMapping(value = "/postMethod")
        public SearchParam postMethod(@RequestBody SearchParam searchParam) {
            return searchParam;
        }

        @PutMapping("/putMethod")
        public void put() {

        }

        @PatchMapping("/patchMethod")
        public void patch() {

        }
}
