package com.gpdi.hqplus;

import lombok.Data;

import java.util.*;

/**
 * @author: lianghb
 * @create: 2019-06-20 22:02
 **/
public class TreeDemo {

    public static void main(String[] args) {
        List<Dept> list = new ArrayList<>(21);

        Dept root = new Dept();
        root.setId("0");
        root.setName("根元素");
        list.add(root);

        for (int i = 1; i <= 10; i++) {
            Dept dept = new Dept();
            dept.setId(i + "");
            dept.setPId("0");
            dept.setName("dept" + i);
            list.add(dept);
        }

        for (int i = 11; i <= 20; i++) {
            Dept dept = new Dept();
            dept.setId(i + "");
            dept.setPId("5");
            dept.setName("dept" + i);
            list.add(dept);
        }

        Map<String, Dept> sort = sort(list);

        Dept dept = sort.get(root.getId());
        print(dept);

    }

    private static Map<String, Dept> sort(List<Dept> list) {
        Map<String, Dept> map = new HashMap<>();

        for (Dept dept : list) {
            map.put(dept.getId(), dept);
        }

        for (Dept dept : list) {
            Dept parent = map.get(dept.getPId());
            if (parent != null) {
                parent.getSub().add(dept);
            }
        }

        return map;
    }

    @Data
    private static class Dept {
        private String id;
        private String pId;
        private String name;
        private List<Dept> sub = new ArrayList<>();

        @Override
        public String toString() {
            return "Dept{" +
                    "id='" + id + '\'' +
                    ", pId='" + pId + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private static void print(Dept dept) {
        if (dept == null) {
            return;
        }
        System.out.println(dept.toString());

        for (Dept sub : dept.getSub()) {
            print(sub);
        }
    }


    private void aa() {
        Map<String, Integer> map = new HashMap<>();

        Integer max = null;

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            Integer integer = map.get(key);
            if (max == null || integer > max) {
                max = integer;
            }
        }

        String[] sort = new String[max];

        for (String key : keySet) {
            Integer integer = map.get(key);
            sort[max - integer] = key;
        }

        String[] result = new String[1000];
        int index = 0;
        for (String sortKey : sort) {
            if (sortKey != null) {
                result[index++] = sortKey;
            }
        }
    }
}
