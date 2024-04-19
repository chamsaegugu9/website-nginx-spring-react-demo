package com.example.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdemo.model.HelloModel;
import com.example.springdemo.repository.HelloRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HelloService {
    @Autowired
    private HelloRepository helloRepository;

    // 유저 식별값으로 유저 정보 얻기
    @Transactional
    public HelloModel getHelloModel(long id) {
        HelloModel getData = helloRepository.findById(id).orElseGet(()->{
            return new HelloModel("empty");
        });
        //HelloModel getData = helloRepository.findById(id).get();
        return getData;
    }

    // 모든 유저 정보 얻기 
    @Transactional
    public List<HelloModel> getAllHelloModel() {
        return helloRepository.findAll();
    }

    // 유저 정보 저장(회원가입)
    @Transactional
    public HelloModel saveHelloModel(HelloModel hm) {
        HelloModel saveData = hm;
        helloRepository.save(saveData);
        return saveData;
    }

    // 유저 정보 삭제(회원탈퇴)
    @Transactional
    public HelloModel deleteHelloModel(long id){
        // HelloModel deleteData = helloRepository.findById(id).orElseGet(()->{
        //     return new HelloModel("empty");
        // });
        // if(!HelloModel.Check(deleteData)) {
        //     HelloModel cdeleteData = HelloModel.copy(deleteData);
        //     helloRepository.delete(deleteData);
        //     return cdeleteData;
        // }

        HelloModel deleteData = helloRepository.findById(id).get();
        helloRepository.delete(deleteData);
        return deleteData;
    }

    // 유저 정보 수정
    @Transactional
    public HelloModel putHelloModel(HelloModel hm) {
        // HelloModel putData = helloRepository.findById(hm.getId()).orElseGet(()->{
        //     return new HelloModel("empty");
        // });
        // if(!HelloModel.Check(putData)) {
        //     putData.setName(hm.getName());
        //     putData.setEmail(hm.getEmail());
        // }
        HelloModel putData = helloRepository.findById(hm.getId()).get();
        putData.setName(hm.getName());
        putData.setEmail(hm.getEmail());
        return putData;
    }

    // 로그인 
    @Transactional
    public HelloModel loginHelloModel(HelloModel hm) {
        HelloModel loginData = helloRepository.findByUsernameAndPassword(hm.getUsername(), hm.getPassword()).orElseGet(()->{
            return new HelloModel("empty");
        });
        return loginData;
    }

    // 로그아웃
    @Transactional
    public HelloModel logoutHelloModel(HelloModel hm) {

        return new HelloModel();
    }
}
