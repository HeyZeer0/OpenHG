package net.heyzeer0.openhg.utils;

import net.heyzeer0.openhg.Main;

import java.io.File;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class GeneralUtil {

    public static void deleteDir(File dir) {
        if(dir.isDirectory()) {
            String[] arq = dir.list();
            //pode ser que aqui em baixo não seja necessário, mais só pra garantir xd
            for(int i = 0; i < arq.length; i++) {
                deleteDir(new File(dir, arq[i]));
            }
            dir.delete();
        }
    }

    public static void deleteWorld(String world) {
        File f = new File(File.separator + world);
        for(File a : f.listFiles()) {
            if(a.isDirectory()) {
                for(File b : a.listFiles()) {
                    b.delete();
                }
            }else{
                a.delete();
            }
        }
    }

}
