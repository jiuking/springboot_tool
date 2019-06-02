package com.hjc.demo.springboot.init;

public class MaoPaoSort {
    public static void main(String[] args) {
        int[] a = new int[]{10, 8, 3, 2, 7, 6};
        for (int i = 0; i < a.length-1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j+1]) {
                    int swap = a[j+1];
                    a[j+1] = a[j];
                    a[j] = swap;
                }
            }
        }
        for (int i : a) {
            System.out.println(i);
        }
        int[] test = new int[]{10, 8, 3, 2, 7, 6};
        for (int j = 0; j < test.length - 1; j++)
        {
            for (int i = 0; i < test.length - j - 1; i++)
            {
                if (test[i] < test[i + 1])
                {
                    int temp = test[i + 1];
                    test[i + 1] = test[i];
                    test[i] = temp;
                }
            }
        }
        for (int i : test) {
            System.out.print(i);
        }

        int[] maoPao = new int[]{10, 8, 3, 2, 7, 6};
        for (int i = 0; i < maoPao.length - 1; i++) {
            for (int j = 0; j < maoPao.length - i - 1; j++) {
                if (maoPao[j] > maoPao[j + 1]) {
                    int a1 = maoPao[j];
                    maoPao[j] = maoPao[j + 1];
                    maoPao[j+1] = a1;
                }
            }
        }
        for (int i : maoPao) {
            System.out.println(i);
        }
    }

}
