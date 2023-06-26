package spring;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

//getter : 데이터값을 가져오는 형태
//setter : this를 이용하여 데이터값을 이관

@Getter
@Setter
public class dao {
 String nidx;
 String nsubject;
 String nwriter;
 String npass;
 String ntext;
 String ndate;
 
 //db에서 받아온 데이터 정보를 배열화 시키는 메소드 (getter만 작동) - 1차열
 public ArrayList<String> datas(){
	 ArrayList<String> lists = new ArrayList<String>();
	 lists.add(getNidx());
	 lists.add(getNsubject());
	 lists.add(getNwriter());
	 lists.add(getNdate());
	 return lists;
 }
  
}
