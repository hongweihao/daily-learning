package mkii.finger1;

import java.util.Stack;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 * <p>
 * 测试：
 * 1.pushA != popA
 * 2.pushA | popA == null []
 * 3.正确序列
 * 4.错误序列
 */
public class StackOperation {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (popA == null || popA.length <= 0 || pushA == null || pushA.length <= 0 || popA.length != pushA.length) {
            return false;
        }
        // 数据栈
        Stack<Integer> stack = new Stack<>();
        int p = 0;  // 指向pushA
        int i;  // 指向popA弹出序列
        for (i = 0; i < popA.length; i++) {
            int pop = popA[i];

            // 栈是空的/栈顶没找到元素，从pushA序列中添加元素，
            while (stack.empty() || pop != stack.peek()){
                if (p >= pushA.length){
                    // 栈顶找不到元素，pushA序列也没有元素可以添加
                    return false;
                }
                stack.push(pushA[p]);
                p++;
            }
            // 在栈顶找到元素，弹出。继续下一个元素
            if (pop == stack.peek()){
                stack.pop();
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        int[] pushA = {1,2,3,4,5};
        int[] popA = {4,5,3,2,1};
        int[] popB = {4,3,5,1,2};

        StackOperation stackOperation = new StackOperation();
        System.out.println(stackOperation.IsPopOrder(pushA, popB));
    }
}
