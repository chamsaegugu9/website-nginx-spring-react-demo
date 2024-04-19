package com.example.springdemo.model;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class HelloBeer {

    public HelloBeer(String s) {
        if(s.equals("empty")) {
            this.id = -1L;
            this.name = "empty";
            this.comment = "empty";
            this.cate = "empty";
            this.price = -1;
            this.volume = -1;
            this.alchol = -1;
            this.orderquantity = -1;
            this.quantity = -1;
            this.filepath = "empty";
        }
    }

    public static boolean check(HelloBeer hb) {
        if(hb.getId() == -1L && 
        hb.getAlchol() == -1 && 
        hb.getCate().equals("empty") && 
        hb.getComment().equals("empty") && 
        hb.getName().equals("empty") && 
        hb.getPrice() == -1 && 
        hb.getVolume() == -1 && 
        hb.getOrderquantity() == -1 && 
        hb.getQuantity() == -1 && 
        hb.getFilepath().equals("empty")
        ) {
            return true;
        }
        return false;
    }

    // 식별값 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 술의 이름
    @Column(nullable = false, length = 100)
    private String name;

    // 알콜 도수
    @Column(nullable = false)
    private float alchol;

    // 단위는 : ml 
    // 용량 
    @Column(nullable = false)
    private float volume;

    // 가격 
    // 한국돈
    @Column(nullable = false)
    private float price;

    // 술에 대한 간단한 설명
    @Column(nullable = true, length = 300)
    private String comment;

    // 술 종류 
    @Column(nullable = false)
    private String cate;

    // 현재 수량 
    @Column(nullable = false)
    private int quantity;

    // 주문량
    @Column(nullable = false)
    private int orderquantity;

    // 술의 이미지 위치
    @Column(nullable = false, length = 100)
    private String filepath;

    // @Transient
    // private Resource fileResource;
}
