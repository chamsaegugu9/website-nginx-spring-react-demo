package com.example.springdemo.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;

import com.example.springdemo.model.HelloBeer;
import com.example.springdemo.property.HelloBeerProperty;
import com.example.springdemo.repository.HelloBeerRepository;

@Service
public class HelloBeerService {
    
    private HelloBeerRepository helloBeerRepository;

    private final Path rootLocation;
    
    @Autowired
    public HelloBeerService(HelloBeerRepository helloBeerRepository, HelloBeerProperty property) {
        this.helloBeerRepository = helloBeerRepository;
        if(property.getLocation().trim().length() == 0) {
            this.rootLocation = Paths.get("upload-dir");
        }
        else {
            this.rootLocation = Paths.get(property.getLocation());
        }
    }

    // beer 데이터 얻기
    @Transactional
    public HelloBeer getHelloBeer(Long id) {
        HelloBeer getData = helloBeerRepository.findById(id).orElseGet(()->{
            return new HelloBeer("empty");
        });
        
        return getData;
    }

    // beer 이미지 파일 경로 얻기
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

    // beer 이미지 파일 얻기
    public Resource getHelloBeerFile(String filename) throws Exception {
        Path filePath = load(filename);
        Resource filerResource;
        try {
            filerResource = new UrlResource(filePath.toUri());
        }
        catch(Exception e) {
            throw e;
        }
        
        return filerResource;
    }

    // beer 전체 데이터 얻기
    // 페이지
    @Transactional
    public Page<HelloBeer> getAllHelloBeer(int page, int size, String s) {
        int pagesize = size;
        if (s.equals("ALL")) {
            pagesize =  (int)helloBeerRepository.count();
        }
        Pageable pageable = PageRequest.of(page, pagesize);
        return helloBeerRepository.findAll(pageable);
    }

    // beer 데이터 저장
    @Transactional
    public HelloBeer saveHelloBeer(HelloBeer hb, MultipartFile file) {
        HelloBeer saveData = hb;

        // beer 이미지 저장
        try {
            if(file.isEmpty()) {
                // hb.setFilepath("empty");
                throw new Exception("file error");
            }
            String filename = file.getOriginalFilename();
            String fileExtension = filename.substring(filename.lastIndexOf("."));
            String UUIDname = UUID.randomUUID().toString() + fileExtension;
            Path destinationFile = this.rootLocation.resolve(
                Paths.get(UUIDname))
                .normalize().toAbsolutePath();
            if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                    throw new Exception("file error");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                StandardCopyOption.REPLACE_EXISTING);
            }
            // 이미지 파일의 이름 저장
            saveData.setFilepath(UUIDname);
        }
        catch (Exception e) {
            hb.setFilepath("empty");
        }
        helloBeerRepository.save(saveData);
        return saveData;
    }

    // beer 데이터 수정 
    @Transactional
    public HelloBeer putHelloBeer(HelloBeer hb, MultipartFile file) throws Exception {
        HelloBeer putData = helloBeerRepository.findById(hb.getId()).get();
        
        //beer 의 이미지 파일 수정
        try {
            if(hb.getFilepath().equals("empty")) {
                Path filePath = this.rootLocation.resolve(
                    Paths.get(putData.getFilepath()))
                    .normalize().toAbsolutePath();

                Files.delete(filePath);
                hb.setFilepath("empty");
            }
            else if(!file.isEmpty()) {
                Path filePath = this.rootLocation.resolve(
                    Paths.get(putData.getFilepath()))
                    .normalize().toAbsolutePath();
                Files.delete(filePath);
                String filename = file.getOriginalFilename();
                String fileExtension = filename.substring(filename.lastIndexOf("."));
                String UUIDname = UUID.randomUUID().toString() + fileExtension;
                Path destinationFile = this.rootLocation.resolve(
                    Paths.get(UUIDname))
                    .normalize().toAbsolutePath();
                if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                    throw new Exception("file error");
                }
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
                }
                // 이미지 파일의 절대경로 저장
                hb.setFilepath(UUIDname);
            }
        }
        catch (Exception e) {
            throw e;
        }
        putData.setAlchol(hb.getAlchol());
        putData.setCate(hb.getCate());
        putData.setComment(hb.getComment());
        putData.setName(hb.getName());
        putData.setPrice(hb.getPrice());
        putData.setVolume(hb.getVolume());
        putData.setFilepath(hb.getFilepath());
        // putData.setOrderquantity(hb.getOrderquantity());
        // putData.setQuantity(hb.getQuantity());
        return putData;
    }

    @Transactional
    public HelloBeer deleteHelloBeer(long id) {
        HelloBeer deleteHelloBeer = helloBeerRepository.findById(id).get();
        helloBeerRepository.delete(deleteHelloBeer);
        return deleteHelloBeer;
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (Exception e) {

        }
    }
    public void init1() {
        // HelloBeer saveData1 = new HelloBeer("empty");
        // HelloBeer saveData2 = new HelloBeer("empty");
        // HelloBeer saveData3 = new HelloBeer("empty");
        // HelloBeer saveData4 = new HelloBeer("empty");


        // saveData1.setFilepath("beer_img1");
        // saveData2.setFilepath("beer_img2");
        // saveData3.setFilepath("beer_img3");
        // saveData4.setFilepath("beer_img4");

        for(int i=0;i<10;i++) {
            HelloBeer saveData1 = new HelloBeer("empty");
            HelloBeer saveData2 = new HelloBeer("empty");
            HelloBeer saveData3 = new HelloBeer("empty");
            HelloBeer saveData4 = new HelloBeer("empty");

            saveData1.setFilepath("beer_img1.png");
            saveData2.setFilepath("beer_img2.png");
            saveData3.setFilepath("beer_img3.png");
            saveData4.setFilepath("beer_img4.png");

            helloBeerRepository.save(saveData1);
            helloBeerRepository.save(saveData2);
            helloBeerRepository.save(saveData3);
            helloBeerRepository.save(saveData4);
        }

        // helloBeerRepository.save(saveData1);
        // helloBeerRepository.save(saveData2);
        // helloBeerRepository.save(saveData3);
        // helloBeerRepository.save(saveData4);
    }
}
