package com.example.springdemo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springdemo.dto.ResponseDto;
import com.example.springdemo.model.HelloBeer;
import com.example.springdemo.model.HelloModel;
import com.example.springdemo.model.UserRole;
import com.example.springdemo.service.HelloBeerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




// beer 컨트롤러
@RestController
@RequestMapping("QQQ")
public class HelloBeerController {
    @Autowired
    private HelloBeerService helloBeerService;
    
    // beer 데이터 
    @GetMapping("/beer/{id}")
    public ResponseDto<HelloBeer> getMethodName(@PathVariable(name = "id") long id) {
        HelloBeer getData = helloBeerService.getHelloBeer(id);
        if(HelloBeer.check(getData)) {
            return new ResponseDto<>(HttpStatus.BAD_REQUEST, getData); 
        }
        return new ResponseDto<>(HttpStatus.OK, getData);
    }
    

    // beer 정보 목록
    // 페이지
    @GetMapping({"/beer", "/beer/"})
    public ResponseDto<Page<HelloBeer>> getMethodName(
    @RequestParam(defaultValue = "0", required = false, name = "page") int page, 
    @RequestParam(defaultValue = "10", required = false, name = "size") int size, 
    @RequestParam(defaultValue = "",required = false, name = "s") String s) {
        int pageindex = page;
        int pagesize = size;
        if(pagesize <= 10) {
            pagesize = 10;
        }
        else if(pagesize <= 20) {
            pagesize = 20;
        }
        else {
            pagesize = 30;
        }
        return new ResponseDto<>(HttpStatus.OK, helloBeerService.getAllHelloBeer(pageindex, pagesize, s));
    }

    // beer 정보 저장
    @PostMapping("/beer/save")
    public ResponseDto<HelloBeer> saveHelloBeer(@RequestBody HelloBeer hb, @RequestParam("file") MultipartFile file) {
        HelloBeer saveData = hb;
        helloBeerService.saveHelloBeer(hb, file);
        return new ResponseDto<>(HttpStatus.OK, saveData);
    }
    
    // beer 정보 삭제
    @DeleteMapping("/beer/{id}/delete")
    public ResponseDto<HelloBeer> deleteHelloBeer(@PathVariable(name = "id") long id, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        HelloModel loginUser = (HelloModel)session.getAttribute("loginUser");
        if(loginUser.getRole() != UserRole.HELLOWORLD) {
            throw new Exception("error");
        }
        HelloBeer deleteData = helloBeerService.deleteHelloBeer(id);
        return new ResponseDto<>(HttpStatus.OK, deleteData);
    }

    // beer 정보 수정
    @PutMapping("/beer/{id}/put")
    public ResponseDto<HelloBeer> putHelloBeer(@PathVariable(name = "id") long id, @RequestBody HelloBeer hb, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {


        HttpSession session = request.getSession();
        HelloModel loginUser = (HelloModel)session.getAttribute("loginUser");
        if(loginUser.getRole() != UserRole.HELLOWORLD) {
            throw new Exception("error");
        }
        //HelloBeer putData = helloBeerService.getHelloBeer(id);
        HelloBeer putData = helloBeerService.putHelloBeer(hb, file);
        return new ResponseDto<>(HttpStatus.OK, putData);
    }

    // beer 이미지 얻기 
    // @GetMapping("/beer/img/{filename:.+}")
    // public ResponseEntity<Resource> getMethodName(@PathVariable(name = "filename") String filename) throws Exception {
    //     Resource fileResource;
    //     try {
    //         fileResource = helloBeerService.getHelloBeerFile(filename);

    //     }
    //     catch (Exception e) {
    //         throw e;
    //     }
        
    //     return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
    //     "attachment; filename=\"" + fileResource.getFilename() + "\"").body(fileResource);
    // }
    @GetMapping("/beer/img/{filename:.+}")
    public ResponseEntity<Resource> getMethodName(@PathVariable(name = "filename") String filename) throws Exception {
        Resource fileResource;
        try {
            fileResource = helloBeerService.getHelloBeerFile(filename);

        }
        catch (Exception e) {
            throw e;
        }
        
        return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(fileResource.getFile().toPath()))
        .body(fileResource);
    }
}
