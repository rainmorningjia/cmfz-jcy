package com.baizhi.cmfz.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

/**
 * @author Miles
 * @Title: AudioUtil
 * @ProjectName cmfz-jcy
 * @Date 2018/12/24--10:57
 */
public class AudioUtil {
    public static String queryDuration(String path){
        File source = new File("E:\\IDEA\\workespace\\projectlater\\cmfz-jcy\\src\\main\\webapp\\audio\\"+path);
        Encoder encoder = new Encoder();

        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration();
            long second = ls / 1000;
            int se = (int) second % 60;
            int minute = (int) second / 60;
            String ses=null;
            String minutes=null;
            if (se<10){
                 ses="0"+se;
            }else {
                ses=Integer.toString(se);
            }
            if ((minute<10)){
                minutes="0"+minute;
            }else {
                minutes=Integer.toString(minute);
            }
            String duration =  minutes + ":" + ses;
            return duration;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
