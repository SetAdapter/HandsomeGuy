package com.example.handsomelibrary.utils;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import com.example.handsomelibrary.application.BaseApp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 文件工具类
 * <p/>
 * Created by fangs on 2017/3/22.
 */
public class FileUtils {

    //采用自己的格式去设置文件，防止文件被系统文件查询到
    public static final String SUFFIX_WY = ".fc";
    public static final String SUFFIX_TXT = ".txt";
    public static final String SUFFIX_EPUB = ".epub";
    public static final String SUFFIX_PDF = ".pdf";

    /** 应用 所有 文件 根目录 */
    public static String SAVE_FOLDER = "HJY";

    private FileUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 到得文件的放置路径
     * <p/> 如果文件目录不存在 会创建
     * @param aModuleName 模块名字 (如："head.img.temp")
     * @return
     * @author fangs
     */
    public static String getPath(String aModuleName) {
        String sp = File.separator;
        String modulePath = aModuleName.replace(".", sp);
        String fDirStr = sp + modulePath + sp;

        File dirpath;

        if (isSDCardEnable()) dirpath = new File(getSDCardPath() + SAVE_FOLDER, fDirStr);
        else dirpath = Environment.getDataDirectory();

        if (!dirpath.exists() && !dirpath.isDirectory()) {
            dirpath.mkdirs();
        }
        return dirpath.getPath();
    }

    /**
     * 判断指定路径的 文件夹 是否存在，不存在创建文件夹
     *
     * @param filePath
     * @return
     */
    public static File folderIsExists(String filePath) {
        File folder = new File(filePath);
        try {
            if (!folder.exists() || !folder.isDirectory()) {
                folder.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return folder;
    }

    /**
     * 判断指定路径的 文件 是否存在，不存在创建文件
     * @param filePath
     * @return
     */
    public static File fileIsExists(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * 递归删除文件和文件夹
     * @param file    要删除的根目录
     */
    public static void recursionDeleteFile(File file){

        if (!file.exists()) return;

        if(file.isFile()){
            deleteFileSafely(file);//删除当前 文件
            return;
        }

        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(null == childFile || childFile.length == 0){
                deleteFileSafely(file);//删除 当前目录
                return;
            }

            for(File f : childFile){
                recursionDeleteFile(f);
            }

            deleteFileSafely(file);//删除 当前目录
        }
    }


    /**
     * 安全删除文件
     * @param file
     * @return
     */
    public static boolean deleteFileSafely(File file) {
        if (file != null) {
            String tmpPath = file.getParent() + File.separator + System.currentTimeMillis();
            File tmp = new File(tmpPath);
            file.renameTo(tmp);
            return tmp.delete();
        }
        return false;
    }

    /***
     * 获取文件类型
     *
     * @param paramString
     * @return
     */
    public static String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            L.d("file", "paramString---->null");
            return str;
        }
        L.d("file","paramString:"+paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            L.d("file","i <= -1");
            return str;
        }


        str = paramString.substring(i + 1);
        L.d("file","paramString.substring(i + 1)------>"+str);
        return str;
    }

    /**
     * 向指定文件写内容  (追加形式写文件)
     * @param path          文件目录(如：fy.com.base)
     * @param inputfileName 文件名（如：log.txt）
     * @param content       准备写入的内容
     */
    public static void fileToInputContent(String path, String inputfileName, String content) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n").append(content).append("\n");

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            File dir = new File(getPath(path));
            if (!dir.exists()) {
                dir.mkdir();
            }

            try {
                // 文件目录 + 文件名 String
                String fileName = dir.toString() + File.separator + inputfileName;
                // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
                FileWriter writer = new FileWriter(fileName, true);
                writer.write(sb.toString());
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取应用 图片保存目录
     * @return
     */
    public static String getImgPath(){
        String takeImageFilePath;

        if (FileUtils.isSDCardEnable())
            takeImageFilePath = FileUtils.getSDCardPath() + "pictures/";
        else takeImageFilePath = Environment.getDataDirectory().getPath() + "/pictures/";

        return takeImageFilePath;
    }

    /**
     * 根据系统时间、前缀、后缀产生一个文件
     * @param folder    目标文件所在的 文件夹（目录）
     * @param prefix    目标文件的 前缀 (如：IMG_)
     * @param suffix    目标文件的 后缀名（如：.jpg）
     * @return
     */
    public static String createFile(String folder, String prefix, String suffix) {

        folderIsExists(folder);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        String filename = prefix + dateFormat.format(new Date(System.currentTimeMillis())) + suffix;

        return new File(folder, filename).getPath();
    }

    //获取Cache文件夹
    public static String getCachePath(){
        if (isSDCardEnable()){
            return BaseApp.getAppContext()
                    .getExternalCacheDir()
                    .getAbsolutePath();
        }
        else{
            return BaseApp.getAppContext()
                    .getCacheDir()
                    .getAbsolutePath();
        }
    }

    /**
     * 本来是获取File的内容的。但是为了解决文本缩进、换行的问题
     * 这个方法就是专门用来获取书籍的...
     *
     * 应该放在BookRepository中。。。
     * @param file
     * @return
     */
    public static String getFileContent(File file){
        Reader reader = null;
        String str = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            while ((str = br.readLine()) != null){
                //过滤空语句
                if (!str.equals("")){
                    //由于sb会自动过滤\n,所以需要加上去
                    sb.append("    "+str+"\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(reader);
        }
        return sb.toString();
    }

    //获取文件夹
    public static File getFolder(String filePath){
        File file = new File(filePath);
        //如果文件夹不存在，就创建它
        if (!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    //获取文件
    public static synchronized File getFile(String filePath){
        File file = new File(filePath);
        try {
            if (!file.exists()){
                //创建父类文件夹
                getFolder(file.getParent());
                //创建文件
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static long getDirSize(File file){
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                long size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {
                return file.length();
            }
        } else {
            return 0;
        }
    }

    //递归删除文件夹下的数据
    public static synchronized void deleteFile(String filePath){
        File file = new File(filePath);
        if (!file.exists()) return;

        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File subFile : files){
                String path = subFile.getPath();
                deleteFile(path);
            }
        }
        //删除文件
        file.delete();
    }
    //获取文件的编码格式
    public static Charset getCharset(String fileName) {
        BufferedInputStream bis = null;
        Charset charset = Charset.GBK;
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            bis = new BufferedInputStream(new FileInputStream(fileName));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1)
                return charset;
            if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = Charset.UTF8;
                checked = true;
            }
            /*
             * 不支持 UTF16LE 和 UTF16BE
            else if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = Charset.UTF16LE;
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = Charset.UTF16BE;
                checked = true;
            } else */

            bis.mark(0);
            if (!checked) {
                while ((read = bis.read()) != -1) {
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80 - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = Charset.UTF8;
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(bis);
        }
        return charset;
    }
}
