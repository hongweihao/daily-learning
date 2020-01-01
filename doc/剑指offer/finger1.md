### 第一遍

> 使用Java实现，利用牛客网测试

#### 1、赋值运算符函数

> 如下为类型`CMyString`的声明，请为该类型添加赋值运算符函数

##### 思路



##### 解法



##### 分析



#### 2、实现Singleton模式（单例模式）

> 设计一个类，要求只能生成该类的一个实例

##### 思路



##### 解法



##### 分析



#### 3、数组中重复的数字

> 题目一：找出数组中重复的数字。
>
> 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。

##### 思路

> 暴力法：遍历数组，每个数字判断与其后的数字是否相等。相等则返回。
>
> 排序法：先排序，再扫描
>
> hash法：存储数字出现的次数，一旦发现出现2次就返回key
>
> 下标法：如果数组中没有重复的数字，那么数字应该与下标对应。从0开始扫描，如果数字与下标不对应，则与值对应的位置交换（把当前位置不符合的数字放到其符合的位置）；如果数字与下标对应，则继续扫描。直到交换相同数字的时候就找到了。

##### 解法

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

##### 分析

>  暴力法：时间复杂度是O(n^2)
>
> 排序法：时间复杂读取决于排序算法，一般使用O(nlogn)的算法
>
> hash法：时间复杂度为O(n)，空间复杂度为O(n)
>
> 下标法：时间复杂度O(n)



> 题目二：...

##### 思路



##### 解法



##### 分析





#### 4、二维数组中的查找

> 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

##### 思路

> 选择右上角元素x，如果x大于目标整数a，则排除x这一列。如果x小于a则排除x这一行。直到越界或者x等于a

##### 解法

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

##### 分析

> 时间复杂度与行数和列数相关



#### 5、替换空格

> 
> 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
> 

##### 思路

> 先扫描有几个空格，构建一个新的字符串，长度由空格数和原字符串计算而来。将原字符串除空格外的内容搬到新字符串

##### 解法

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

##### 分析

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

##### 思路

> 使用栈：遍历链表，放入栈。再将栈内元素按序取出打印。
>
> 递归：遍历到当前节点时，先判断是否存在后继节点，如果存在则先打印后继节点，不存在则打印自己。
>
> 但是链表过长会发生栈溢出异常。

##### 解法

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

##### 分析

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

##### 思路

> 利用递归，先从前序序列中取出根节点的值，在中序序列中找到根节点的位置，分成两个区域，递归。

##### 解法

```java
/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * 思路：将每一个节点都看作一个根节点（包括叶子节点），遍历前序序列匹配中序序列可以得到每一个“根”节点
 *
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

##### 分析

> 递推公式：f(n) = f(0..p-1) + f(p+1..n)
>
> p表示每次找到的根的位置
>
> 终止条件是到达叶子节点，即p-1<leftLimit  p+1 > rightLimit



#### 8、二叉树的下一个节点(pending)

> 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。* 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
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

##### 思路

> 

##### 解法

```java

```

##### 分析





#### 9、用两个栈实现队列

> 如两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

##### 思路

> 一个用来push一个用来pop

##### 解法

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

##### 分析

> 



#### 10、斐波那契数列

> 题目一：大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39

##### 思路

> 循环：相当于自下而上计算了
>
> 自上而下递归：会有大量重复计算，效率不高
>
> 自下而上递归：与循环相似，但是采用递归方式

##### 解法

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

##### 分析

> 自上而下递推公式：f(n) = f(n-1) + f(n -2)
>
> 终止条件：n



> 题目二：青蛙跳台阶问题
>
> 一只青蛙一次可以跳上1级台阶，也可以跳上2级。* 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）

##### 思路

> 递归：n级可以来自n-1级和n-2级。蛙在n-1级的时候可以跳上n级，在n-2级也可以跳上n级
>
> 数学归纳法：算出1，2，3，4，5归纳总结

##### 解法

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

##### 分析

> 与斐波那契数列一样的



#### 11、旋转数组的最小数字

> 如把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

##### 思路

> 数组是排序的特殊数组，可以使用二分法思想。要注意存在相同的元素组成的数组

##### 解法

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

##### 分析

> 

#### 12、矩阵中的路径

> 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
>
> 例如 a b c e s f c s a d e e 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。

##### 思路

> 回溯法，天生适合递归，因为递归正好可以回退。
>
> 画出解空间树，然后遍历解空间树就可以解决问题了。

##### 解法

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

##### 分析

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

##### 思路

> 解法与矩阵一样，只是想要的结果不同。
>
> 回溯法，天生适合递归，因为递归正好可以回退。
>
> 画出解空间树，然后遍历解空间树就可以解决问题了。

##### 解法

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

##### 分析

>递推公式
>
>终止条件



#### 14、剪绳子(pending)

> 给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
>
> 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

##### 思路

> 动态规划...

##### 解法

```java

```

##### 分析

>



#### 15、二进制中1的个数

> 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
>
> & 与，只有11才是1
>
> | 或，只有00才是0
>
> ^ 异或，相同为0，不同为1

##### 思路

> （pending）

##### 解法

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
    
    //
    public int NumberOf2(int n){
        int count = 0;
        int flag = 1;
        // 为什么flag>0不行？因为int的范围会导致最后一次左移溢出为负数，每次左移都会进一位，最后超出限制了为0
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

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



#### 11、

> 如

##### 思路

> 数

##### 解法

```java

```

##### 分析

>



