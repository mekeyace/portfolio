package web;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class noticevo {
	int nidx;
	String nsubject,nwriter,npass,ntext;
	Date ndate;	
	String cks;
	String mfile;	//DB Field명
	
	MultipartFile mfile2;	//post로 넘어온 값을 저장하는 용도
}
