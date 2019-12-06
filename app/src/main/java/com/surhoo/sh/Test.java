package com.surhoo.sh;

import java.util.Stack;

public class Test {


    public static void main(String[] args) {


        Permutation(new char[]{'你','好','吗'},1);

    }

    public static void Permutation(char chs[],int start )
      {
                 if(start==chs.length-1)
                     {
                         System.out.println(chs);
                         //如果已经到了数组的最后一个元素，前面的元素已经排好，输出。Arrays.toString(chs);
                     }
                 for(int i=start;i<=chs.length-1;i++)
                     {
                     //把第一个元素分别与后面的元素进行交换，递归的调用其子数组进行排序
                             Swap(chs,i,start);
                             Permutation(chs,start+1);
                             Swap(chs,i,start);
                     //子数组排序返回后要将第一个元素交换回来。
                     //如果不交换回来会出错，比如说第一次1、2交换，第一个位置为2，子数组排序返回后如果不将1、2
                     //交换回来第二次交换的时候就会将2、3交换，因此必须将1、2交换使1还是在第一个位置
                     }
             }


              public static void Swap(char chs[],int i,int j)
     {
                 char temp;
                 temp=chs[i];
                 chs[i]=chs[j];
                 chs[j]=temp;
             }

}
