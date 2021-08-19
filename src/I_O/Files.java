package I_O;

import java.io.File;

public class Files
{
    //初始化
    static long max = 0;
    static long min = Long.MAX_VALUE;
    static File max1;
    static File min1;
    public static void method1(File f)
    {
        File[] fs = f.listFiles();
        if (fs==null){}
        else {
            for (int i = 0; i < fs.length; i++) {
                //如果是文件夹，进行递归
                if (fs[i].isDirectory()) {
                    method1(fs[i]);
                }
                //如果是文件且不为空，那么进行比较
                else if (fs[i].length() > 0) {
                    System.out.println("正在比较"+fs[i]);
                    if (fs[i].length() > max) {
                        max = fs[i].length();
                        max1 = fs[i];
                    } else if (fs[i].length() < min) {
                        min = fs[i].length();
                        min1 = fs[i];
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {
        //文件路径
        String path = "/Users/apple/Downloads";
        File f = new File(path);
        //执行
        method1(f);
        System.out.printf("最大的文件为:" + max1 + "，它的大小为:" + max + "%n" + "最小的文件为：" + min1 +"，它的大小为：" + min);
    }
}
