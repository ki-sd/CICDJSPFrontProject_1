package com.sist.vo;

import lombok.Data;

//FNO                                       NOT NULL NUMBER
//NAME                                      NOT NULL VARCHAR2(200)
//TYPE                                               VARCHAR2(100)
//PHONE                                              VARCHAR2(50)
//ADDRESS                                            VARCHAR2(500)
//SCORE                                              NUMBER(3,1)
//THEME                                              VARCHAR2(2000)
//POSTER                                             VARCHAR2(500)
//IMAGES                                             VARCHAR2(4000)
//TIME                                               VARCHAR2(500)
//PARKING                                            VARCHAR2(500)
//CONTENT                                            CLOB
//HIT                                                NUMBER
//PRICE                                              VARCHAR2(100)
//JJIMCOUNT                                          NUMBER
//REPLYCOUNT                                         NUMBER
//LIKECOUNT                                          NUMBER
//RDAYS                                              VARCHAR2(100)
@Data
public class FoodVO {
	private int fno,hit;
	private String name,type,phone,address,theme,poster,images,time,parking,content,price;
	private double score;
}
