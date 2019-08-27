package com.sxjs.jd.composition.main.classificationfragment;

import com.sxjs.jd.entities.ClassIndex;
import com.sxjs.jd.entities.ClassListBean;

import java.util.List;

/**
 * Created by admin on 2017/3/12.
 */

public interface ClassificationContract {
 interface View {
    void setTypeOfNameData(List<String> list);

     void setHomeIndexData(ClassListBean classTitle);

     void setTypeIconsData(String text);
     void setTyptListIconData(ClassIndex text);

}

 interface Presenter {
    void getTypeOfNameData();


    void  getTitleListData();


     void getTypeIconsData();
}

}