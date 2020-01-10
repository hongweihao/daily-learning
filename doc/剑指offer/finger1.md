第一遍

> 使用Java实现，利用牛客网测试

#### 1、赋值运算符函数

#### 2、实现Singleton模式

> -> [单例模式](../design-pattern/Singleton-单例模式.md)

#### 3、数组中重复的数字

##### 题目一：找出数组中重复的数字

> 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。

###### 思路

> 暴力法：遍历数组，每个数字判断与其后的数字是否相等。相等则返回。
>
> 排序法：先排序，再扫描
>
> hash法：存储数字出现的次数，一旦发现出现2次就返回key
>
> 下标法：如果数组中没有重复的数字，那么数字应该与下标对应。从0开始扫描，如果数字与下标不对应，则与值对应的位置交换（把当前位置不符合的数字放到其符合的位置）；如果数字与下标对应，则继续扫描。直到交换相同数字的时候就找到了。

###### 解法

```java
public class Duplication {
    // 暴力法
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        // 检查合法性
        if (numbers == null || length <= 0){
            return false;
        }
        for (int number : numbers) {
            if (number >= length) {
                return false;
            }
        }

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++){
                if (numbers[j] == numbers[i]){
                    duplication[0] = numbers[i];
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2,3,1,0,2,5,3};
        int length = 7;
        int[] duplicate = new int[1];
        Duplication duplication = new Duplication();
        duplication.duplicate(a, length, duplicate);
        System.out.println(duplicate[0]);
    }
}
```

###### 分析

>  暴力法：时间复杂度是O(n^2)
>
> 排序法：时间复杂读取决于排序算法，一般使用O(nlogn)的算法
>
> hash法：时间复杂度为O(n)，空间复杂度为O(n)
>
> 下标法：时间复杂度O(n)



##### 题目二：不修改数组找出重复的数字

> 

###### 思路

> 

###### 解法

> 

###### 分析

> 

#### 4、二维数组中的查找

> 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

###### 思路

> 选择右上角元素x，如果x大于目标整数a，则排除x这一列。如果x小于a则排除x这一行。直到越界或者x等于a

###### 解法

```java
/**
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，
 * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 测试用例：
 * 1.正常数组，目标在数组中
 * 2.正常数组，目标不在数组中
 * 3.空数组
 *
 */
public class IntInBinaryArray {
    public boolean Find(int target, int [][] array) {
        if (array == null || array.length <= 0 || array[0].length <= 0){
            return false;
        }
        int row = 0;
        int col = array.length - 1;
        // 右上角的元素
        int topRight;

        while (row < array.length && col >= 0) {
            topRight = array[row][col];
            // 找到目标元素
            if (topRight == target) {
                return true;
            }
            if (topRight < target) {
                // 右上角元素小于目标元素，排除这一行
                row++;
            }else{
                // 右上角元素大于目标元素，排除这一列
                col--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IntInBinaryArray intInBinaryArray = new IntInBinaryArray();
        /*int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };*/

        int[][] array = {{}};
        System.out.println(intInBinaryArray.Find(10, array));
    }
}
```

###### 分析

> 时间复杂度与行数和列数相关



#### 5、替换空格

> 
> 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
> 

###### 思路

> 先扫描有几个空格，构建一个新的字符串，长度由空格数和原字符串计算而来。将原字符串除空格外的内容搬到新字符串

###### 解法

```java
/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 *
 * 测试：
 * 1.带空格字符串
 * 2.不带空格字符串
 * 3.空字符串
 */
public class ReplaceSpace {
    public String replaceSpace(StringBuffer str) {
        if (str == null || str.length() <= 0){
            return "";
        }
        String replace = "%20";
        StringBuffer newStr = new StringBuffer();

        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == ' '){
                newStr.append(replace);
            }else{
                newStr.append(str.charAt(i));
            }
        }
        return newStr.toString();
    }

    public static void main(String[] args) {
        ReplaceSpace replaceSpace = new ReplaceSpace();

        StringBuffer stringBuffer = new StringBuffer("we are family!");
        StringBuffer stringBuffer1 = new StringBuffer("family!");
        StringBuffer stringBuffer3 = new StringBuffer("");
        StringBuffer stringBuffer2 = null;

        System.out.println(replaceSpace.replaceSpace(stringBuffer));
        System.out.println(replaceSpace.replaceSpace(stringBuffer1));
        System.out.println(replaceSpace.replaceSpace(stringBuffer2));
        System.out.println(replaceSpace.replaceSpace(stringBuffer3));
    }
}
```

###### 分析

> 时间复杂度是O(n)
>
> 空间复杂度是O(n)

#### 6、从尾到头打印链表

> 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
>
> ```java
> class ListNode {    
>     int val;    
>     ListNode next = null;    
>     ListNode(int val) {        
>     	this.val = val;    
>     }
> }
> ```

###### 思路

> 使用栈：遍历链表，放入栈。再将栈内元素按序取出打印。
>
> 递归：遍历到当前节点时，先判断是否存在后继节点，如果存在则先打印后继节点，不存在则打印自己。
>
> 但是链表过长会发生栈溢出异常。

###### 解法

```java
/**
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 *
 * 测试：
 * 1. 空链表
 * 2. 正常链表
 */
public class ReversePrintLinkedList {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (listNode == null){
            return arrayList;
        }
        Stack<Integer> stack = new Stack<>();
        while (listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.empty()){
            arrayList.add(stack.pop());
        }
        return arrayList;
    }
}
```

###### 分析

> 时间复杂度O(n)
>
> 空间复杂度O(n)

#### 7、重建二叉树

> 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
>
> ```java
> class TreeNode {    
>     int val;    
>     TreeNode left;    
>     TreeNode right;    
>     TreeNode(int x) {        
>         val = x;    
>     }
> }
> ```

###### 思路

> 利用递归，先从前序序列中取出根节点的值，在中序序列中找到根节点的位置，分成两个区域，递归。

###### 解法

```java
/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * 思路：将每一个节点都看作一个根节点（包括叶子节点），遍历前序序列匹配中序序列可以得到每一个“根”节点
 *
 * 测试：
 * 1. 两个正确的序列
 * 2. 一个错误的前序序列，正确的中序序列
 * 3. 两个错误的序列
 * 4. 一个空前序序列
 * 5. 一个空中序序列
 * 6. 两个空序列
 */
public class RebuildTree {
    // 前序序列的指针（确定根）
    private int prePoint = 0;

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length <= 0 || in.length <= 0) {
            return null;
        }
        TreeNode root = getNode(pre, in, 0, in.length - 1);
        return root;
    }

    private TreeNode getNode(int[] pre, int[] in, int inStart, int inEnd) {
        // 确定根节点在中序序列中的位置
        int rootIndex = -1;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[prePoint]) {
                rootIndex = i;
            }
        }
        if (rootIndex == -1) {
            return null;
        }
        // 确定根节点，如果是叶子节点，就不会继续create左右节点
        TreeNode root = new TreeNode(pre[prePoint]);

        //左子树
        if (inStart <= rootIndex - 1) {
            prePoint++;
            root.left = getNode(pre, in, inStart, rootIndex - 1);
        }
        if (inEnd >= rootIndex + 1) {
            prePoint++;
            root.right = getNode(pre, in, rootIndex + 1, inEnd);
        }
        return root;
    }

    public void inEach(TreeNode treeNode){
        if (treeNode == null){
            return;
        }
        inEach(treeNode.left);
        System.out.println(treeNode.val);
        inEach(treeNode.right);
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        RebuildTree rebuildTree = new RebuildTree();
        TreeNode tree = rebuildTree.reConstructBinaryTree(pre, in);
        rebuildTree.inEach(tree);

        /*System.out.println(tree.val);
        System.out.println(tree.left.val);
        System.out.println(tree.right.val);*/
    }
}
```

###### 分析

> 递推公式：f(n) = f(0..p-1) + f(p+1..n)
>
> p表示每次找到的根的位置
>
> 终止条件是到达叶子节点，即p-1<leftLimit  p+1 > rightLimit

#### 8、二叉树的下一个节点(pending)

> 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
>
> ```java
> class TreeLinkNode {
>     int val;
>     TreeLinkNode left = null;
>     TreeLinkNode right = null;
>     TreeLinkNode next = null;
> 
>     TreeLinkNode(int val) {
>         this.val = val;
>     }
> }
> ```

###### 思路

> 

###### 解法

```java

```

###### 分析

> 

#### 9、用两个栈实现队列

> 如两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

###### 思路

> 一个用来push一个用来pop

###### 解法

```java
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
```

###### 分析

> 

#### 10、斐波那契数列

##### 题目一：斐波那契数列

>  大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39

###### 思路

> 循环：相当于自下而上计算了
>
> 自上而下递归：会有大量重复计算，效率不高
>
> 自下而上递归：与循环相似，但是采用递归方式

###### 解法

```java
/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，
 * 请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39
 *
 * 测试：
 * 1. 正常区间内 n=30
 * 2. n=39
 * 3. n=40
 * 4. n=0
 * 5. n=1
 */
public class Fibonacci {

    public int fibonacci(int n) {
        if (n < 0 || n > 39){
            System.out.println("out of limit");
            return -1;
        }
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }

        int n_1 = 1;
        int n_2 = 0;
        int temp;
        for (int i = 2; i <= n; i++) {
            temp = n_1;
            n_1 = n_1 + n_2;
            n_2 = temp;
        }
        return n_1;
    }
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fibonacci(3));
    }
}
```

###### 分析

> 自上而下递推公式：f(n) = f(n-1) + f(n -2)
>
> 终止条件：n



##### 题目二：青蛙跳台阶问题

> 一只青蛙一次可以跳上1级台阶，也可以跳上2级。* 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）

###### 思路

> 递归：n级可以来自n-1级和n-2级。蛙在n-1级的时候可以跳上n级，在n-2级也可以跳上n级
>
> 数学归纳法：算出1，2，3，4，5归纳总结

###### 解法

```java
/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * <p>
 * 测试：
 * 1.n=0
 * 2.n
 */
public class Jump {

    /**
     * 数列：1,2,3,5,8,13
     * @param target
     * @return
     */
    public int JumpFloor(int target) {
        if (target <= 0)
            return 0;
        if (target == 1)
            return 1;
        if (target == 2)
            return 2;

        int n_1 = 2;
        int n_2 = 1;
        int temp;
        for (int i = 3; i <= target; i++){
            temp = n_1;
            n_1 = n_1 + n_2;
            n_2 = temp;
        }
        return n_1;
    }

    public static void main(String[] args) {
        Jump jump = new Jump();
        System.out.println(jump.JumpFloor(6));
    }
}
```

###### 分析

> 与斐波那契数列一样的



#### 11、旋转数组的最小数字

> 如把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

###### 思路

> 数组是排序的特殊数组，可以使用二分法思想。要注意存在相同的元素组成的数组

###### 解法

```java
/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * <p>
 * 测试：
 * 1. null
 * 2. 空数组
 * 3. 正常旋转数组
 * 4. 非旋转数组
 * 5. 特殊旋转数组{2,2,2, 1, 2,2,2,2}
 */
public class RotateArray {
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length <= 0) {
            System.out.println("array is null");
            return 0;
        }
        // 特殊旋转数组，重复数字
        if (array[0] == array[array.length - 1] && array[0] == array[(array.length - 1) / 2]) {
            return minInSpecieArray(array);
        }
        return minInRotateArray(array);
    }

    // 类似于二分法求解
    private int minInRotateArray(int[] array) {
        // 定义边界
        int p = 0;
        int q = array.length - 1;
        int key;
        // p+1<q 表示
        while (p + 1 < q) {
            // key是中间值，哨兵指针
            key = (p + q) / 2;
            //
            if (array[key] >= array[p]) {
                p = key;
            }
            if (array[key] <= array[q]) {
                q = key;
            }
        }
        return array[q];
    }

    private int minInSpecieArray(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return array[i + 1];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] a = {3, 4, 5, 1, 2};
        int[] b = {2, 2, 2, 1, 2, 2, 2, 2};
        int[] c = null;

        RotateArray rotateArray = new RotateArray();

        System.out.println(rotateArray.minInRotateArray(a));
        System.out.println(rotateArray.minInSpecieArray(b));

        System.out.println(rotateArray.minNumberInRotateArray(a));
        System.out.println(rotateArray.minNumberInRotateArray(b));
        System.out.println(rotateArray.minNumberInRotateArray(c));
    }
}
```

###### 分析

> 

#### 12、矩阵中的路径

> 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
>
> 例如 a b c e s f c s a d e e 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。

###### 思路

> 回溯法，天生适合递归，因为递归正好可以回退。
>
> 画出解空间树，然后遍历解空间树就可以解决问题了。

###### 解法

```java
/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * 例如 a b c e s f c s a d e e 矩阵中包含一条字符串"bcced"的路径，
 * 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 *
 * 注意：
 * 1. 越界
 * 2. 递归设置出口
 *
 * 测试：
 * 1. {'b','c', 'c', 'e', 'a'} false
 * 2. {'b','c', 'c', 'e', 'd'} true
 * 3. null {}
 * ...
 * 1.matrix的测试用例
 */
public class Matrix {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if(str == null || str.length <= 0 || matrix == null || matrix.length <= 0 || rows <= 0 || cols <= 0){
            return false;
        }
        // 记录是否走过
        int[] sign = new int[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 找到第一个字符
                if (nextChar(matrix, rows, cols, i, j, str, 0, sign)){
                    return true;
                }else{
                    // 擦除痕迹
                    sign = new int[rows * cols];
                }
            }
        }
        return false;
    }

    public boolean nextChar(char[] matrix, int rows, int cols, int i, int j, char[] str, int p, int[] sign){
        // 出口,str的字符全部可以找到位置
        if (str.length == p){
            return true;
        }
        // 越界
        if (i >= 0 && i < rows && j >= 0 && j < cols) {
            // 这个位置已经被走过了
            if (sign[i * cols + j] == 1) {
                return false;
            }
            // 不符合字符
            if (matrix[i * cols + j] != str[p]) {
                return false;
            }
            // 标记走过
            sign[i * cols + j] = 1;
            // 判断4个相邻位置是否能够走
            if (nextChar(matrix, rows, cols, i + 1, j, str, p + 1, sign) ||
                    nextChar(matrix, rows, cols, i, j + 1, str, p + 1, sign) ||
                    nextChar(matrix, rows, cols, i - 1, j, str, p + 1, sign) ||
                    nextChar(matrix, rows, cols, i, j - 1, str, p + 1, sign)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String matrixStr =  "ABCEHJIG" +
                            "SFCSLOPQ" +
                            "ADEEMNOE" +
                            "ADIDEJFM" +
                            "VCEIFGGS";
        String strStr = "SGGFIECVAASABCEHJIGQEM";
        char[] matrix = matrixStr.toCharArray();
        char[] str = strStr.toCharArray();
        Matrix ma = new Matrix();
        System.out.println(ma.hasPath(matrix,5, 8, str));
    }
}
```

###### 分析

>先找到入口，然后接着往深处走。
>
>递推公式：f(n) = f(0, 0) + .. + f(row, col); 
>
>f(row, col)=f(row-1, col) + f(row+1, col) + f(row, col-1)+f(row, col+1)



#### 13、机器人的运动路径

> 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
>
> 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
>
>  这里问题的本质是能否到达？而不是一条路最多走多少格子，所以应该是4条路的结果都加起来，但又不能重复走。。

###### 思路

> 解法与矩阵一样，只是想要的结果不同。
>
> 回溯法，天生适合递归，因为递归正好可以回退。
>
> 画出解空间树，然后遍历解空间树就可以解决问题了。

###### 解法

```java
/**
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 * (这里问题的本质是能否到达？而不是一条路最多走多少格子，所以应该是4条路的结果都加起来，但又不能重复走。。)
 *
 * 测试：
 * 1. <=0
 */
public class Machine {
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold <= 0 || rows <= 0 || cols <= 0) {
            return 0;
        }
        // 记录是否占用
        boolean[] visited = new boolean[rows * cols];
        return checkPath(threshold, rows, cols, 0, 0, visited);
    }

    /**
     * 边界检查和占用检查
     *
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    private int checkPath(int threshold, int rows, int cols, int row, int col, boolean[] visited) {
        int count = 0;
        // 占用检查和边界检查
        if (row >= 0 && row < rows && col >= 0 && col < cols && !visited[row * cols + col] && getDigit(row, col) <= threshold) {
            //count++;
            visited[row * cols + col] = true;
            //System.out.println(count + " ---> (" + row + ", " + col + ")");

            // 检查4个格子
            count = 1 + checkPath(threshold, rows, cols, row - 1, col, visited) +
                    checkPath(threshold, rows, cols, row + 1, col, visited) +
                    checkPath(threshold, rows, cols, row, col - 1, visited) +
                    checkPath(threshold, rows, cols, row, col + 1, visited);
        }
        return count;
    }

    /**
     * 根据坐标计算数位和
     *
     * @param row
     * @param col
     * @return
     */
    private int getDigit(int row, int col) {
        int sum = 0;
        row = Math.abs(row);
        col = Math.abs(col);
        do {
            sum += row % 10;
        } while ((row /= 10) > 0);

        do {
            sum += col % 10;
        } while ((col /= 10) > 0);

        return sum;
    }

    public static void main(String[] args) {
        Machine machine = new Machine();
        System.out.println(machine.movingCount(5, 10, 10)); // 21
    }
}
```

###### 分析

>递推公式
>
>终止条件



#### 14、剪绳子|pending

> 给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
>
> 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

###### 思路

> 动态规划...

###### 解法

```java

```

###### 分析

>



#### 15、二进制中1的个数

> 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
>
> & 与，只有11才是1
>
> | 或，只有00才是0
>
> ^ 异或，相同为0，不同为1

###### 思路

> 一：选择一个flag=1，与整数n进行&操作。每次&完flag进位左移1位，再次与n进行&操作。
>
> 二：一个数-1再与自己做&操作可以减少一个1

###### 解法

```java
/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * & 与，只有11才是1
 * | 或，只有00才是0
 * ^ 异或，相同为0，不同为1
 *
 * <p>
 * 测试：
 * 1. 0
 * 2. 正数
 * 3. 负数
 */
public class Number {
    // 作弊的解法(java API)
    public int NumberOf0(int n) {
        return Integer.bitCount(n);
    }
    
    //  对于正数来说，这个方法是正确的，因为正数右移是在左侧添加0。
    //  而对于负数来说，右移操作是在左侧添加1，会发生死循环
    public int NumberOf1(int n){
        int count = 0;
        while (n != 0){
            if ((n & 1) == 1){ // 如果最后一位是1
                count++;
            }
            n = n >> 1;  //把最后一位丢弃
        }
        return count;
    }
    
    // 让n的每一位都与1做&操作，统计
    public int NumberOf2(int n){
        int count = 0;
        int flag = 1;
        // flag != 0表示flag中1的位置最后左移超过位数（这里int是32位，范围在-2^31~2^31-1之间）,1(后面跟31个0)超过最大区间值会溢出，是一个负数
        while (flag != 0){
            // n & flag 都是0的时候才会满足==0，但是flag可能是正数或者负数但是不可能是0
            // 为什么>0不行？ 最后一次flag溢出时，flag是负数，如果n是正数，那么就会有<0的情况出现，就会漏掉
            if ((n & flag) != 0){
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    // 每个数-1再与自己&可以得到少一个1的数
    public int NumberOf3(int n){
        int count = 0;
        while (n != 0){
            n = n & (n - 1);
            ++count;
        }
        return count;
    }

    public static void main(String[] args) {
        Number number = new Number();
        System.out.println(number.NumberOf3(5));
        System.out.println(number.NumberOf0(5));
    }
}
```

###### 分析

>

#### 16、数值的整数次方

> 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。保证base和exponent不同时为0

###### 思路

> 循环：base的exponent次方
>
> 递归：
>
> exponent为奇数 -->递推公式：b^x = b^(x/2)*b^(x/2) --> f(b,x) = f(b,x/2) * f(b, x/2)
>
> exponent为偶数 -->f(b, x) = f(b, (x-1)/2) * f(b, (x-1)/2) * f(b, 1)
>
> 终止条件：x/2==x | x/2 == x-1

###### 解法

```java
/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 保证base和exponent不同时为0
 *
 * 测试：
 * base是0，exponent是2
 * base是2，exponent是3
 * base是2，exponent是-3
 * base是-2，exponent是3
 * base是-2，exponent是-3
 *
 */
public class Power {
    // 作弊解法, java API
    public double power(double base, int exponent) {
        return Math.pow(base, exponent);
    }

    public double power1(double base, int exponent) throws Exception{
        if (base == 0 && exponent == 0){
            throw new Exception("0");
        }
        if (base == 0){
            return 0;
        }
        if (exponent == 0){
            return 1;
        }
        
        double result = 1;
        for (int i = 0; i < Math.abs(exponent); i++){
            result *= base;
        }
        return exponent > 0 ? result : 1 / result;
    }
    
    public static void main(String[] args) throws Exception{
        Power power = new Power();
        System.out.println(power.power(-2,-3));
        System.out.println(power.power1(-2,-3));
    }
}
```

###### 分析

>

#### 17、打印从1到最大的n位数|pending

> 打印从1到最大的n位数

###### 思路

> 

###### 解法

```java
/**
 * 打印1到n位十进制数
 * (需要考虑大数问题) 可以用java的api BigInteger
 *
 * 测试：
 * 0,-1,1,5
 *
 */
public class PrintNumber {

    /**
     * 不满足大数，int可能会溢出，long也可能会溢出
     * @param n
     */
    public void print(int n){

        if (n < 1){
            return;
        }

        int sum = 0;
        while (n-- > 0){
            sum += 9 * Math.pow(10, n);
        }

        for (int i = 1; i <= sum; i++){
            System.out.println(i);
        }
    }

    public void print2(int n){

        if (n < 1){
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (n-- > 0){
            stringBuilder.append("9");
        }
        BigInteger bigInteger = new BigInteger(stringBuilder.toString());
        BigInteger bigInteger1 = new BigInteger("1");
        BigInteger val = new BigInteger("1");

        while (bigInteger.compareTo(bigInteger1) >= 0){
            System.out.println(bigInteger1);
            bigInteger1 = bigInteger1.add(val);
        }
    }

    public void print3(int n){

    }

    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber();
        printNumber.print2(10);
    }
}
```

###### 分析

>



#### 18、删除链表的节点|pending

##### 题目一：删除链表节点

> 给定单向链表的表头指针和一个节点的指针，定义一个函数在O(1)时间内删除该节点。

###### 思路

> 

###### 解法

```java

```

##### 题目二：删除重复节点

> 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
>
> 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5

###### 思路

> 当前节点与下一个节点不同，返回当前节点；当前节点与下n个系节点相等，返回下n+1个节点
>
> 递推公式：
>
> rmDuplication(node) = rmDuplication(node.next) //下一个节点不同，返回当前节点
>
> rmDuplication(node) = rmDuplication(node..(n+1)..next)  //下n个系节点相等，返回下n+1个节点
>
> 递归终止条件：node == null || node.next==null // 只存在一个节点没有被判断

###### 解法

```java
/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 *
 * 测试：
 * 1.null/只有一个节点，返回源
 * 2. 无重复
 * 3. 2个重复
 * 4. 5个重复
 * 5. 多次重复
 * 5. 多次全部重复
 *
 */
public class RemoveDuplication {
    // 这是去重的，至少会保留一个
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null){
            return pHead;
        }
        ListNode current = pHead;

        while (current != null){
            // 当前节点的下一个节点
            ListNode next = current.next;
            // 遍历相同节点并赋值，直到下一个节点是空或不同
            while (next != null && current.val == next.val){
                next = next.next;
            }
            current.next = next;
            // 如果下一个节点不同了就继续遍历
            current = current.next;
        }
        return pHead;
    }
    // 如果重复，一个不剩
    public ListNode deleteDuplication2(ListNode pHead) {
        if (pHead == null || pHead.next == null){
            return pHead;
        }
        return rmDuplication(pHead);
    }

    private ListNode rmDuplication(ListNode next){
        // 空的或者只存在1个节点就不会有重复节点了
        if (next == null || next.next == null){
            return next;
        }

        ListNode sNext = next.next;
        // 当前节点和下一个节点不相等那么，下一个节点开始删除重复
        if(next.val != sNext.val){
            next.next = rmDuplication(next.next);
            return next;
        }

        // 当前节点和下n个节点相等, 找到下一个不同节点的指针
        while (sNext != null && next.val == sNext.val){
            sNext = sNext.next;
        }
        // 如果不是最后1次重复，那么返回此段重复节点的下一个节点
        // 如果是最后一个重复，那么返回null
        return rmDuplication(sNext);
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(3);
        ListNode node8 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;

        RemoveDuplication removeDuplication = new RemoveDuplication();
        ListNode head = removeDuplication.deleteDuplication2(node1);

        System.out.println(head.val);
        System.out.println(head.next.val);
    }
}
```

###### 分析

>

#### 19、正则表达式匹配|pending

> 请实现一个函数用来匹配包括'.'和'*'的正则表达式。* 模式中的字符'.'表示任意一个字符，而'\*'表示它前面的字符可以出现任意次（包含0次）。注意：在本题中，匹配是指字符串的所有字符匹配整个模式。
>
> 例如，字符串"aaa"与模式"a.a"和"ab\*ac\*a"匹配，但是与"aa.a"和"ab*a"均不匹配

###### 思路

> 

###### 解法

```java

```

###### 分析

>



#### 20、表示数值的字符串|pending

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析

>

#### 21、调整数组顺序使奇数位于偶数前面

> 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。

###### 思路

> 创建一个新数组大小与原数组相同。先挑出奇数放入新数组再挑出偶数放入新数组。

###### 解法

```java
/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。 （稳定算法）
 *
 * 测试：
 * 1.null []
 * 2.{1,2,3,4,5,6}
 * 3.{1,3,5,7,9}
 * 3.{2,4,6}
 * 4.{0}
 * 5.{1,3,5,2,4,6}
 */
public class ReOrderArray {
    // 用O(n)空间的新数组存储
    public void reOrderArray(int [] array) {
        if (array == null || array.length <= 0){
            return;
        }
        if (array.length == 1){
            //return array;
            return;
        }

        int[] newArray = new int[array.length];
        int newArrayP = 0;
        int arrayP = 0;
        while (arrayP < array.length){
            if (array[arrayP] % 2 == 1){
                newArray[newArrayP++] = array[arrayP];
            }
            arrayP++;
        }
        arrayP = 0;
        while (arrayP < array.length){
            if (array[arrayP] % 2 == 0){
                newArray[newArrayP++] = array[arrayP];
            }
            arrayP++;
        }

        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }

        array = newArray;
        System.out.println("done~");
    }

    public static void main(String[] args) {

        int[] a = {1,2,3,4,5,6,7};
        /*int[] b = {1,3,5,7,9};
        int[] c = {0};
        int[] d = {1,3,5,2,4,6};*/

        ReOrderArray reOrderArray = new ReOrderArray();
        reOrderArray.reOrderArray(a);
        /*reOrderArray.reOrderArray(b);
        reOrderArray.reOrderArray(c);
        reOrderArray.reOrderArray(d);*/
    }
}
```

###### 分析

>

#### 22、链表中倒数第k个节点

> 输入一个链表，输出该链表中倒数第k个结点。

###### 思路

> 遍历链表，将元素取出放在一个数组中，获取第(size-k)个节点

###### 解法

```java
/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 *
 * 测试：
 * head = null, head = *p
 * k = 0, k = 1, k = 100(99)
 */
public class LinkedListKthNode {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null || k <= 0){
            return null;
        }

        List<ListNode> list = new ArrayList<>();
        while (head != null){
            //stack.push(head);
            list.add(head);
            head = head.next;
        }
        // 超出范围
        if (k > list.size()){
            return null;
        }
        return list.get(list.size() - k);
    }
    
    public static void main(String[] args) {
        LinkedListKthNode listKthNode = new LinkedListKthNode();
    }
}
```

###### 分析

>

#### 23、链表中环的入口节点

> 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。

###### 思路

> 用一个map存储已经遍历的节点，直到找到重复节点或者空

###### 解法

```java
public class LinkedAndCircle {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        // 用map存已经遍历的节点
        Map<ListNode, Integer> map = new HashMap<>();
        ListNode current = pHead;

        // 遍历，直到遇到空或者重复
        while (current != null && !map.containsKey(current)){
            map.put(current, 1);
            current = current.next;
        }
        // 遍历查找完当前节点是空或者重复节点
        return current;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node1;

        /*node2.next = node3;
        node3.next = node4;
        node4.next = node5;*/
        //node5.next = node3;

        LinkedAndCircle linkedAndCircle = new LinkedAndCircle();
        ListNode listNode = linkedAndCircle.EntryNodeOfLoop(node1);
        System.out.println(listNode.val);
    }
}
```

###### 分析

>

#### 24、反转链表

> 输入一个链表，反转链表后，输出新链表的表头。

###### 思路

> 遍历链表，用栈存储节点的值。再将栈中的数据弹出。

###### 解法

```java
/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 * <p>
 * 测试：
 * head = null, head
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null){
            return null;
        }
        // 遍历链表，用栈存储
        Stack<Integer> stack = new Stack<>();
        ListNode first = head;
        while (first != null) {
            stack.push(first.val);
            first = first.next;
        }
        // 该表链表节点的值
        first = head;
        while (!stack.empty()){
            first.val = stack.pop();
            first = first.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode1 = new ListNode(1);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ReverseList reverseLis = new ReverseList();
        ListNode result = reverseLis.reverselise2(listNode1);

        System.out.println(result.val);
        System.out.println(result.next.val);
        System.out.println(result.next.next.val);
    }
}
```

###### 分析

>

#### 25、合并两个排序的链表

> 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。

###### 思路

> 递推公式：
>
> merge(list1, list2) = merge(list1.next, list2)  // 如果list1节点值小于等于list2节点值
>
> merge(list1, list2) = merge(list1, list2.next)  // 如果list1节点值大于list2节点值
>
> 终止条件：其中一个list为null  list1 == null || list2 == null

###### 解法

```java
/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 *
 * 测试：
 * list1=null || list2 = null
 *
 */
public class MergeList {
    public ListNode merge(ListNode list1,ListNode list2) {
        // 注意这里，可能只有一个链表为空的情况
        // 这里也可以保证，递归过程中，其中一个链表已经遍历完结点，另一个链表能接上
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        ListNode head;
        if (list1.val <= list2.val){
            head = list1;
            head.next = merge(list1.next, list2);
        }else{
            head = list2;
            head.next = merge(list1, list2.next);
        }
        return head;
    }

    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode head;
        if(list1.val > list2.val){
            head = list2;
            head.next = Merge(list1, list2.next);
        }else{
            head = list1;
            head.next = Merge(list1.next, list2);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode list11 = new ListNode(1);
        ListNode list12 = new ListNode(3);
        ListNode list13 = new ListNode(5);
        ListNode list14 = new ListNode(7);

        list11.next = list12;
        list12.next = list13;
        list13.next = list14;

        ListNode list21 = new ListNode(2);
        ListNode list22 = new ListNode(4);
        ListNode list23 = new ListNode(6);
        ListNode list24 = new ListNode(8);

        list21.next = list22;
        list22.next = list23;
        list23.next = list24;

        MergeList mergeList = new MergeList();
        ListNode listNode = mergeList.merge(list11, list21);

        System.out.println(listNode.val);
        System.out.println(listNode.next.val);
        System.out.println(listNode.next.next.val);
        System.out.println(listNode.next.next.next.val);
    }
}
```

###### 分析

>

#### 26、树的子结构

> 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）

###### 思路

> 先在A中遍历查找B的根节点是否能匹配到（相当于深度优先），能匹配到则比较子树内容（hasNode）。
>
> 递推公式：
>
> // A树中找B树的根节点
>
> hasSubtree(root1, root2) = hasNode(root1, root2)  // root1.val == root2.val
>
> hasSubtree(root1, root2) = hasSubtree(root1.left, root2)  // root1.val != root2.val
>
> hasSubtree(root1, root2) = hasSubtree(root1.right, root2) // root1.left.val != root2.val
>
> 递归出口：root1 == null
>
> // AB树根节点匹配的情况下，比较A,B的子树，子节点
>
> hasNode(root1, root2) = hasNode(root1.left, root2.left) && hasNode(root1.right, root2.right)
>
> 递归出口：root2遍历完，root1提前结束，root1子节点与root2不匹配

###### 解法

```java
/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * <p>
 * 测试：
 * 1.{1, 2, 3, 4, 5}  {2, 4, 5}
 * 2.{1, 2, 3, 4, 5}  {3, 4, 6}
 * 3.{1, 2, 3, 4, 5}  {3, 4, 6}
 * 3.{1, 2, 3, 4, 5}  {3, 4, 5}
 * 4. null || null
 */
public class HasSubTree {
    // A的当前节点能否匹配到B的根节点，如果能匹配到则继续比较子节点；不能匹配到则递归判断子树能否匹配到
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        boolean flag = false;
        // 深入查找
        if (root1.val == root2.val) {
            flag = hasNode(root1, root2);
        }
        // 左子树查找，如果root的值不匹配需要在子树中查找
        if (!flag) {
            flag = hasSubtree(root1.left, root2);
        }
        // 右子树查找
        if (!flag) {
            flag = hasSubtree(root1.right, root2);
        }
        return flag;
    }

    // 判断两个树是否相同，root已经是相等了的
    private boolean hasNode(TreeNode root1, TreeNode root2) {
        // 递归出口，B树全部遍历完
        if (root2 == null) {
            return true;
        }
        // 保证边界值（叶子）
        if (root1 != null) {
            if (root1.val != root2.val) {
                return false;
            }
            // 左右子树都需要通过才行
            return hasNode(root1.left, root2.left) && hasNode(root1.right, root2.right);
        }
        // A树先到达边界
        return false;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        TreeNode treeNode22 = new TreeNode(2);
        TreeNode treeNode44 = new TreeNode(4);
        TreeNode treeNode55 = new TreeNode(5);
        treeNode22.left = treeNode44;
        treeNode22.right = treeNode55;

        HasSubTree hasSubTree = new HasSubTree();
        System.out.println(hasSubTree.hasSubtree(treeNode1, treeNode22));
    }
}
```

###### 分析

>

#### 27、二叉树镜像

> 操作给定的二叉树，将其变换为源二叉树的镜像。
>
> ```
>             8
>     	   /  \
>     	  6   10
>     	 / \  / \
>     	5  7 9 11
>     	镜像二叉树
>     	    8
>     	   /  \
>     	  10   6
>     	 / \  / \
>     	11 9 7  5
> ```

###### 思路

> 递推公式：
>
> swapChildren(root) = swapChildren(root.left) + swapChildren(root.right)
>
> 递归出口：root == null 递归完叶子节点了

###### 解法

```java
/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *
 * 测试：
 * 1. null
 * 2. root
 * 3. 正常
 * 4. 一边
 * 5.一边再一边
 */
public class BinaryTreeMirror {
    public void Mirror(TreeNode root){
        root = swapChildren(root);
    }
    // 交换子树，子节点
    private TreeNode swapChildren(TreeNode root){
        if (root == null){
            return root;
        }
        // 交换子树
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 交换子树之后，再交换子树的子树
        root.left = swapChildren(root.left);
        root.right = swapChildren(root.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(8);
        TreeNode treeNode2 = new TreeNode(7);
        TreeNode treeNode3 = new TreeNode(6);
        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(9);
        TreeNode treeNode7 = new TreeNode(11);
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
        treeNode3.left = treeNode4;
        treeNode4.left = treeNode5;

        BinaryTreeMirror binaryTreeMirror = new BinaryTreeMirror();
        binaryTreeMirror.Mirror(treeNode1);
    }
}
```

###### 分析

>

#### 28、对称的二叉树

> 请实现一个函数，用来判断一颗二叉树是不是对称的。
>
> 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。

###### 思路

> 递推公式：
>
> compare(left, right) = compare(left.left, right.right) && compare(left.right, right.left)
>
> 递推出口：至少一个子树为空，比较失败

###### 解法

```java
/**
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 *
 * 并不一定左右子树相同，也有可能是叶子交换
 *
 * 测试：
 * 1。null
 * 2.左右不对称
 * 3.左右对称
 * 4.左右不均衡
 */
public class SymmetricalBinaryTree {
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null || ( pRoot.left == null && pRoot.right == null)){
            return true;
        }
        // 比较左右子树是否对称
        return compare(pRoot.left, pRoot.right);
    }
    
    private boolean compare(TreeNode left, TreeNode right){
        // 左右子树都空
        if (left == null && right == null){
            return true;
        }
        // 左右子树其中一个为空
        if (left == null || right == null){
            return false;
        }
        // 左右子树根节点值相同
        if (left.val == right.val){
            // 要求是对称的
            return compare(left.left, right.right) && compare(left.right, right.left);
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode node = null;

        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        
        SymmetricalBinaryTree symmetricalBinaryTree = new SymmetricalBinaryTree();
        //System.out.println(symmetricalBinaryTree.isSymmetrical(node));
        System.out.println(symmetricalBinaryTree.isSymmetrical(node1));
    }
}
```

###### 分析
> 

#### 29、顺时针打印矩阵

> 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
>
> 例如，如果输入如下4 X 4矩阵： 
>
> 1     2     3   4 
>
> 5     6     7   8
>
> 9    10  11 12 
>
> 13 14   15 16
>
> 则依次出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.

###### 思路

> （pending）

###### 解法

```java
/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 * <p>
 * 测试：
 * 1.正常
 * 2. null || length=0
 * 3.只有1行
 * 4.只有1列
 * 5.只有一行一列
 */
public class PrintMatrix {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> numbers = new ArrayList<>();
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return numbers;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = rows;
        int col = cols;
        int line = 0;
        // 不一定行列相同，要小于最小的
        while ((line * 2) < Math.min(rows, cols)) {
            System.out.println(line << 2);
            int i, j;
            // up
            for (i = line; i < col; i++) {
                numbers.add(matrix[line][i]);
            }
            // right
            for (--i, j = line + 1; j < row; j++) {
                numbers.add(matrix[j][i]);
            }
            // bottom 如果只有一行的情况下，要保证只打印一次
            for (--j, --i; i >= line && j > line; i--) {
                numbers.add(matrix[j][i]);
            }
            // left， 入职只有一列的情况下，要保证只打印一次
            for (++i, --j; j > line && i < col - 1; j--) {
                numbers.add(matrix[j][i]);
            }
            row--;
            col--;
            line++;
        }
        return numbers;
    }

    public static void main(String[] args) {
         //int[][] matrix = {{1}};
        //int[][] matrix = {{1,2},{3,4},{5,6},{7,8},{9,10}};
        int[][] matrix = {{1,2,3,4,5},
                        {6,7,8,9,10},
                        {11,12,13,14,15},
                        {16,17,18,19,20},
                        {21,22,23,24,25}};
        PrintMatrix printMatrix = new PrintMatrix();
        ArrayList<Integer> result = printMatrix.printMatrix(matrix);
        System.out.println(result.size());
    }
}
```

###### 分析
> 

#### 30、包含min函数的栈

> 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数,时间复杂度应为O(1)。

###### 思路

> 把min元素放在stack顶，每次push需要先pop min再放入元素，最后再判断min是否需要变化。

###### 解法

```java
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
```

###### 分析
> 

#### 31、栈的压入弹出序列

> 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
>
> 例如：序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）

###### 思路

> 在栈中找到弹出序列的元素就继续下一个元素，没找到弹出序列元素就将压入序列的一个元素压入栈。

###### 解法

```java
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
```

###### 分析
>

#### 32、从上到下打印二叉树

> 题目一：从上往下打印出二叉树的每个节点，同层节点从左至右打印。
>
> 题目二：分行打印二叉树（牛客题目）

###### 思路

> 题目一：借助一个队列，存储未遍历的节点
>
> 题目二：借助两个队列，分行存储未遍历的节点

###### 解法

```java
/**
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 * <p>
 * 测试：
 * 1. null
 * 2. 完全二叉树
 * 3. 一般二叉树
 * 4. 左一半二叉树
 * 5. 右一半二叉树
 * 6. 只有根节点
 *
 */
public class PrintBinaryTree {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        // 打印输出的结点值
        ArrayList<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        // 使用队列按顺序暂时存放每一层的结点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode currentNode = null;

        while (!queue.isEmpty()) {
            // 获取队列头
            currentNode = queue.remove();
            // 将队列头的结点输出
            nodes.add(currentNode.val);
            // 将队列头的子节点放到队列尾
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        return nodes;
    }

    // newcode题目
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        // 打印输出的结点值
        ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
        if (pRoot == null) {
            return nodes;
        }
        // 使用队列按顺序暂时存放每一层的结点
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(pRoot);
        TreeNode currentNode = null;

        while (!queue1.isEmpty() || !queue2.isEmpty()) {

            Queue<TreeNode> queue, emptyQueue;
            if (queue1.isEmpty()){
                queue = queue2;
                emptyQueue = queue1;
            }else {
                queue = queue1;
                emptyQueue = queue2;
            }
            ArrayList<Integer> node = new ArrayList<>();
            while (!queue.isEmpty()) {
                // 获取队列头
                currentNode = queue.remove();
                // 将队列头的结点输出
                node.add(currentNode.val);
                // 将队列头的子节点放到队列尾
                if (currentNode.left != null) {
                    emptyQueue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    emptyQueue.add(currentNode.right);
                }
            }
            nodes.add(node);
        }
        return nodes;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        
        PrintBinaryTree printBinaryTree = new PrintBinaryTree();
        //ArrayList<Integer> result = printBinaryTree.PrintFromTopToBottom(treeNode1);
        ArrayList<ArrayList<Integer>> nodes = printBinaryTree.Print(treeNode1);

        for (ArrayList<Integer> node : nodes) {
            for (Integer i : node) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
```

###### 分析
>

> 题目三：之字形打印二叉树
>
> 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。

###### 思路

> 用两个栈，分行存储未遍历的节点

###### 解法

```java
/**
 * 请实现一个函数按照之字形打印二叉树，
 * 即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，
 * 第三行按照从左到右的顺序打印，其他行以此类推。
 *
 */
public class PrintBtreeWithZhi {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot == null){
            return arrayLists;
        }
        // 用两个栈存储每一行的节点
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(pRoot);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            ArrayList<Integer> arrayList = new ArrayList<>();

            // 奇数行，根节点在这一行
            while (!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                if (node.left != null) {
                    stack2.push(node.left);
                }
                if (node.right != null) {
                    stack2.push(node.right);
                }
                //System.out.println(node.val);
                arrayList.add(node.val);
            }
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            // 偶数行
            while (!stack2.isEmpty()) {
                TreeNode node = stack2.pop();
                if (node.right != null) {
                    stack1.push(node.right);
                }
                if (node.left != null) {
                    stack1.push(node.left);
                }
                //System.out.println(node.val);
                arrayList2.add(node.val);
            }
            // 如果只new一个List会有可能2行存储到一个List中
            if (!arrayList.isEmpty()) {
                arrayLists.add(arrayList);
            }
            if (!arrayList2.isEmpty()) {
                arrayLists.add(arrayList2);
            }
        }
        return arrayLists;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;
        node5.left = node10;
        node5.right = node11;
        node6.left = node12;

        PrintBtreeWithZhi printBtreeWithZhi = new PrintBtreeWithZhi();
        ArrayList<ArrayList<Integer>> arrayLists = printBtreeWithZhi.Print(node1);

        for (ArrayList<Integer> arrayList : arrayLists) {
            for (Integer integer : arrayList) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
```

###### 分析
>

#### 33、二叉搜索树的后序遍历序列

> 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。

###### 思路

> （pending）

###### 解法

```java
/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * 二叉搜索树（Binary Search Tree）又称二叉排序树（Binary Sort Tree），
 * 需要保证根节点的左子树元素皆小于根，右子树皆大于根。
 * 中序遍历序列是一个排序完成的序列
 *
 * 跟前边的重建二叉树类似
 *
 *
 * 测试：
 * 1. 正常序列
 * 2. 错误序列
 * 3. null | length = 0
 * 4. 1
 * 5. 只有左树
 * 6. 只有右树
 */
public class BinarySearchTree {
    // 后序序列指针，如果不设置全局，一旦递归两层就会丢失递归函数中的操作，（--p不生效了，p的位置就混乱了）
    private int p;

    public boolean VerifySequenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length <= 0){
            return false;
        }

        // 得到中序遍历序列
        int[] mid = Arrays.copyOf(sequence, sequence.length);
        Arrays.sort(mid);
        // 初始化指针后往前，因为是后序序列
        p = sequence.length - 1;
        return eachBinarySearchTree(mid, 0, mid.length - 1, sequence);
    }

    // 根据中/后序序列，生成一颗二叉搜索树
    private boolean eachBinarySearchTree(int[] mid, int low, int high, int[] after/*, int p*/){
        // 找到根在中序序列中的位置
        int rootIndex = -1;
        for (int i = low; i <= high; i++) {
            if (mid[i] == after[p]){
                rootIndex = i;
                break;
            }
        }
        // 判断根节点是否存在
        if (rootIndex == -1){
            return false;
        }
        // 子树
        boolean flag = true;
        if (rootIndex + 1 <= high) {
            --p;
            flag = eachBinarySearchTree(mid, rootIndex + 1, high, after/*, --p*/);
        }
        if (low <= rootIndex - 1) {
            --p;
            flag = flag && eachBinarySearchTree(mid, low, rootIndex - 1, after/*, --p*/);
        }
        return flag;
    }

    public static void main(String[] args) {
        int[] after = {2,3,1};
        BinarySearchTree searchTree = new BinarySearchTree();
        System.out.println(searchTree.VerifySequenceOfBST(after));
    }
}
```

###### 分析
>



#### 34、二叉树中和为某一值的路径

> 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
>
> (注意: 在返回值的list中，数组长度大的数组靠前)

###### 思路

> 深度优先遍历，用一个arrayList收集路径

###### 解法

```java
/**
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 *
 * 测试：
 *
 */
public class SumIsPathInBinaryTree {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        ArrayList<Integer> path = new ArrayList<>();
        int sum = 0;
        find(root, target, sum, path, result);
        return result;
    }
    private ArrayList<ArrayList<Integer>> find(TreeNode root, int target, int current, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result){
        // 将路径数值放入路径中
        path.add(root.val);
        current += root.val;

        // 到达叶子节点且路径符合，就收集起来
        boolean isLeaf = root.left == null && root.right == null;
        if (target == current && isLeaf){
            System.out.print("found a path: ");
            for (Integer i : path) {
                System.out.print(i);
            }
            System.out.println();
            result.add(new ArrayList<Integer>(path));
        }
        // 不是叶子节点就继续遍历子节点
        if (root.left != null){
            find(root.left, target, current, path, result);
        }
        if (root.right != null){
            find(root.right, target, current, path, result);
        }
        // 返回父节点，删除路径中的当前节点
        path.remove(path.size() - 1);
        return result;
    }

    public static void main(String[] args) {
        int target = 22;
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(12);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        SumIsPathInBinaryTree sumIsPathInBinaryTree = new SumIsPathInBinaryTree();
        ArrayList<ArrayList<Integer>> result = sumIsPathInBinaryTree.FindPath(node1, target);

        System.out.println(node3.val);
    }
}
```

###### 分析
>

#### 35、复杂链表的复制

> 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
>
> （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
>
> ```java
> class RandomListNode {    
>     int label;    
>     RandomListNode next = null;    
>     RandomListNode random = null;    
>     RandomListNode(int label) {        
>         this.label = label;    
>     }
> }
> ```

###### 思路

> 创建一个map，用来存储已经创建的节点。先处理label（此节点），再处理random节点，最后通过递归处理next。

###### 解法

```java
/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，
 * 一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 *
 * 测试：
 * 1. null
 * 2.
 */
public class CloneLinked {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        Map<Integer, RandomListNode> nodes = new  HashMap<>();
        // clone list
        RandomListNode head = createNode(pHead, nodes);
        return head;
    }
    private RandomListNode createNode(RandomListNode head, Map<Integer, RandomListNode> nodes){
        RandomListNode node = null;
        // 递归出口
        if (head == null){
            return node;
        }
        // 处理label
        int label = head.label;
        if (nodes.containsKey(label)){
            // 节点已经存在
            node = nodes.get(label);
        }else {
            // 节点不存在需要重新new
            node = new RandomListNode(label);
        }
        // 处理random
        RandomListNode random = head.random;
        if (random != null) {
            if (nodes.containsKey(random.label)) {
                // 节点已经存在
                node.random = nodes.get(random.label);
            } else {
                // 节点不存在需要重新new
                node.random = new RandomListNode(random.label);
            }
        }
        //node.random = createNode(head.random, nodes);
        node.next = createNode(head.next, nodes);
        return node;
    }

    public static void main(String[] args) {
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node1.random = node3;
        node2.random = node3;
        node3.random = node1;
        node4.random = node2;

        CloneLinked cloneLinked = new CloneLinked();
        cloneLinked.Clone(node1);
    }
}
```

###### 分析
>

#### 36、二叉搜索树和双向链表

> 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。

###### 思路

> pending

###### 解法

```java
/**
 * ？？？ 还需要时间理解
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 总体思路复盘：分为左子树/根/右子树。另外需要一个指针指向已排序的最后一个节点
 * 先对左子树进行转换，然后根作为左子树的最后（最大）一个节点。
 * 然后对根的右子树转换。
 *
 */
public class BinarySearchTreeAndDoubleLinkedList {
    // 指向转换好的链表的最后一个节点
    private TreeNode lastNode;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        convertNode(pRootOfTree);

        // 取得双向链表的头节点
        TreeNode headNode = lastNode;
        while (headNode != null && headNode.left != null){
            headNode = headNode.left;
        }
        return headNode;
    }

    private void convertNode(TreeNode node){
        if (node == null) {
            return;
        }
        // 当前节点
        TreeNode current = node;
        // 左子树转换
        if (current.left != null){
            convertNode(current.left);
        }
        // 当前节点的左指针需要指向左子树的最后一个节点
        current.left = lastNode;

        // 当前节点作为左子树链表的最大节点
        if (lastNode != null){
            lastNode.right = current;
        }
        lastNode = current;

        // 左子树转换完就转换右子树
        if (current.right != null){
            convertNode(current.right);
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(8);
        TreeNode node5 = new TreeNode(14);
        TreeNode node6 = new TreeNode(12);
        TreeNode node7 = new TreeNode(16);
        node1.left = node2;
        node1.right = node5;
        node2.left = node3;
        node2.right = node4;
        node5.left = node6;
        node5.right = node7;

        BinarySearchTreeAndDoubleLinkedList binarySearchTreeAndDoubleLinkedList = new BinarySearchTreeAndDoubleLinkedList();
        TreeNode head = binarySearchTreeAndDoubleLinkedList.Convert(node1);
        System.out.println(head.val);
    }
}
```

###### 分析
>

#### 37、序列化二叉树|pending

> 请实现两个函数，分别用来序列化和反序列化二叉树

###### 思路

> 数

###### 解法

```java

```

###### 分析
>



#### 38、字符串的排列|pending

> 输入一个字符串,按字典序打印出该字符串中字符的所有排列。 
>
> 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。

###### 思路

> 数

###### 解法

```java

```

###### 分析
>



#### 39、数组中出现次数超过一半的数字

> 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
>
> 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

###### 思路

> pending

###### 解法

```java
/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 *
 * 测试:
 * 1. null | []
 * 2. [1]
 * 3. [122222234]
 */
public class HalfNumber {
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array == null || array.length <= 0){
            return 0;
        }
        if (array.length == 1){
            return array[0];
        }
        int len = array.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : array) {
            if (map.containsKey(i)){
                int count = map.get(i) + 1;
                if (count<<1 > len){
                    return i;
                }
                map.put(i, count);
            }else{
                map.put(i, 1);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] array = {3};
        HalfNumber halfNumber = new HalfNumber();
        System.out.println(halfNumber.MoreThanHalfNum_Solution(array));
    }
}
```

###### 分析
>



#### 40、最小的k个数

> 输入n个整数，找出其中最小的K个数。
>
> 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。

###### 思路

> 这里使用选择排序：O(n*k)
>
> 可以使用堆：O(nlogk)

###### 解法

```java
/**
 * 输入n个整数，找出其中最小的K个数。
 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 *
 * 测试：
 * 1. null | []
 * 2. k <= 0
 * 3. len < k
 * [4,5,1,6,2,7,3,8] 4
 */
public class MinK {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> mins = new ArrayList<>(k);
        if (input == null || k <= 0 || input.length < k){
            return mins;
        }
        int temp;
        // 这里使用选择排序，每一轮得到一个最小值，时间复杂度度是n*k。不能使用冒泡，因为冒泡只是两个值之间的交换，需要n*n得出n个值排好序
        for (int i = 0; i < k; i++){
            for (int j = i + 1; j < input.length; j++){
                if (input[i] > input[j]){
                    temp = input[j];
                    input[j] = input[i];
                    input[i] = temp;
                }
            }
            mins.add(input[i]);
        }
        return mins;
    }

    public static void main(String[] args) {
        int[] input = {4,5,1,6,2,7,3,8};
        int k = 1;
        MinK minK = new MinK();
        minK.GetLeastNumbers_Solution(input, k);
    }
}
```

###### 分析
>



#### 41、数据流中的中位数|pending

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>



#### 42、连续子数组的最大和|pending

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>



#### 43、1~n整数中1出现的次数

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>



#### 44、数字序列中某一位的数字| pending

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>



#### 45、把数组排成最小的数

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>



#### 46、把数字翻译成字符串

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 47、礼物的最大价值

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 48、最长不重复字符的子字符串

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 49、丑数

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 50、第一个只出现一次的字符

> 题目一：

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

> 题目二：字节流中第一个只出现一次的字符
>
> 

###### 思路

> 数

###### 解法

```java

```

###### 分析

> 

#### 51、数组中的逆序对

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 52、两个链表的第一个公共节点

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 53、在排序数组中查找数字

> 题目一:

###### 思路

> 数

###### 解法

```java

```

###### 分析
>



> 题目二：0~n-1中缺少的数字

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

> 题目三：数组中数值和下标相等的元素

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 54、二叉搜索树的第k大节点

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 55、二叉树的深度

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 

> 题目二：平衡二叉树

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 56、数组中数字出现的次数

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 

> 题目二：数组中唯一只出现一次的数字

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 57、和为s的两个数字

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 

> 题目二：和为s的连续整数序列

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 58、反转字符串

> 题目一：反转单词顺序

###### 思路

> 数

###### 解法

```java

```

###### 分析
>



> 题目二：左旋转字符串

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 59、队列的最大值

> 题目一：滑动窗口的最大值

###### 思路

> 数

###### 解法

```java

```

###### 分析
>



> 题目二：队列的最大值

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 60、n个骰子的点数

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 61、扑克牌中的顺子

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 62、圆圈中最后剩下的数字

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 63、股票的最大利润

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 64、求1+2+...+n

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 65、不用加减乘除做加法

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 66、构建乘积数组

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 67、把字符串转换成整数

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>

#### 68、树中两个节点的最低公共祖先

> 如

###### 思路

> 数

###### 解法

```java

```

###### 分析
>