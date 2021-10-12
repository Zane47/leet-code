# 数组
## 数组倒序, 注意是boxed类型
```java
// 322
Integer[] coins = new Integer[size];
Arrays.sort(coins, Collections.reverseOrder());
```

## 数组自定义排序
```java
// 先排序, 按照右坐标升序
// 452
Arrays.sort(points, new Comparator<int[]>() {
    @Override
    public int compare(int[] o1, int[] o2) {
        // -2147483645 - 2147483647 Numeric overflow in expression
        // return o1[1] - o2[1];
        if (o1[1] < o2[1]) {
            return -1;
        } else if (o1[1] > o2[1]) {
            return 1;
        } else {
            // o1[1] == o2[1]
            return 0;
        }
    }
});
```

## 数组初始化
```java
memo = new int[coins.length];
Arrays.fill(memo, -2);
```

# char
## char转int
注意不可以使用Integer.valueOf(), 那样子是ascii码
如果要取ascii码, 也不需要Integer.valueOf(), 直接使用即可
正确的方式应该是
```java
Character.getNumericValue(input.charAt(i))
```

