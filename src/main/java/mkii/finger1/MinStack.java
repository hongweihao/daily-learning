package mkii.finger1;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * <p>
 *     这里复杂度为O(n)，可以采取辅助栈，即空间换时间
 * 测试：
 */
public class MinStack {
    // min在第一， top在第二
    private Stack<Integer> stack = new Stack<>();

    public void push(int node) {
        // empty
        int min;
        if (stack.empty()) {
            stack.push(node);
            min = node;
        } else {
            min = stack.pop();
            stack.push(node);
            if (node < min) {
                min = node;
            }
        }
        stack.push(min);
    }

    // 时间复杂度为O(n)
    public void pop() {
        if (stack.empty()) {
            return;
        }
        int min = stack.pop();
        int peek = stack.pop();
        // 最小的被弹出了
        if (min == peek){
            Stack<Integer> newStack = new Stack<>();
            min = Integer.MAX_VALUE;
            int temp;
            while (!stack.empty()){
                temp = stack.pop();
                newStack.push(temp);
                if (min > temp){
                    min = temp;
                }
            }
            while (!newStack.empty()){
                stack.push(newStack.pop());
            }
        }
        stack.push(min);
    }

    public int top() {
        if (stack.empty()) {
            System.out.println("Exception");
            try {
                throw new Exception("stack is empty!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("stack is empty!");
                return -1;
            }
        }
        stack.peek();
        int min = stack.pop();
        if (stack.empty()) {
            stack.push(min);
            return min;
        } else {
            int top = stack.peek();
            stack.push(min);
            return top;
        }
    }

    public int min() {
        if (stack.empty()) {
            System.out.println("Exception");
            try {
                throw new Exception("stack is empty!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("stack is empty!");
                return -1;
            }
        }
        return stack.peek();
    }
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(4);
        System.out.println(minStack.min());
        minStack.push(3);
        System.out.println(minStack.min());
        minStack.push(2);
        System.out.println(minStack.min());
        minStack.push(4);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
        minStack.push(0);
        System.out.println(minStack.min());
    }
}
