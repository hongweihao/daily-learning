package mkii.datastru;

/**
 * 用数组实现一个顺序栈
 *
 * 入栈：先判断是否满了，满了无法入栈，因为不提供扩容功能。
 *      将元素加入栈顶（top+1）
 * 出栈；是否空，如果空，无法出栈
 *      不为空，弹出（top）
 *
 */
public class ArrayStack {
    // 数据域
    private int[] elements;
    // 栈顶指针
    private int top;
    // 栈大小
    private int size;

    // 初始化，只提供自定义大小，不提供扩容
    public ArrayStack(int size){
        this.size = size;
        this.top = -1;
        this.elements = new int[size];
    }

    // 把元素压入栈
    public boolean push(int data){
        if (top + 1 == size){
            System.out.println("栈已经满了！");
            return false;
        }
        elements[++top] = data;
        System.out.println("the data(" + data + ") has already been pushed!");
        return true;
    }

    // 找到栈顶元素并弹出
    public int pop() throws Exception{
        if (size == 0 || top == -1){
            //System.out.println("栈为空！");
            throw new Exception("栈为空！");
        }
        // 数组中还是会有该元素存在，可能会造成内存泄漏。
        return elements[top--];
    }
    public int top() throws Exception{
        if (size == 0 || top == -1){
            //System.out.println("栈为空！");
            throw new Exception("栈为空！");
        }
        // 数组中还是会有该元素存在，可能会造成内存泄漏
        return elements[top];
    }

    public static void main(String[] args) throws Exception{
        ArrayStack arrayStack = new ArrayStack(4);
        arrayStack.push(1);
        arrayStack.push(2);
        System.out.println(arrayStack.top);

        arrayStack.push(3);
        arrayStack.push(4);
        System.out.println(arrayStack.top);
        //arrayStack.push(5);

        System.out.println("pop :" + arrayStack.pop());
        System.out.println("pop :" + arrayStack.pop());
        System.out.println(arrayStack.top);
    }
}
