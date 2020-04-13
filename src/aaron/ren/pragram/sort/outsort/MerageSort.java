package aaron.ren.pragram.sort.outsort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.cnblogs.com/chengxiao/p/6194356.html
 * https://blog.csdn.net/daaikuaichuan/article/details/80641757
 * https://blog.csdn.net/weixin_39296576/article/details/90403996
 * https://blog.csdn.net/xushiyu1996818/article/details/84936832
 */
public class MerageSort {
    final static int SIZE = 10000;//这里是定义我们将源文件中以10000条记录作为单位进行分割。

    public static void main(String[] args) throws IOException {
        File file = new File("paixu.txt");
        BufferedReader fr = new BufferedReader(new FileReader(file));//源数据文件读取。

        int[] nums = new int[SIZE];//临时存放分割时的记录
        List<String> fileNames = new ArrayList<String>();//保存所有分割文件的名称
        int index = 0;
        while (true) {
            String num = fr.readLine();//从原文件中读取一条记录
            if (num == null) {//如果读取完毕后，进行一次排序并保存
                fileNames.add(sortAndSave(nums, index));
                break;
            }
            nums[index] = Integer.valueOf(num);
            index++;
            if (index == SIZE) {//当nums里面读的数字到达长度边界时，排序，存储
                fileNames.add(sortAndSave(nums, index));//sortAndSave是将nums中前index条记录先快速排序，然后存入文件，最好将文件名返回。
                index = 0;//重置index
            }
        }
        fr.close();

        mergeSort(fileNames);//将所有fileNames的文件进行合并

    }

    //sortAndSave是将nums中前index条记录先快速排序，然后存入文件，最好将文件名返回
    public static String sortAndSave(int[] nums, int size) throws IOException {
        quicksort.sort(nums, 0, size - 1);
        String fileName = "sort" + System.nanoTime() + ".txt";
        File rf = new File(fileName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(rf));
        for (int num : nums) {
            bw.write(num + "\n");
        }
        bw.close();
        return fileName;
    }

    /**
     * 并归排序
     *
     * @param fileNames
     * @throws IOException
     */
    public static void mergeSort(List<String> fileNames) throws IOException {
        List<String> tempFileNames = new ArrayList<>();
        for (int i = 0; i < fileNames.size(); i++) {
            String resultFileName = "sort" + System.nanoTime() + ".txt";
            File resultFile = new File(resultFileName);
            tempFileNames.add(resultFileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(resultFile));

            File file1 = new File(fileNames.get(i++));
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            if (i < fileNames.size()) {
                File file2 = new File(fileNames.get(i));
                BufferedReader br2 = new BufferedReader(new FileReader(file2));
                int num1 = 0;
                int num2 = 0;
                boolean isFirst = true;
                boolean firstNext = true;
                String numVal1 = null, numVal2 = null;
                for (; ; ) {
                    if (isFirst) {
                        numVal1 = br1.readLine();
                        numVal2 = br2.readLine();
                        num1 = Integer.valueOf(numVal1);
                        num2 = Integer.valueOf(numVal2);
                        isFirst = false;
                    } else if (firstNext) {
                        numVal1 = br1.readLine();
                    } else {
                        numVal2 = br2.readLine();
                    }
                    if (numVal1 != null && numVal2 != null) {
                        if (firstNext) {
                            num1 = Integer.valueOf(numVal1);
                        } else {
                            num2 = Integer.valueOf(numVal2);
                        }
                        if (num1 < num2) {
                            bw.write(num1 + "\n");
                            firstNext = true;
                        } else {
                            bw.write(num2 + "\n");
                            firstNext = false;
                        }
                    } else {
                        if (numVal1 != null) {
                            bw.write(numVal1 + "\n");
                        }
                        if (numVal2 != null) {
                            bw.write(numVal2 + "\n");
                        }
                        break;
                    }
                }
                while (true) {
                    numVal2 = br2.readLine();
                    if (numVal2 != null) {
                        bw.write(numVal2 + "\n");
                    } else {
                        break;
                    }
                }
                br2.close();
                file2.delete();
            }
            while (true) {
                String numVal1 = br1.readLine();
                if (numVal1 != null) {
                    bw.write(numVal1 + "\n");
                } else {
                    break;
                }
            }
            br1.close();
            file1.delete();
            bw.close();
        }
        int size = tempFileNames.size();
        if (size > 1) {
            mergeSort(tempFileNames);
        } else if (size == 1) {
            File file = new File(tempFileNames.get(0));
            file.renameTo(new File("result.txt"));
        }
    }


    /**
     * 快速排序
     */
    static class quicksort {


        public static void sort(int data[], int low, int hight) {
            quicksort qs = new quicksort();
            qs.data = data;
            qs.sort(low, hight);
        }

        public int data[];


        private int partition(int sortArray[], int low, int hight) {
            int key = sortArray[low];
            while (low < hight) {
                while (low < hight && sortArray[hight] >= key) {
                    hight--;
                }
                sortArray[low] = sortArray[hight];
                while (low < hight && sortArray[low] <= key) {
                    low++;
                }
                sortArray[hight] = sortArray[low];
            }
            sortArray[low] = key;
            return low;
        }


        public void sort(int low, int hight) {
            if (low < hight) {
                int result = partition(data, low, hight);
                sort(low, result - 1);
                sort(result + 1, hight);
            }
        }


        public void display() {
            for (int i = 0; i < data.length; i++) {
                System.out.print(data[i]);
                System.out.print(" ");
            }
        }
    }

}

