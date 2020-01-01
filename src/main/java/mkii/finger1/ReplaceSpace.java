package mkii.finger1;

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
