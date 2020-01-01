package mkii.finger1;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * 测试：
 * 1. 空栈push
 * 2. 空栈pop
 * 3. 正常栈push
 * 4. 正常栈pop
 */
public class QueueBy2Stack {
    // 负责push
    Stack<Integer> stack1 = new Stack<Integer>();

    // 负责pop
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        migrate(stack2, stack1);
        if (stack1 == null){
            stack1 = new Stack<>();
        }
        stack1.push(node);
    }

    public int pop() {
        migrate(stack1, stack2);

        if (stack2 == null || stack2.empty()){
            System.out.println("queue is null");
            return -1;
        }

        return stack2.pop();
    }

    private void migrate(Stack<Integer> source, Stack<Integer> destination){
        if (source == null || destination == null){
            System.out.println("Stack is null");
            return;
        }

        while (!source.empty()){
            destination.push(source.pop());
        }
    }

    public static void main(String[] args) {
        QueueBy2Stack queue = new QueueBy2Stack();

        System.out.println(queue.pop());

        queue.push(1);
        queue.push(2);

        System.out.println(queue.pop());

    }
}
