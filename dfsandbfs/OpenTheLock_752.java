package leetcode.dfsandbfs;

import java.util.*;

/**
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 *
 *
 *
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 *
 *
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"],
 * target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/open-the-lock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */

public class OpenTheLock_752 {
    public static void main(String[] args) {
        // 从 0 0 0 0 开始

        String[] deadends = new String[]{"0201","0101","0102","1212","2002"};
        String target = "0202";

        System.out.println(new Solution().openLock(deadends, target));


    }

    /**也是一个找最短路径的题目，想到用bfs
     * 这里的bfs有限制
     * 1.可能走回头路，例如：0000 -> 1000, 然后队列取到1000 又-> 0000，死循环
     * -> 使用isVisited来标记是否访问
     *
     * 2.到target就return步数
     *
     * 3.到deadend就continue，如果全部遍历完了都没有的话，那么return-1
     *
     * // 其实这里可以直接把deadend初始化到visited中
     */
    static class Solution {
        public int openLock(String[] deadends, String target) {
            int step = 0;
            // 避免重复访问
            HashSet<String> isVisited = new HashSet<>();

            // deadends初始化成list，为了使用contains
            ArrayList<String> deadEndList = new ArrayList<>(Arrays.asList(deadends));

            // bfs队列
            Queue<String> queue = new LinkedList<>();
            queue.offer("0000");
            isVisited.add("0000");
            while (!queue.isEmpty()) {
                int size = queue.size();
                // 将当前队列中的所有节点向周围扩散
                for (int i = 0; i < size; i++) {
                    // 出队
                    String poll = queue.poll();
                    // 死胡同就下一个
                    if (deadEndList.contains(poll)) {
                        // 下一个
                        continue;
                    }
                    if (target.equals(poll)) {
                        return step;
                    }
                    // 遍历他相邻的所有可能,开始拨动锁
                    // 四位的锁"0000"，每个位置都可以向上或者向下拨动
                    for (int j = 0; j < 4; j++) {
                        String up = wrapUp(poll, j);
                        if (!isVisited.contains(up)) {
                            isVisited.add(up);
                            queue.offer(up);
                        }
                        String down = wrapDown(poll, j);
                        if (!isVisited.contains(down)) {
                            isVisited.add(down);
                            queue.offer(down);
                        }
                    }

                }
                // 走完一次队列，那就加一步
                step++;
            }


            // 全部都访问完了，都没有找到路径
            return -1;
        }

        /**
         * 向下拨动锁 0 9 8 7 6 5 4 3 2 1 0 9
         *
         * @param poll
         * @param j 四位密码中的第几位
         * @return
         */
        private String wrapDown(String poll, int j) {
            char[] chars = poll.toCharArray();

            if (chars[j] == '0') {
                chars[j] = '9';
            }
            else {
                chars[j] -= 1;
            }

            return new String(chars);

        }

        /**
         * 向上拨动锁 0 1 2 3 4 5 6 7 8 9 0
         *
         * @param poll
         * @param j 四位密码中的第几位
         * @return
         */
        private String wrapUp(String poll, int j) {
            char[] chars = poll.toCharArray();

            if (chars[j] == '9') {
                chars[j] = '0';
            }
            else {
                chars[j] += 1;
            }

            // 这样子返回的是[0,0,0,0]
            // return Arrays.toString(chars);
            return new String(chars);
        }
    }


}
