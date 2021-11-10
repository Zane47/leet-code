package leetcode.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class RemoveArrayList {
    public static void main(String[] args) {

        List<Integer> list1 = initList();
        // do not use
        // removeSub1(list1);

        List<Integer> list2 = initList();
        // 使用iterator
        removeSub2(list2);

        // 倒过来循环
        // removeSub3();

        // lambda
        removeSub4();
    }


    private static void removeSub4() {
        List<Integer> list = initList();
        list.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer == 40;
            }
        });
        list.removeIf(next -> next == 30);
        assert list.size() == 3;
    }

    private static void removeSub3() {
        List<Integer> list = initList();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) == 30) {
                list.remove(i);
            }
        }
    }

    private static void removeSub2(List<Integer> list) {
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
            Integer next = iterator.next();
            if (next == 30) {
                iterator.remove();
            }
        }
    }


    private static void removeSub1(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 40) {
                list.remove(i);
                i--;
            }
        }
    }

    private static List<Integer> initList() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        return list;
    }

}
