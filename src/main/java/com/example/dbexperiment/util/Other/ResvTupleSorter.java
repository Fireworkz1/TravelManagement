package com.example.dbexperiment.util.Other;

import com.example.dbexperiment.Entity.ResvTuple;

import java.util.List;

public class ResvTupleSorter {
    public static void bubbleSort(List<ResvTuple> resvTupleList) {
        int n = resvTupleList.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (resvTupleList.get(j).getDate().compareTo(resvTupleList.get(j + 1).getDate()) > 0) {
                    // 交换元素位置
                    ResvTuple temp = resvTupleList.get(j);
                    resvTupleList.set(j, resvTupleList.get(j + 1));
                    resvTupleList.set(j + 1, temp);
                    swapped = true;
                }
            }

            // 如果没有发生交换，表示列表已排序，可以提前结束循环
            if (!swapped) {
                break;
            }
        }
    }
}
