package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
      public static List<Integer> sortReview(List<Integer> list){
        Collections.sort(list,Collections.reverseOrder());
        return list;
      }
     public static HashMap<Integer,String> linkDetails(List<Integer> list,List<String> li){
      HashMap<Integer,String> map=new HashMap<>();
      for(int i=0;i<list.size();i++){
         map.put(list.get(i),li.get(i));
      }
      return map;
      
   }

}
