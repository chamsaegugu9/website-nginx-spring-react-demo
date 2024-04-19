package com.example.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdemo.dto.ResponseDto;
import com.example.springdemo.model.HelloModel;
import com.example.springdemo.model.UserRole;
import com.example.springdemo.service.HelloService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

//import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



// 유저에 관한 컨트롤러
@RestController
// @RequestMapping("/api")
@RequestMapping("/QQQ")
public class HelloController {

    @Autowired
    private HelloService helloService;


    // 유저 정보확인 
    @GetMapping("/hello/{id}")
    public HelloModel getHello(@PathVariable(name="id") long id, HttpServletRequest requset) throws Exception {
        HttpSession session = requset.getSession();
        HelloModel loginUser = (HelloModel)session.getAttribute("loginUser");
        if(loginUser.getId() != id) {
            throw new Exception("error");
        }
        HelloModel getModel = helloService.getHelloModel(id);
        
        return getModel;
    }

    // 모든 유저 정보확인 
    // @GetMapping(value = {"/hello", "/hello/"})
    // public List<HelloModel> getAllHello() {
    //     List<HelloModel> allHelloModel = helloService.getAllHelloModel();
    //     return allHelloModel;
    // }


    // 유저 회원가입 
    // @PostMapping("/hello/save")
    // public HelloModel saveHelloModel(@RequestBody HelloModel hm) {
    //     HelloModel saveData = new HelloModel();
    //     saveData.setName("hello_world");
    //     saveData.setEmail("hello@world.com");
    //     helloService.saveHelloModel(saveData);
    //     return hm;
    // }
    @GetMapping("/hello/save")
    public HelloModel saveHello(@RequestBody(required = false) HelloModel hm) {
        HelloModel saveData = new HelloModel();
        saveData.setName("hello_world");
        saveData.setEmail("hello@world.com");
        saveData.setPassword("123456789");
        saveData.setUsername("qwerrewq");
        saveData.setRole(UserRole.HELLO);
        saveData = helloService.saveHelloModel(saveData);
        
        return saveData;
    }

    // 유저 회원 탈퇴 
    // @DeleteMapping("/hello/{id}/delete")
    // public HelloModel deleteHello(@PathVariable(name = "id") Long id) {
    //     HelloModel deleteData = helloService.deleteHelloModel(id);
    //     return deleteData;
    // }
    @GetMapping("/hello/{id}/delete")
    public ResponseDto<HelloModel> deleteHello(@PathVariable(name = "id") long id, HttpServletRequest requset) throws Exception {
        HttpSession session = requset.getSession();
        HelloModel loginUser = (HelloModel)session.getAttribute("loginUser");
        if(loginUser.getId() != id) {
            throw new Exception("error");
        }
        HelloModel deleteData = helloService.deleteHelloModel(id);

        return new ResponseDto<>(HttpStatus.OK, deleteData);
    }

    // 유저 정보 수정 
    // @PutMapping("/hello/{id}/put")
    // public HelloModel putHello(@PathVariable(name = "id") Long id, @RequestBody(required = false) HelloModel hm) {
    //     HelloModel putData = hm;
    //     putData.setId(id);
    //     putData = helloService.putHelloModel(putData);
    //     return putData;
    // }
    @GetMapping("/hello/{id}/put")
    public ResponseDto<HelloModel> putHello(@PathVariable(name = "id") long id, @RequestBody(required = false) HelloModel hm, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        HelloModel loginUser = (HelloModel)session.getAttribute("loginUser");
        if(loginUser.getId() != id) {
            throw new Exception("error");
        }
        HelloModel putData = new HelloModel();
        putData.setName("world_hello");
        putData.setEmail("world@hello.com");
        putData.setId(id);
        putData = helloService.putHelloModel(putData);
        
        // HelloModel putData = hm;
        // HelloModel oldData = helloService.getHelloModel(id);
        // oldData.setEmail(hm.getEmail());
        // oldData.setName(hm.getName());
        
        return new ResponseDto<>(HttpStatus.OK, putData);
    }

    // 로그인 
    @PostMapping("/login")
    public ResponseDto<String> postLogin(@RequestBody(required = true) HelloModel hm, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("loginUser") == null) {
            return new ResponseDto<>(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, "error");
        }
        HelloModel loginData = helloService.loginHelloModel(hm);
        if(!(HelloModel.Check(hm))) {
            return new ResponseDto<>(HttpStatus.BAD_REQUEST, "error");
        }

        session.setAttribute("loginUser", loginData);
        return new ResponseDto<>(HttpStatus.OK, loginData.getUsername());
    }

    // 로그아웃 
    @PostMapping("/logout")
    public ResponseDto<String> postLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        else {
            return new ResponseDto<>(HttpStatus.BAD_REQUEST, "error");
        }
        return new ResponseDto<>(HttpStatus.OK, "logout");
    }
    
    // 로그인 체크 
    @GetMapping("/login")
    public ResponseDto<String> getLogin(HttpServletRequest request) {
        
        return new ResponseDto<>(HttpStatus.OK, "username");
        // HttpSession session = request.getSession();
        // HelloModel loginUser = (HelloModel)session.getAttribute("loginUser");
        // if(loginUser == null) {
        //     return new ResponseDto<>(HttpStatus.BAD_REQUEST, "error");
        // }
        // return new ResponseDto<>(HttpStatus.OK, loginUser.getUsername());
    }
    
}
